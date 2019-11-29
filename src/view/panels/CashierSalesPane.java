package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Article;
import model.Shop;

public class CashierSalesPane extends GridPane {
    private Shop shop;
    private TableView<Article> table = new TableView<>();

    ObservableList<Article> articles = FXCollections.observableArrayList();


    public CashierSalesPane(Shop shop) {
        final double[] thePrice = {0};
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.shop = shop;

        final TextField articleCode = new TextField();
        articleCode.setPromptText("Enter Article Code");
        articleCode.setPrefColumnCount(10);
        articleCode.getText();
        GridPane.setConstraints(articleCode, 0, 0);
        this.getChildren().add(articleCode);

        //Defining the Clear button
        Button clear = new Button("Clear Field");
        GridPane.setConstraints(clear, 1, 0);
        this.getChildren().add(clear);

        TableColumn sales = new TableColumn("Products");
        TableColumn<Article, Integer> code = new TableColumn<>("Article Code");
        TableColumn<Article, Integer> name = new TableColumn<>("Article Name");
        TableColumn<Article, Integer> group = new TableColumn<>("Article Group");
        TableColumn<Article, Integer> price = new TableColumn<>("Article Price");
        Label label = new Label("Price: €");
        TextField totalprice = new TextField();

        sales.getColumns().addAll(code, name, group, price);


        table.setMaxSize(400, 400);


        code.setMinWidth(table.getMaxWidth() / 5);
        name.setMinWidth(table.getMaxWidth() / 5);
        group.setMinWidth(table.getMaxWidth() / 5);
        price.setMinWidth(table.getMaxWidth() / 5);

        code.setCellValueFactory(new PropertyValueFactory("articleCode"));
        name.setCellValueFactory(new PropertyValueFactory("articleName"));
        group.setCellValueFactory(new PropertyValueFactory("group"));
        price.setCellValueFactory(new PropertyValueFactory("price"));

        table.getColumns().addAll(sales);
        totalprice.setEditable(false);

        this.add(table, 0, 4,4,20);
        this.add(label,0,1);
        this.add(totalprice,1,1);


        //Setting an action for the Clear button
        clear.setOnAction(e -> articleCode.clear());
        articleCode.setOnKeyReleased(event ->{
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println(articleCode.getText());
                Article article = shop.getContext().get(Integer.parseInt(articleCode.getText()));
                articles.add(article);
                thePrice[0] += article.getPrice();
                System.out.println(article);
                System.out.println(thePrice[0]);
                table.setItems(articles);
                totalprice.setText(String.valueOf(thePrice[0]));
            }
        });

    }

}
