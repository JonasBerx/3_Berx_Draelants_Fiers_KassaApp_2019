package model;

import model.properties.Properties;
import newDatabase.ArticleDbContext;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class DomainInterface {
    private final Shop shop;

    public DomainInterface() throws IOException {
        Properties.load();

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

    public Article getBasketArticle(int index) {
        return shop.getBasket().get(index);
    }

    public Collection<Article> getAllBasketArticles() {
        return shop.getBasket().getAll();
    }

    public void removeBasketArticle(Article article) {
        shop.getBasket().remove(article);
    }

    public void removeBasketArticleIndex(int index) { shop.getBasket().removeIndex(index);}

    public void removeBasketArticles(Collection<Article> articles) {
        shop.getBasket().removeAll(articles);
    }

    public void removeBasketArticleIndices(List<Integer> indices) {
        shop.getBasket().removeIndices(indices);
    }

    public void clearBasketArticles() {
        shop.getBasket().clear();
    }

    public double getBasketTotalPrice() {
        return shop.getBasket().getTotalPrice();
    }

    public double getBasketDiscountedPrice() {
        return shop.getBasket().getTotalDiscountedPrice();
    }
    //endregion
}
