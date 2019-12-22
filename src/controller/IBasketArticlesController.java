package controller;

import model.article.Article;
import model.basket.Basket;
import model.basket.BasketEvent;
import model.basket.BasketEventData;
import model.observer.EventData;
import model.observer.Observer;
import model.shop.ShopEvent;
import model.shop.ShopEventData;

public interface IBasketArticlesController extends Observer {
    void addArticle(Article article);
    void removeArticles(Article article, int amountToRemove);
    default void handleRemovedLastArticle() {};
    void clearArticles();
    void updatePriceLabels();
    void handleBasketSwitchEvent(Basket oldBasket);

    @Override
    default void update(Enum<?> event, EventData data) {
        if (event instanceof BasketEvent) {
            BasketEvent basketEvent = (BasketEvent) event;
            BasketEventData basketEventData = (BasketEventData) data;
            switch (basketEvent) {
                case ADDED_ARTICLE:
                    addArticle(basketEventData.getAddedArticle());
                    break;
                case CLEARED_ARTICLES:
                    clearArticles();
                    handleRemovedLastArticle();
                    break;
                case REMOVED_ARTICLES:
                    basketEventData.getRemovedArticles().forEach(this::removeArticles);
                    break;
                case REMOVED_LAST_ARTICLE:
                    handleRemovedLastArticle();
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
