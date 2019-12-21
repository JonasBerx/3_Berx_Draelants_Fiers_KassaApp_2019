package model;

import model.article.Article;
import model.observer.Observer;
import model.properties.PropertiesOld;
import model.properties.Property;
import model.shop.Shop;
import newDatabase.ArticleDbContext;

import java.io.IOException;
import java.util.*;

public class DomainFacade {
    private final Shop shop;

    public DomainFacade() throws IOException {
        PropertiesOld.load();
        Property.load();

        this.shop = new Shop();
    }

    public Shop getShop() {
        return shop;
    }

    public ArticleDbContext getContext() {
        return shop.getContext();
    }

    public void putSaleOnHold() {
        shop.putSaleOnHold();
    }

    public void continueHeldSale() {
        shop.resumeSale();
    }

    public boolean saleIsOnHold() {
        return shop.saleIsOnHold();
    }

    public void addShopObserver(Observer observer) { shop.addObserver(observer); }

    public void removeShopObserver(Observer observer) { shop.removeObserver(observer); }

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

    public Collection<Article> getAllUniqueBasketArticles() {
        return shop.getBasket().getAllUniqueArticles();
    }

    public void removeBasketArticle(Article article) {
        shop.getBasket().remove(article);
    }

    public void removeBasketArticles(Map<Article, Integer> articleAmountsToRemove) {
        shop.getBasket().removeAll(articleAmountsToRemove);
    }

    public void removeBasketArticles(Collection<Article> articles) {
        shop.getBasket().removeAll(articles);
    }

    public void clearBasketArticles() {
        shop.getBasket().clear();
    }

    //Geeft prijs ZONDER toegepaste korting
    public double getBasketTotalPrice() {
        return shop.getBasket().getTotalPrice();
    }

    //Geeft totale prijs MET korting toegepast
    public double getBasketDiscountedPrice() {
        return shop.getBasket().getTotalDiscountedPrice();
    }
    //endregion
}
