package view.jfx.cashier;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.Util;
import model.article.Article;
import view.jfx.IAlerts;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static view.jfx.Helpers.setCellValueFactory;

//TODO Create controller
public class Sale extends GridPane implements IAlerts {
    private ObservableList<Article> articles = FXCollections.observableArrayList();
    private Label totalPriceLbl;
    private Button holdSaleBtn;
    private Button clearBtn;
    private Button payBtn;
    private Button deleteBtn;
    private Button closeBtn;
    private TextField articleCodeFld;
    private TableView<Article> articlesTbl;

    public Sale() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        articleCodeFld = new TextField();
        articleCodeFld.setPromptText("Artikelcode");
        articleCodeFld.setPrefColumnCount(10);
        articleCodeFld.getText();
        GridPane.setConstraints(articleCodeFld, 0, 0);
        this.getChildren().add(articleCodeFld);

        //Defining Pause button
        holdSaleBtn = new Button("Pauzeer verkoop");
        GridPane.setConstraints(holdSaleBtn, 2, 0);
        this.getChildren().add(holdSaleBtn);

        //Defining Pause button
        deleteBtn = new Button("Verwijder");
        GridPane.setConstraints(deleteBtn, 3, 0);
        this.getChildren().add(deleteBtn);

        //Defining the Clear button
        clearBtn = new Button("Verwijder alle");
        GridPane.setConstraints(clearBtn, 4, 0);
        this.getChildren().add(clearBtn);

        closeBtn = new Button("Sluit af");
        GridPane.setConstraints(closeBtn, 5, 0);
        this.getChildren().add(closeBtn);

        //Defining Pause button
        payBtn = new Button("Pay");
        payBtn.setDisable(true);
//        GridPane.setConstraints(payBtn, 5, 0);
//        this.getChildren().add(payBtn);


        TableColumn sales = new TableColumn<>("Products");
        TableColumn<Article, Integer> code = new TableColumn<>("Article Code");
        TableColumn<Article, String> name = new TableColumn<>("Article Name");
        TableColumn<Article, String> group = new TableColumn<>("Article Group");
        TableColumn<Article, Double> price = new TableColumn<>("Article Price");
        totalPriceLbl = new Label();
        totalPriceLbl.setFont(new Font("Arial", 25));
        setTotalPriceLbl(0.0);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Oofsies woofsies");
        alert.setHeaderText("You made an oopsies");

        sales.getColumns().addAll(code, name, group, price);
        articlesTbl = new TableView<>();
        articlesTbl.prefWidthProperty().bind(this.prefWidthProperty());
        articlesTbl.setMaxSize(1000, 800);
        articlesTbl.setItems(articles);
        articlesTbl.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        code.prefWidthProperty().bind(articlesTbl.widthProperty().divide(4));
        name.prefWidthProperty().bind(articlesTbl.widthProperty().divide(4));
        group.prefWidthProperty().bind(articlesTbl.widthProperty().divide(4));
        price.prefWidthProperty().bind(articlesTbl.widthProperty().divide(4));

        setCellValueFactory(code, Article::getCode);
        setCellValueFactory(name, Article::getName);
        setCellValueFactory(group, Article::getGroup);
        setCellValueFactory(price, Article::getPrice);

        articlesTbl.getColumns().addAll(sales);

        this.add(articlesTbl, 0, 4, 6, 1);
        this.add(totalPriceLbl, 1, 5);

        deleteBtn.setDisable(true);
        articlesTbl.getSelectionModel().selectedItemProperty().addListener((lst, old, newSelection) -> {
            deleteBtn.setDisable(newSelection == null);
        });
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
    }

    public void setHoldSaleBtn(boolean isOnHold) {
        String btnText = isOnHold ? "Continue sale on hold" : "Put sale on hold";
        holdSaleBtn.setText(btnText);
    }

    public void setTotalPriceLbl(Double price) {
        totalPriceLbl.setText(String.format("Total: €%.2f", price));
    }

    public void setDiscountPriceLbl(double basketDiscountedPrice) {
    }

    public void clearArticles() {
        articles.clear();
    }

    public List<Article> getSelectedArticles() {
        return articlesTbl.getSelectionModel().getSelectedItems();
    }

    public String getArticleCodeFld() {
        return articleCodeFld.getText();
    }

    public void clearArticleCodeFld() {
        articleCodeFld.clear();
    }

    public void setEnablePayBtn(boolean enabled) {
        payBtn.setDisable(!enabled);
    }

    public void setOnClearArticles(Runnable action) {
        clearBtn.setOnAction(e -> action.run());
    }

    public void setOnSubmitArticleCode(Runnable action) {
        articleCodeFld.setOnAction(e -> action.run());
    }

    public void setOnToggleHoldSale(Runnable action) {
        holdSaleBtn.setOnAction(e -> action.run());
    }

    public void setOnPay(Runnable action) {
        payBtn.setOnAction(e -> action.run());
    }

    public void setOnRemove(Runnable action) {
        deleteBtn.setOnAction(e -> action.run());
    }
}
