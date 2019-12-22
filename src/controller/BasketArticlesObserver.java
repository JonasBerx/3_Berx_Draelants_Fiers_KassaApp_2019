package controller;

import model.article.Article;
import model.basket.BasketEvent;
import model.basket.BasketEventData;
import model.observer.EventData;
import model.observer.Observer;

public interface BasketArticlesObserver extends Observer {
    void addArticle(Article article);
    void removeArticles(Article article, int amountToRemove);
    default void handleRemovedLastArticle() {};
    void clearArticles();
    void updatePriceLabels();

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
        }
    }
}
