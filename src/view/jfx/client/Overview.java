package view.jfx.client;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.util.Pair;
import model.*;
import model.article.Article;
import model.basket.Basket;
import model.observer.Observer;

import java.util.Map;

//TODO Create controller
public class Overview extends GridPane {
    private ObservableList<Pair<Article, Integer>> articles = FXCollections.observableArrayList();
    private Label totalPriceLbl;
    private Label discountPriceLbl;

    public Overview() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        TableColumn productInfo = new TableColumn("Products");
        TableColumn<Pair<Article, Integer>, String> nameCol = new TableColumn<>("Article Name");
        TableColumn<Pair<Article, Integer>, Number> priceCol = new TableColumn<>("Price");
        TableColumn<Pair<Article, Integer>, Number> quantityCol = new TableColumn<>("Amount");

        TableView<Pair<Article, Integer>> table = new TableView<>();
        table.setMaxSize(490, 600);

        nameCol.setMinWidth(table.getMaxWidth() / 3);
        priceCol.setMinWidth(table.getMaxWidth() / 3);
        quantityCol.setMinWidth(table.getMaxWidth() / 3);

        nameCol.setCellValueFactory(data -> new SimpleStringProperty((data.getValue().getKey().getName())));
        priceCol.setCellValueFactory(data -> new SimpleDoubleProperty((data.getValue().getKey().getPrice())));
        quantityCol.setCellValueFactory(data -> new SimpleIntegerProperty(((data.getValue().getValue() + 1))));

        productInfo.getColumns().addAll(nameCol, priceCol, quantityCol);
        table.setItems(articles);
        table.getColumns().addAll(productInfo);

        totalPriceLbl = new Label();
        totalPriceLbl.setFont(new Font("Arial", 30));
        setTotalPriceLbl(0.0);

        discountPriceLbl = new Label();
        totalPriceLbl.setFont(new Font("Arial", 30));
        setDiscountPriceLbl(0.0);

        this.add(table, 0, 0);
        this.add(totalPriceLbl, 0, 1);
        this.add(discountPriceLbl, 0, 2);
    }

    public void setTotalPriceLbl(Double price) {
        totalPriceLbl.setText(String.format("Total: €%.2f", price));
    }

    public void setDiscountPriceLbl(Double price) {
        discountPriceLbl.setText(String.format("DiscountPrice: €%.2f", price));
    }

    private Pair<Article, Integer> getPair(Article article) {
        return articles.stream().filter(p -> p.getKey().equals(article))
                .findFirst()
                .orElse(null);
    }

    private void updatePair(Pair<Article, Integer> oldPair, Pair<Article, Integer> newPair) {
        articles.remove(oldPair);
        articles.add(newPair);
    }

    public void addArticle(Article article) {
        addArticle(article, 1);
    }

    public void addArticle(Article article, Integer amountToAdd) {
        Pair<Article, Integer> existing = getPair(article);
        int amount = existing == null ? amountToAdd : existing.getValue() + amountToAdd;
        updatePair(existing, new Pair<>(article, amount));
    }

    public void addArticles(Map<Article, Integer> articleAmounts) {
        articleAmounts.forEach(this::addArticle);
    }

    public void removeArticle(Article article) {
        removeArticles(article, 1);
    }

    public void removeArticles(Article article, int amountToRemove) {
        Pair<Article, Integer> pair = getPair(article);
        int amount = pair.getValue();
        articles.remove(pair);
        if (amountToRemove > amount)
            return;

        articles.add(new Pair<>(article, amount - amountToRemove));
    }

    public void clearArticles() {
        articles.clear();
    }
}
