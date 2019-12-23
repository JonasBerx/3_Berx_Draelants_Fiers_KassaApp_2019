package model.shop;


import db.ArticleDbType;
import model.Prop;
import model.basket.BasketEvent;
import model.basket.BasketEventData;
import model.basket.state.BasketState;
import model.basket.state.CancelledState;
import model.basket.state.PayedState;
import model.log.Log;
import model.observer.EventData;
import model.observer.Observable;
import model.observer.Observer;
import model.basket.Basket;
import db.ArticleDbContext;

import java.util.LinkedList;

public class Shop implements Observable, Observer {
    LinkedList<Observer> observers = new LinkedList<>();
    private ArticleDbContext articleDb;
    private Basket basket;
    private Basket heldBasket; // "pause sale" functionality
    private Log log;


    public Shop(Log log) {
        articleDb = new ArticleDbContext(Prop.ARTICLE_DB.asEnum(ArticleDbType.class));
        basket = new Basket(log);
        basket.addObserver(this);
    }

    public Basket getBasket() {
        return basket;
    }

    public void putSaleOnHold() {
        if (this.heldBasket != null)
            throw new IllegalStateException("There is already a sale on hold");

        this.heldBasket = basket;
        this.basket = new Basket(log);
        updateBasketObserver(heldBasket, basket);
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
        updateBasketObserver(oldBasket, basket);
        updateObservers(ShopEvent.RESUMED_SALE, new ShopEventData(oldBasket));
    }

    private void updateBasketObserver(Basket oldBasket, Basket newBasket) {
        if (heldBasket != null)
            heldBasket.removeObserver(this);
        basket.addObserver(this);
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

    @Override
    public void update(Enum<?> event, EventData data) {
        if (event instanceof BasketEvent) {
            BasketEvent basketEvent = (BasketEvent) event;
            BasketEventData basketEventData = (BasketEventData) data;
            if (basketEvent == BasketEvent.STATE_CHANGED) {
                BasketState newState = basketEventData.getNewState();
                boolean payed = newState instanceof PayedState;
                boolean cancelled = newState instanceof CancelledState;
                if (payed || cancelled) {
                    Basket oldBasket = getBasket();
                    basket = new Basket(log);
                    updateObservers(ShopEvent.TRANSACTION_FINISHED, new ShopEventData(oldBasket));
                    if (payed)
                        updateObservers(ShopEvent.ARTICLE_STOCK_CHANGED, null);
                }
            }
        }
    }
}