package model.basket;

import model.article.Article;
import model.basket.state.BasketState;
import model.observer.EventData;

import java.util.Map;

public class BasketEventData extends EventData {
    private Map<Article, Integer> removedArticles;
    private Article addedArticle;
    private BasketState oldState;
    private BasketState newState;


    public BasketEventData(Map<Article, Integer> removedArticles) {
        this.removedArticles = removedArticles;
    }

    public BasketEventData(Article addedArticle) {
        this.addedArticle = addedArticle;
    }

    public BasketEventData(BasketState oldState, BasketState newState) {
        this.oldState = oldState;
        this.newState = newState;
    }

    public Map<Article, Integer> getRemovedArticles() {
        return removedArticles;
    }

    public Article getAddedArticle() {
        return addedArticle;
    }

    public BasketState getOldState() {
        return oldState;
    }

    public BasketState getNewState() {
        return newState;
    }
}
