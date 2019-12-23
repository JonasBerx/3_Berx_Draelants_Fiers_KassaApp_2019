package controller.cashier;

import model.DomainFacade;
import model.observer.EventData;
import model.observer.Observer;
import model.shop.ShopEvent;

public class ArticlesOverview implements Observer {
    private view.jfx.cashier.ArticlesOverview view;
    private DomainFacade model;

    public ArticlesOverview(DomainFacade model, view.jfx.cashier.ArticlesOverview view) {
        this.view = view;
        this.model = model;

        model.addShopObserver(this);
        populate();
    }

    private void populate() {
        view.populateArticles(model.getArticleDb().getAll());
    }

    @Override
    public void update(Enum<?> event, EventData data) {
        if (event instanceof ShopEvent) {
            ShopEvent shopEvent = (ShopEvent) event;
            if (shopEvent == ShopEvent.ARTICLE_STOCK_CHANGED) {
                populate();
            }
        }
    }
}
