package model;

import newDatabase.ArticleDbContext;

import java.util.Collection;

public class DomainInterface {
    private final Shop shop;

    public DomainInterface() {
        this.shop = new Shop();
    }

    public ArticleDbContext getContext() {
        return shop.getContext();
    }

    //region Basket
    public void addBasketObserver(Observer observer) {
        shop.getBasket().addObserver(observer);
    }

    public void removeBasketObserver(Observer observer) {
        shop.getBasket().removeObserver(observer);
    }

    public void addBasketArticle(Article article) {
        shop.getBasket().add(article);
    }

    public Collection<Article> getAllBasketArticles() {
        return shop.getBasket().getAll();
    }

    public void removeBasketArticle(Article article) {
        shop.getBasket().remove(article);
    }

    public void removeBasketArticles(Collection<Article> articles) {
        shop.getBasket().removeAll(articles);
    }

    public void clearBasketArticles() {
        shop.getBasket().clear();
    }

    public double getBasketTotalPrice() {
        return shop.getBasket().getTotalPrice();
    }
    //endregion
}
