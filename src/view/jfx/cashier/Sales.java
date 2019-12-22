package view.jfx.cashier;

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

import java.util.List;
import java.util.Map;
import java.util.Optional;
//TODO Create controller
public class Sales extends GridPane implements IAlerts {
    private ObservableList<Article> articles = FXCollections.observableArrayList();
    private Label totalPriceLbl;
    private Button holdSaleBtn;
    private Button clearBtn;
    private Button payBtn;
    private TextField articleCodeFld;
    private TableView<Article> articlesTbl;

    public Sales() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        articleCodeFld = new TextField();
        articleCodeFld.setPromptText("Enter Article Code");
        articleCodeFld.setPrefColumnCount(10);
        articleCodeFld.getText();
        GridPane.setConstraints(articleCodeFld, 0, 0);
        this.getChildren().add(articleCodeFld);

        //Defining the Clear button
        clearBtn = new Button("Reset");
        GridPane.setConstraints(clearBtn, 1, 0);
        this.getChildren().add(clearBtn);

        //Defining Pause button
        holdSaleBtn = new Button("Pause Sale");
        GridPane.setConstraints(holdSaleBtn, 2, 0);
        this.getChildren().add(holdSaleBtn);

        //Defining Pause button
        Button delete = new Button("Delete Article");
        GridPane.setConstraints(delete, 3, 0);
        this.getChildren().add(delete);
        //Defining Pause button
        payBtn = new Button("Pay");
        GridPane.setConstraints(payBtn, 4, 0);
        this.getChildren().add(payBtn);


        TableColumn sales = new TableColumn("Products");
        TableColumn<Article, Integer> code = new TableColumn<>("Article Code");
        TableColumn<Article, Integer> name = new TableColumn<>("Article Name");
        TableColumn<Article, Integer> group = new TableColumn<>("Article Group");
        TableColumn<Article, Integer> price = new TableColumn<>("Article Price");
        totalPriceLbl = new Label();
        totalPriceLbl.setFont(new Font("Arial", 25));
        setTotalPriceLbl(0.0);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Oofsies woofsies");
        alert.setHeaderText("You made an oopsies");

        sales.getColumns().addAll(code, name, group, price);
        payBtn.setDisable(true);
        articlesTbl = new TableView<>();
        articlesTbl.setMaxSize(800, 800);
        articlesTbl.setItems(articles);
        articlesTbl.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        code.setMinWidth(articlesTbl.getMaxWidth() / 5);
        name.setMinWidth(articlesTbl.getMaxWidth() / 5);
        group.setMinWidth(articlesTbl.getMaxWidth() / 5);
        price.setMinWidth(articlesTbl.getMaxWidth() / 5);

        code.setCellValueFactory(new PropertyValueFactory("articleCode"));
        name.setCellValueFactory(new PropertyValueFactory("articleName"));
        group.setCellValueFactory(new PropertyValueFactory("group"));
        price.setCellValueFactory(new PropertyValueFactory("price"));

        articlesTbl.getColumns().addAll(sales);

        this.add(articlesTbl, 0, 4, 5, 1);
        this.add(totalPriceLbl, 1, 5);

        delete.setDisable(true);
        articlesTbl.getSelectionModel().selectedItemProperty().addListener((lst, old, newSelection) -> {
            delete.setDisable(newSelection == null);
        });

        //Delete button event
        delete.setOnAction(e -> {

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
}
