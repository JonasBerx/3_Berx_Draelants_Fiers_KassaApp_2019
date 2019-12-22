package controller.client;

import controller.BasketArticlesObserver;
import controller.ShopObserver;
import model.DomainFacade;
import model.article.Article;
import model.basket.Basket;
import model.observer.EventData;

import java.util.Map;

public class Overview implements BasketArticlesObserver, ShopObserver {
    private DomainFacade model;
    private view.jfx.client.Overview view;

    public Overview(DomainFacade model, view.jfx.client.Overview view) {
        this.model = model;
        this.view = view;

        populateArticles();

        model.addShopObserver(this);
        model.addBasketObserver(this);
    }

    private void populateArticles() {
        view.clearArticles();
        addArticles(model.getBasketArticleStacks());
    }

    public void addArticles(Map<Article, Integer> articleAmounts) {
        articleAmounts.forEach(view::addArticle);
    }

    @Override
    public void addArticle(Article article) {
        view.addArticle(article, 1);
    }

    @Override
    public void removeArticles(Article article, int amountToRemove) {
        view.removeArticle(article, amountToRemove);
    }

    @Override
    public void clearArticles() {
        view.clearArticles();
    }

    @Override
    public void updatePriceLabels() {
        view.setTotalPriceLbl(model.getBasketTotalPrice());
        view.setDiscountPriceLbl(model.getBasketDiscountedPrice());
    }

    @Override
    public void update(Enum<?> event, EventData data) {
        BasketArticlesObserver.super.update(event, data);
        ShopObserver.super.update(event, data);
    }

    @Override
    public void handleBasketSwitchEvent(Basket oldBasket) {
        oldBasket.removeObserver(this);
        updatePriceLabels();
    }
}
