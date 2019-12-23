package view.jfx.cashier;

import javafx.beans.binding.DoubleBinding;
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

import java.util.*;

import static view.jfx.Helpers.setCellValueFactory;
import static view.jfx.Helpers.tableColumn;

//TODO Create controller
public class Sale extends GridPane implements IAlerts {
    private ObservableList<Article> articles = FXCollections.observableArrayList();
    private Label totalPriceLbl;
    private Button holdSaleBtn;
    private Button clearBtn;
    private Button payBtn;
    private Button cancelBtn;
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
        holdSaleBtn = new Button();
        GridPane.setConstraints(holdSaleBtn, 2, 0);
        this.getChildren().add(holdSaleBtn);

        //Defining Pause button
        deleteBtn = new Button("Verwijder");
        GridPane.setConstraints(deleteBtn, 3, 0);
        this.getChildren().add(deleteBtn);
        deleteBtn.setDisable(true);

        //Defining the Clear button
        clearBtn = new Button("Verwijder alle");
        GridPane.setConstraints(clearBtn, 4, 0);
        this.getChildren().add(clearBtn);

        closeBtn = new Button("Sluit af");
        GridPane.setConstraints(closeBtn, 5, 0);
        this.getChildren().add(closeBtn);


        articlesTbl = new TableView<>();
        TableColumn<Article, ?> sales = new TableColumn<>("Products");
        DoubleBinding width = articlesTbl.widthProperty().divide(4);
        List<TableColumn<Article, ?>> columns = Arrays.asList(
                tableColumn("Code", Article::getCode, width),
                tableColumn("Name", Article::getName, width),
                tableColumn("Group", Article::getGroup, width),
                tableColumn("Price", Article::getPrice, width)
        );
        sales.getColumns().addAll(columns);
        articlesTbl.prefWidthProperty().bind(this.prefWidthProperty());
        articlesTbl.setItems(articles);
        articlesTbl.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        articlesTbl.getColumns().add(sales);
        articlesTbl.getSelectionModel().selectedItemProperty().addListener((lst, old, newSelection) -> {
            deleteBtn.setDisable(newSelection == null);
        });
        this.add(articlesTbl, 0, 4, 6, 1);

        totalPriceLbl = new Label();
        totalPriceLbl.setFont(new Font("Arial", 25));
        setTotalPriceLbl(0.0);
        this.add(totalPriceLbl, 1, 5);

        payBtn = new Button("Pay");
        payBtn.setDisable(true);
        GridPane.setConstraints(payBtn, 0, 6, 3, 1);
        this.getChildren().add(payBtn);

        cancelBtn = new Button("Cancel");
        cancelBtn.setDisable(true);
        GridPane.setConstraints(cancelBtn, 3, 6,3,1);
        this.getChildren().add(cancelBtn);
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
    }

    public void setHoldSaleBtnEnabled(boolean enabled) {
        holdSaleBtn.setDisable(!enabled);
    }

    public void setHoldSaleBtnHeld(boolean isOnHold) {
        String btnText = isOnHold ? "Hervat verkoop" : "Pauzeer verkoop";
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

    public void setCloseBtnEnabled(boolean enabled) {
        closeBtn.setDisable(!enabled);
    }

    public void setPayBtnEnabled(boolean enabled) {
        payBtn.setDisable(!enabled);
    }

    public void setCancelBtnEnabled(boolean enabled) {
        cancelBtn.setDisable(!enabled);
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

    public void setOnRemove(Runnable action) {
        deleteBtn.setOnAction(e -> action.run());
    }

    public void setOnClose(Runnable action) {
        closeBtn.setOnAction(e -> action.run());
    }

    public void setOnPay(Runnable action) {
        payBtn.setOnAction(e -> action.run());
    }

    public void setOnCancel(Runnable action) {
        cancelBtn.setOnAction(e -> action.run());
    }
}
