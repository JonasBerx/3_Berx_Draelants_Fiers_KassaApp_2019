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
import model.article.Article;
import model.basket.Basket;
import model.basket.BasketEvent;
import model.basket.BasketEventData;
import model.observer.EventData;
import model.observer.Observer;
import model.shop.ShopEvent;
import model.shop.ShopEventData;

//TODO Create controller
public class ClientOverview extends GridPane implements Observer {
    private ObservableList<Pair<Article, Integer>> articles = FXCollections.observableArrayList();
    private Label totalPriceLbl;
    private DomainFacade domainFacade;
    private Label discountPriceLbl;

    public ClientOverview(DomainFacade domainFacade) {
        this.domainFacade = domainFacade;
        domainFacade.addShopObserver(this);
        domainFacade.addBasketObserver(this);

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
        domainFacade.getAllUniqueBasketArticles().forEach(this::addArticle);
        table.setItems(articles);
        populateArticles();
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

    public void populateArticles() {
        articles.clear();
        domainFacade.getAllUniqueBasketArticles().forEach(this::addArticle);
    }

    private void setTotalPriceLbl(Double price) {
        totalPriceLbl.setText(String.format("Total: €%.2f", price));
    }

    private void setDiscountPriceLbl(Double price) {
        System.out.println(domainFacade.getBasketTotalPrice());
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

    private void addArticle(Article article) {
        Pair<Article, Integer> existing = getPair(article);
        int count = 0;
        if (existing != null) {
            count = existing.getValue() + 1;
        }
        updatePair(existing, new Pair<>(article, count));
    }

    private void removeArticle(Article article) {
        removeArticles(article, 1);
    }

    private void removeArticles(Article article, int amountToRemove) {
        Pair<Article, Integer> pair = getPair(article);
        int amount = pair.getValue();
        articles.remove(pair);
        if (amountToRemove > amount)
            return;

        articles.add(new Pair<>(article, amount - amountToRemove));
    }

    private void handleBasketSwitchEvent(Basket oldBasket) {
        oldBasket.removeObserver(this);
        populateArticles();
        setTotalPriceLbl(domainFacade.getBasketTotalPrice());
    }

    private void updatePriceLabels() {
        setTotalPriceLbl(domainFacade.getBasketTotalPrice());
        setDiscountPriceLbl(domainFacade.getBasketDiscountedPrice());
    }

    @Override
    public void update(Enum<?> event, EventData data) {
        if (event instanceof BasketEvent) {
            BasketEvent basketEvent = (BasketEvent) event;
            BasketEventData basketEventData = (BasketEventData) data;
            switch (basketEvent) {
                case ADDED_ARTICLE:
                    addArticle(basketEventData.getAddedArticle());
                    break;
                case CLEARED_ARTICLES:
                    articles.clear();
                    break;
                case REMOVED_ARTICLES:
                    basketEventData.getRemovedArticles().forEach(this::removeArticles);
                    break;
                case REMOVED_ARTICLE:
                    removeArticle(basketEventData.getRemovedArticle());
                    break;
                case TOTAL_PRICE_CHANGED:
                    updatePriceLabels();
                    break;
            }
        } else if (event instanceof ShopEvent) {
            ShopEvent shopEvent = (ShopEvent) event;
            ShopEventData shopEventData = (ShopEventData) data;
            switch (shopEvent) {
                case PUT_SALE_ON_HOLD:
                case RESUMED_SALE:
                    handleBasketSwitchEvent(shopEventData.getOldBasket());
                    break;
            }
        }
    }
}
