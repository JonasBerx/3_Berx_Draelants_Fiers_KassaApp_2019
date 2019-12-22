package model.shop;


import model.observer.Observable;
import model.observer.Observer;
import model.basket.Basket;
import model.properties.PropertiesOld;
import db.ArticleDbContext;

import java.util.LinkedList;

public class Shop implements Observable {
    LinkedList<Observer> observers = new LinkedList<>();
    private ArticleDbContext articleDb;
    private Basket basket;
    private Basket heldBasket; // "pause sale" functionality


    public Shop() {
        articleDb = new ArticleDbContext(PropertiesOld.getMemory());
        basket = new Basket();
    }

    public Basket getBasket() {
        return basket;
    }

    public void putSaleOnHold() {
        if (this.heldBasket != null)
            throw new IllegalStateException("There is already a sale on hold");

        this.heldBasket = basket;
        this.basket = new Basket();
        updateObservers(ShopEvent.PUT_SALE_ON_HOLD, new ShopEventData(heldBasket));
    }

    public ArticleDbContext getArticleDb() {
        return articleDb;
    }

    public void resumeSale() {
        if (this.heldBasket == null)
            throw new IllegalStateException("There is no sale on hold");

        Basket oldBasket = heldBasket;
        this.basket = heldBasket;
        this.heldBasket = null;
        updateObservers(ShopEvent.RESUMED_SALE, new ShopEventData(oldBasket));
    }

    public boolean saleIsOnHold() {
        return this.heldBasket != null;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void updateObservers(ShopEvent event, ShopEventData data) {
        observers.forEach(observer -> observer.update(event, data));
    }
}