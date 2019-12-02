package view.panels;

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
import javafx.util.Pair;
import model.*;

import java.util.Collection;

public class ClientOverview extends GridPane implements Observer {
    private ObservableList<Pair<Article, Integer>> articles = FXCollections.observableArrayList();
    private Label totalPrice;

    public ClientOverview(DomainInterface domainInterface) {
        domainInterface.addBasketObserver(this);

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

        nameCol.setCellValueFactory(data -> new SimpleStringProperty((data.getValue().getKey().getArticleName())));
        priceCol.setCellValueFactory(data -> new SimpleDoubleProperty((data.getValue().getKey().getPrice())));
        quantityCol.setCellValueFactory(data -> new SimpleIntegerProperty(((data.getValue().getValue()))));

        productInfo.getColumns().addAll(nameCol, priceCol, quantityCol);
        domainInterface.getAllBasketArticles().forEach(this::addArticle);
        table.setItems(articles);
        table.getColumns().addAll(productInfo);

        totalPrice = new Label();
        setTotalPrice(0.0);

        this.add(table, 0, 0);
        this.add(totalPrice,0,1);
    }

    private void setTotalPrice(Double price) {
        totalPrice.setText(String.format("Total: €%.2f", price));
    }

    private Pair<Article, Integer> getPair(Article article) {
        return articles.stream().filter(p -> p.getKey().equals(article))
                .findFirst()
                .orElse(null);
    }

    private void updateCount(Pair<Article, Integer> oldPair, Pair<Article, Integer> newPair) {
        articles.remove(oldPair);
        articles.add(newPair);
    }

    private void addArticle(Article article) {
        Pair<Article, Integer> existing = getPair(article);
        int count = 0;
        if (existing != null) {
            count = existing.getValue() + 1;
        }
        updateCount(existing, new Pair<>(article, count));
    }

    private void removeArticle(Article article) {
        Pair<Article, Integer> existing = getPair(article);
        if (existing != null) {
            int count = existing.getValue();
            if (count == 0) {
                articles.remove(existing);
            } else {
                updateCount(existing, new Pair<>(article, count - 1));
            }
        }
    }

    @Override
    public void update(Enum event, Object data) {
        if (event instanceof BasketEvent) {
            BasketEvent basketEvent = (BasketEvent) event;
            switch (basketEvent) {
                case ADDED_ARTICLE:
                    addArticle((Article) data); break;
                case CLEARED_ARTICLES:
                    articles.clear(); break;
                case REMOVED_ARTICLES:
                    Collection<Article> removed = (Collection<Article>) data;
                    removed.forEach(this::removeArticle);
                    break;
                case REMOVED_ARTICLE_INDEX:
                    articles.remove((int) data); break;
                case REMOVED_ARTICLE:
                    removeArticle((Article) data); break;
                case REMOVED_ARTICLE_INDICES:
                    Collection<Integer> removedIndices = (Collection<Integer>) data;
                    for (int i : removedIndices)
                        articles.remove(i);
                    break;
                case TOTAL_PRICE_CHANGED:
                    setTotalPrice((Double) data); break;
            }
        }
    }
}
