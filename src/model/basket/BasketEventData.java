package model.basket;

import model.article.Article;
import model.observer.EventData;

import java.util.Map;

public class BasketEventData extends EventData {
    private Map<Article, Integer> removedArticles;
    private Article removedArticle;
    private Article addedArticle;


    public BasketEventData(Map<Article, Integer> removedArticles, Article removedArticle, Article addedArticle) {
        this.removedArticles = removedArticles;
        this.removedArticle = removedArticle;
        this.addedArticle = addedArticle;
    }

    public Map<Article, Integer> getRemovedArticles() {
        return removedArticles;
    }

    public Article getRemovedArticle() {
        return removedArticle;
    }

    public Article getAddedArticle() {
        return addedArticle;
    }
}
