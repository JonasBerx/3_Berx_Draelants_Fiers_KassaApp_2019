package model;

import model.article.Article;
import model.basket.Basket;
import model.log.Log;
import model.observer.Observer;
import model.shop.Shop;
import db.ArticleDbContext;

import java.io.IOException;
import java.util.*;

/**
 * @author the team
 */

public class DomainFacade {
    private final Shop shop;
    private final Log log;

    public DomainFacade() throws IOException {
        Prop.load();

        this.log = new Log();
        this.shop = new Shop(log);
    }

    public Log getLog() {
        return log;
    }

    public List<String> getLogItems() {
        return log.getItems();
    }

    public void addLogObserver(Observer observer) {
        log.addObserver(observer);
    }

    public Shop getShop() {
        return shop;
    }

    public ArticleDbContext getArticleDb() {
        return shop.getArticleDb();
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
    public Basket getBasket() {
        return shop.getBasket();
    }

    public void updateDiscountContext() {
        getBasket().updateDiscountContext();
    }

    public void addBasketObserver(Observer observer) {
        getBasket().addObserver(observer);
    }

    public void removeBasketObserver(Observer observer) {
        getBasket().removeObserver(observer);
    }

    public void closeBasket() {
        getBasket().close();
    }

    public void payBasket() {
        getBasket().pay();
    }

    public void cancelBasket() {
        getBasket().cancel();
    }

    public void addBasketArticle(Article article) {
        getBasket().add(article);
    }

    public Collection<Article> getAllUniqueBasketArticles() {
        return getBasket().getAllUniqueArticles();
    }

    public Map<Article, Integer> getBasketArticleStacks() {
        return getBasket().getArticleStacks();
    }

    public void removeBasketArticle(Article article) {
        getBasket().remove(article);
    }

    public void removeBasketArticles(Map<Article, Integer> articleAmountsToRemove) {
        getBasket().removeAll(articleAmountsToRemove);
    }

    public void removeBasketArticles(Collection<Article> articles) {
        getBasket().removeAll(articles);
    }

    public void clearBasketArticles() {
        getBasket().clear();
    }

    //Geeft prijs ZONDER toegepaste korting
    public double getBasketTotalPrice() {
        return getBasket().getTotalPrice();
    }

    //Geeft totale prijs MET korting toegepast
    public double getBasketDiscountedPrice() {
        return getBasket().getTotalDiscountedPrice();
    }
    //endregion
}
