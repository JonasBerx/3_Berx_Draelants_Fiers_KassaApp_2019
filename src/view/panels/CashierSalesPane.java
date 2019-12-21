package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.util.Pair;
import model.DomainFacade;
import model.Util;
import model.article.Article;
import model.basket.Basket;
import model.basket.BasketEvent;
import model.basket.BasketEventData;
import model.observer.EventData;
import model.observer.Observer;
import model.receipt.ReceiptFactory;
import model.shop.ShopEvent;
import model.shop.ShopEventData;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
//TODO Create controller
public class CashierSalesPane extends GridPane implements Observer {
    private ObservableList<Article> articles = FXCollections.observableArrayList();
    private Label totalPrice;
    private Button holdSaleBtn;
    private DomainFacade domainFacade;

    public CashierSalesPane(DomainFacade domainFacade) {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.domainFacade = domainFacade;
        domainFacade.addShopObserver(this);
        domainFacade.addBasketObserver(this);

        final TextField articleCode = new TextField();
        articleCode.setPromptText("Enter Article Code");
        articleCode.setPrefColumnCount(10);
        articleCode.getText();
        GridPane.setConstraints(articleCode, 0, 0);
        this.getChildren().add(articleCode);

        //Defining the Clear button
        Button clear = new Button("Reset");
        GridPane.setConstraints(clear, 1, 0);
        this.getChildren().add(clear);

        //Defining Pause button
        holdSaleBtn = new Button("Pause Sale");
        GridPane.setConstraints(holdSaleBtn, 2, 0);
        this.getChildren().add(holdSaleBtn);
        updateHoldSaleButton();
        //Defining Pause button
        Button delete = new Button("Delete Article");
        GridPane.setConstraints(delete, 3, 0);
        this.getChildren().add(delete);
        //Defining Pause button
        Button pay = new Button("Pay");
        GridPane.setConstraints(pay, 4, 0);
        this.getChildren().add(pay);


        TableColumn sales = new TableColumn("Products");
        TableColumn<Article, Integer> code = new TableColumn<>("Article Code");
        TableColumn<Article, Integer> name = new TableColumn<>("Article Name");
        TableColumn<Article, Integer> group = new TableColumn<>("Article Group");
        TableColumn<Article, Integer> price = new TableColumn<>("Article Price");
        totalPrice = new Label();
        totalPrice.setFont(new Font("Arial", 25));
        setTotalPrice(0.0);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Oofsies woofsies");
        alert.setHeaderText("You made an oopsies");

        sales.getColumns().addAll(code, name, group, price);
        pay.setDisable(true);
        TableView<Article> table = new TableView<>();
        table.setMaxSize(800, 800);
        table.setItems(articles);
        populateArticles();
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        code.setMinWidth(table.getMaxWidth() / 5);
        name.setMinWidth(table.getMaxWidth() / 5);
        group.setMinWidth(table.getMaxWidth() / 5);
        price.setMinWidth(table.getMaxWidth() / 5);

        code.setCellValueFactory(new PropertyValueFactory("articleCode"));
        name.setCellValueFactory(new PropertyValueFactory("articleName"));
        group.setCellValueFactory(new PropertyValueFactory("group"));
        price.setCellValueFactory(new PropertyValueFactory("price"));

        table.getColumns().addAll(sales);

        this.add(table, 0, 4, 5, 1);
        this.add(totalPrice, 1, 5);

        delete.setDisable(true);
        table.getSelectionModel().selectedItemProperty().addListener((lst, old, newSelection) -> {
            delete.setDisable(newSelection == null);
        });

        //Setting an action for the Clear button
        clear.setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Reset");
            a.setHeaderText("Are you sure?");
            a.setContentText("You will remove all scanned products");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                domainFacade.clearBasketArticles();
                articleCode.clear();
            }
        });

        articleCode.setOnKeyReleased(event ->{
            if (event.getCode() == KeyCode.ENTER) {
                if ( articleCode.getText().trim().isEmpty() || articleCode.getText() == null|| domainFacade.getContext().get(Integer.parseInt(articleCode.getText())) == null) {
                    alert.setContentText("This code doesn't exist.\n Try a different code.");
                    alert.showAndWait();
                } else {
                    Article article = domainFacade.getContext().get(Integer.parseInt(articleCode.getText()));
                    pay.setDisable(false);
                    domainFacade.addBasketArticle(article);
                }
                articleCode.clear();
            }
        });
        //Delete button event
        delete.setOnAction(e -> {
            Map<Article, Integer> selectedItems = Util.flatListToAmountMap(table.getSelectionModel().getSelectedItems());
            if (selectedItems.size() == 0) return;
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Delete");
            a.setHeaderText("Are you sure?");
            StringBuilder itemsStr = new StringBuilder();
            selectedItems.forEach((article, amount) -> {
                itemsStr.append(String.format(
                        "%s (x %d)\n"
                , article.getName(), amount));
            });
            a.setContentText("You will remove: \n" + itemsStr.toString());
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                domainFacade.removeBasketArticles(selectedItems);
                if (domainFacade.getAllUniqueBasketArticles().isEmpty()) {
                    pay.setDisable(true);
                }
            }
        });

        holdSaleBtn.setOnAction(e -> {
            if (domainFacade.saleIsOnHold()) {
                domainFacade.continueHeldSale();
            } else {
                domainFacade.putSaleOnHold();
            }
            updateHoldSaleButton();
        });

        pay.setOnAction(event -> {
            if (domainFacade.getAllUniqueBasketArticles().size() > 0) {
                ReceiptFactory generateReceipt = new ReceiptFactory();
                try {
                    System.out.println(generateReceipt.MakeReceiptFactory().getReceipt(domainFacade));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    public void populateArticles() {
        articles.clear();
        articles.addAll(domainFacade.getAllUniqueBasketArticles());
    }

    private void updateHoldSaleButton() {
        String btnText = domainFacade.saleIsOnHold() ? "Continue sale on hold" : "Put sale on hold";
        holdSaleBtn.setText(btnText);
    }

    private void setTotalPrice(Double price) {
        totalPrice.setText(String.format("Total: €%.2f", price));
    }

    private void handleBasketSwitchEvent(Basket oldBasket) {
        oldBasket.removeObserver(this);
        populateArticles();
        setTotalPrice(domainFacade.getBasketTotalPrice());
    }

    @Override
    public void update(Enum<?> event, EventData data) {
        if (event instanceof BasketEvent) {
            BasketEvent basketEvent = (BasketEvent) event;
            BasketEventData basketEventData = (BasketEventData) data;
            switch (basketEvent) {
                case ADDED_ARTICLE:
                    articles.add(basketEventData.getAddedArticle());
                    break;
                case CLEARED_ARTICLES:
                    articles.clear();
                    break;
                case REMOVED_ARTICLE:
                    articles.remove(basketEventData.getRemovedArticle());
                    break;
                case REMOVED_ARTICLES:
                    basketEventData.getRemovedArticles().forEach((article, amount) -> {
                        for (int i = 0; i < amount; i++) {
                            articles.remove(article);
                        }
                    });
                    break;
                case TOTAL_PRICE_CHANGED:
                    setTotalPrice(domainFacade.getBasketTotalPrice());
                    break;
            }
        } else if (event instanceof ShopEvent) {
            ShopEvent shopEvent = (ShopEvent) event;
            ShopEventData shopEventData = (ShopEventData) data;
            switch (shopEvent) {
                case PUT_SALE_ON_HOLD:
                case RESUMED_SALE:
                    handleBasketSwitchEvent(shopEventData.getOldBasket());
                    break;
            }
        }
    }
}
