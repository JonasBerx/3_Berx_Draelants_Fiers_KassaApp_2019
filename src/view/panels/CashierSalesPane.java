package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class CashierSalesPane extends GridPane implements Observer {
    private DomainInterface domainInterface;
    private TableView<Article> table = new TableView<>();
    private ObservableList<Article> articles = FXCollections.observableList(new ArrayList<>());
    private double totalPrice;

    public CashierSalesPane(DomainInterface domainInterface) {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.domainInterface = domainInterface;
        domainInterface.addBasketObserver(this);

        final TextField articleCode = new TextField();
        articleCode.setPromptText("Enter Article Code");
        articleCode.setPrefColumnCount(10);
        articleCode.getText();
        GridPane.setConstraints(articleCode, 0, 0);
        this.getChildren().add(articleCode);

        //Defining the Clear button
        Button clear = new Button("Reset");
        GridPane.setConstraints(clear, 3, 0,4,1);
        this.getChildren().add(clear);

        TableColumn sales = new TableColumn("Products");
        TableColumn<Article, Integer> code = new TableColumn<>("Article Code");
        TableColumn<Article, Integer> name = new TableColumn<>("Article Name");
        TableColumn<Article, Integer> group = new TableColumn<>("Article Group");
        TableColumn<Article, Integer> price = new TableColumn<>("Article Price");
        Label totalprice = new Label("Total: € 0.0");

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Oofsies woofsies");
        alert.setHeaderText("You made an oopsies");

        sales.getColumns().addAll(code, name, group, price);

        table.setMaxSize(800, 800);
        table.setItems(articles);

        code.setMinWidth(table.getMaxWidth() / 5);
        name.setMinWidth(table.getMaxWidth() / 5);
        group.setMinWidth(table.getMaxWidth() / 5);
        price.setMinWidth(table.getMaxWidth() / 5);

        code.setCellValueFactory(new PropertyValueFactory("articleCode"));
        name.setCellValueFactory(new PropertyValueFactory("articleName"));
        group.setCellValueFactory(new PropertyValueFactory("group"));
        price.setCellValueFactory(new PropertyValueFactory("price"));

        table.getColumns().addAll(sales);


        this.add(table, 0, 4,4,1);

        this.add(totalprice,1,5);


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
                    articleCode.clear();
                } else {
                    Article article = domainInterface.getContext().get(Integer.parseInt(articleCode.getText()));
                    domainInterface.addBasketArticle(article);
                    totalPrice += article.getPrice();

                    articleCode.clear();
                }
            }
        });

    }

    @Override
    public void update(Enum event, Object data) {
        System.out.println(event.name());
        if (event.equals(BasketEvent.ADDED_ARTICLE)) {
            Article article = (Article) data;
            articles.add(article);
        } else if (event.equals(BasketEvent.CLEARED_ARTICLES)) {
            articles.clear();
        } else if (event.equals(BasketEvent.REMOVED_ALL_ARTICLES)) {
            Collection<Article> articles = (Collection<Article>) data;
            articles.removeAll(articles);
        }
    }

    private void updatePrice() {
        totalprice.setText("Total: €" + domainInterface.getBasketTotalPrice());
    }
}
