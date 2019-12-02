package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import model.Article;
import model.Shop;

import java.util.Optional;

public class CashierSalesPane extends GridPane {
    private Shop shop;
    private TableView<Article> table = new TableView<>();
    private double totalPrice;

    ObservableList<Article> articles = FXCollections.observableArrayList();


    public CashierSalesPane(Shop shop) {

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
        Button clear = new Button("Reset");
        GridPane.setConstraints(clear, 2, 0);
        this.getChildren().add(clear);

        //Defining Pause button
        Button pause = new Button("Pause Sale");
        GridPane.setConstraints(pause, 3, 0);
        this.getChildren().add(pause);


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
                articles.removeAll();
                articles = FXCollections.observableArrayList();
                totalPrice = 0;
                System.out.println(articles);
                System.out.println(totalPrice);
                table.setItems(articles);
                totalprice.setText("Total: €" + totalPrice);
                articleCode.clear();
            }

        });
        articleCode.setOnKeyReleased(event ->{

            if (event.getCode() == KeyCode.ENTER) {
                if ( articleCode.getText().trim().isEmpty() || articleCode.getText() == null|| shop.getContext().get(Integer.parseInt(articleCode.getText())) == null) {
                    alert.setContentText("This code doesn't exist.\n Try a different code.");
                    alert.showAndWait();
                    articleCode.clear();
                } else {
                    System.out.println(articleCode.getText());
                    Article article = shop.getContext().get(Integer.parseInt(articleCode.getText()));
                    articles.add(article);
                    totalPrice += article.getPrice();
                    System.out.println(article);
                    System.out.println(totalPrice);
                    table.setItems(articles);
                    totalprice.setText("Total: €" +totalPrice);
                    articleCode.clear();

                }

            }
        });

    }

}
