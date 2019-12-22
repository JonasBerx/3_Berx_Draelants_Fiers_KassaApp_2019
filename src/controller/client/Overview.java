package controller.client;

import model.DomainFacade;
import model.basket.Basket;
import model.basket.BasketEvent;
import model.basket.BasketEventData;
import model.observer.EventData;
import model.observer.Observer;
import model.shop.ShopEvent;
import model.shop.ShopEventData;

public class Overview implements Observer {
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
        view.addArticles(model.getBasketArticleStacks());
    }

    private void updatePriceLabels() {
        view.setTotalPriceLbl(model.getBasketTotalPrice());
        view.setDiscountPriceLbl(model.getBasketDiscountedPrice());
    }

    private void handleBasketSwitchEvent(Basket oldBasket) {
        oldBasket.removeObserver(this);
        updatePriceLabels();
    }

    @Override
    public void update(Enum<?> event, EventData data) {
        if (event instanceof BasketEvent) {
            BasketEvent basketEvent = (BasketEvent) event;
            BasketEventData basketEventData = (BasketEventData) data;
            switch (basketEvent) {
                case ADDED_ARTICLE:
                    view.addArticle(basketEventData.getAddedArticle());
                    break;
                case CLEARED_ARTICLES:
                    view.clearArticles();
                    break;
                case REMOVED_ARTICLES:
                    basketEventData.getRemovedArticles().forEach(view::removeArticles);
                    break;
                case REMOVED_ARTICLE:
                    view.removeArticle(basketEventData.getRemovedArticle());
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
