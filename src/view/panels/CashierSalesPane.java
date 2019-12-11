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
import model.article.Article;
import model.basket.Basket;
import model.basket.BasketEvent;
import model.observer.Observer;
import model.receipt.ReceiptFactory;
import model.shop.ShopEvent;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
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
            List<Integer> selectedIndices = table.getSelectionModel().getSelectedIndices();
            if (selectedIndices.size() == 0) return;
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Delete");
            a.setHeaderText("Are you sure?");
            StringBuilder itemTitlesBuilder = new StringBuilder();
            selectedIndices.forEach(i -> itemTitlesBuilder.append(articles.get(i).getArticleName()).append('\n'));
            a.setContentText("You will remove: \n" + itemTitlesBuilder.toString());
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) {
                domainFacade.removeBasketArticleIndices(selectedIndices);
                if (domainFacade.getAllBasketArticles().isEmpty()) {
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
            if (domainFacade.getAllBasketArticles().size() > 0) {
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
        articles.addAll(domainFacade.getAllBasketArticles());
    }

    private void updateHoldSaleButton() {
        String btnText = domainFacade.saleIsOnHold() ? "Continue sale on hold" : "Put sale on hold";
        holdSaleBtn.setText(btnText);
    }

    private void setTotalPrice(Double price) {
        totalPrice.setText(String.format("Total: €%.2f", price));
    }

    @Override
    public void update(Enum event, Object data) {
        if (event instanceof BasketEvent) {
            BasketEvent basketEvent = (BasketEvent) event;
            switch (basketEvent) {
                case ADDED_ARTICLE:
                    articles.add((Article) data); break;
                case CLEARED_ARTICLES:
                    articles.clear(); break;
                case REMOVED_ARTICLE:
                    articles.remove((Article) data); break;
                case REMOVED_ARTICLE_INDEX:
                    articles.remove((int) data); break;
                case REMOVED_ARTICLES:
                    articles.removeAll((Collection<Article>) data); break;
                case REMOVED_ARTICLE_INDICES:
                    Collection<Integer> removedIndices = ((Pair<Collection<Integer>, Collection<Article>>)data).getKey();
                    for (int i : removedIndices)
                        articles.remove(i);
                    break;
                case TOTAL_PRICE_CHANGED:
                    Pair<Double, Double> prices = (Pair<Double, Double>) data;
                    setTotalPrice(prices.getKey()); break;
            }
        } else if (event instanceof ShopEvent) {
            ShopEvent shopEvent = (ShopEvent) event;
            switch (shopEvent) {
                case PUT_SALE_ON_HOLD:
                    Pair<Basket, Basket> baskets = (Pair) data;
                    Basket oldBasket = baskets.getKey();
                    Basket newBasket = baskets.getValue();
                    oldBasket.removeObserver(this);
                    newBasket.addObserver(this);
                    populateArticles();
                    setTotalPrice(newBasket.getTotalPrice());
                    break;
                case RESUMED_SALE:
                    Basket basket = (Basket) data;
                    basket.addObserver(this);
                    populateArticles();
                    setTotalPrice(basket.getTotalPrice());
                    break;
            }
        }
    }
}
