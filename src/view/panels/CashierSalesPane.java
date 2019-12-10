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
import model.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
//TODO Create controller
public class CashierSalesPane extends GridPane implements Observer {
    private ObservableList<Article> articles = FXCollections.observableArrayList();
    private Label totalPrice;
    private Button holdSaleBtn;
    private DomainInterface domainInterface;

    public CashierSalesPane(DomainInterface domainInterface) {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.domainInterface = domainInterface;
        domainInterface.addShopObserver(this);
        domainInterface.addBasketObserver(this);

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
                domainInterface.clearBasketArticles();
                articleCode.clear();
            }
        });

        articleCode.setOnKeyReleased(event ->{
            if (event.getCode() == KeyCode.ENTER) {
                if ( articleCode.getText().trim().isEmpty() || articleCode.getText() == null|| domainInterface.getContext().get(Integer.parseInt(articleCode.getText())) == null) {
                    alert.setContentText("This code doesn't exist.\n Try a different code.");
                    alert.showAndWait();
                } else {
                    Article article = domainInterface.getContext().get(Integer.parseInt(articleCode.getText()));
                    domainInterface.addBasketArticle(article);
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
                domainInterface.removeBasketArticleIndices(selectedIndices);
            }
        });

        holdSaleBtn.setOnAction(e -> {
            if (domainInterface.saleIsOnHold()) {
                domainInterface.continueHeldSale();
            } else {
                domainInterface.putSaleOnHold();
            }
            updateHoldSaleButton();
        });

        pay.setOnAction(event -> {
            //Om te testen
            ReceiptFactory generateReceipt = new ReceiptFactory();
            try {
                System.out.println(generateReceipt.MakeReceiptFactory().getReceipt(domainInterface));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }



    public void populateArticles() {
        articles.clear();
        articles.addAll(domainInterface.getAllBasketArticles());
    }

    private void updateHoldSaleButton() {
        String btnText = domainInterface.saleIsOnHold() ? "Continue sale on hold" : "Put sale on hold";
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
                    setTotalPrice((Double)data); break;
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
                    break;
                case RESUMED_SALE:
                    Basket basket = (Basket) data;
                    basket.addObserver(this);
                    populateArticles();
                    break;
            }
        }
    }
}
