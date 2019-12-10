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
import javafx.scene.text.Font;
import javafx.util.Pair;
import model.*;

import java.util.Collection;
import java.util.List;
//TODO Create controller
public class ClientOverview extends GridPane implements Observer {
    private ObservableList<Pair<Article, Integer>> articles = FXCollections.observableArrayList();
    private Label totalPrice;
    private DomainInterface domainInterface;
    private Label discountPrice;

    public ClientOverview(DomainInterface domainInterface) {
        this.domainInterface = domainInterface;
        domainInterface.addShopObserver(this);
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
        quantityCol.setCellValueFactory(data -> new SimpleIntegerProperty(((data.getValue().getValue() + 1))));

        productInfo.getColumns().addAll(nameCol, priceCol, quantityCol);
        domainInterface.getAllBasketArticles().forEach(this::addArticle);
        table.setItems(articles);
        populateArticles();
        table.getColumns().addAll(productInfo);

        totalPrice = new Label();
        totalPrice.setFont(new Font("Arial", 30));
        setTotalPrice(0.0);

        discountPrice = new Label();
        totalPrice.setFont(new Font("Arial", 30));
        setDiscountPrice(0.0);

        this.add(table, 0, 0);
        this.add(totalPrice, 0, 1);
        this.add(discountPrice, 0, 2);
    }

    public void populateArticles() {
        articles.clear();
        domainInterface.getAllBasketArticles().forEach(this::addArticle);
    }

    private void setTotalPrice(Double price) {
        totalPrice.setText(String.format("Total: €%.2f", price));
    }

    private void setDiscountPrice(Double price) {
        domainInterface.getBasketTotalPrice();
        if (domainInterface.getAllBasketArticles().size() != 0) {
            domainInterface.getShop().applyKorting();
        }

        System.out.println(domainInterface.getBasketTotalPrice());
        discountPrice.setText(String.format("DiscountPrice: €%.2f", price));

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
                    Article removedArticle = ((Pair<Integer, Article>) data).getValue();
                    articles.remove(removedArticle); break;
                case REMOVED_ARTICLE:
                    removeArticle((Article) data); break;
                case REMOVED_ARTICLE_INDICES:
                    Collection<Article> removedArticles = ((Pair<Collection<Integer>, Collection<Article>>) data).getValue();
                    removedArticles.forEach(this::removeArticle);
                    break;
                case TOTAL_PRICE_CHANGED:
                    setTotalPrice((Double) data);
                    setDiscountPrice((Double) data);
                    break;
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
