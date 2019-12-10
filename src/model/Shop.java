package model;


import javafx.util.Pair;
import newDatabase.ArticleDbContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Shop implements Observable {
    LinkedList<Observer> observers = new LinkedList();
    private KortingContext kortingContext;
    private ArticleDbContext context;
    private Basket basket;
    private Basket heldBasket; // "pause sale" functionality


    public Shop() {
        try {
            StrategyProperties.load();
            context = new ArticleDbContext(StrategyProperties.getMemory());
            kortingContext = new KortingContext(StrategyProperties.getDiscounts());
            basket = new Basket();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<KortingStrategy> getKortingen() {
        System.out.println(kortingContext.getKortingen());
        return kortingContext.getKortingen();
    }

    public Basket getBasket() {
        return basket;
    }

    public void applyKorting() {
        for (int i = 0; i < getKortingen().size(); i++) {
            getKortingen().get(i).berekenKorting(this);
        }
    }

    public void putSaleOnHold() {
        if (this.heldBasket != null)
            throw new IllegalStateException("There is already a sale on hold");

        this.heldBasket = basket;
        this.basket = new Basket();
        updateObservers(ShopEvent.PUT_SALE_ON_HOLD, new Pair<>(heldBasket, basket));
    }

    public ArrayList<Article> getByGroup(String group) {
        ArrayList<Article> groupList = new ArrayList<>();
        for (int i = 0; i < basket.articles.size(); i++) {
            if (basket.articles.get(i).getGroup().equals(group)) {
                groupList.add(basket.articles.get(i));
            }
        }
        return groupList;
    }

    public ArrayList<Article> getByNumber(int artNumber) {
        ArrayList<Article> numberList = new ArrayList<>();
        for (int i = 0; i < basket.articles.size(); i++) {
            if (basket.articles.get(i).getArticleCode() == artNumber) {
                numberList.add(basket.articles.get(i));
            }
        }
        return numberList;
    }

    public Article getMostExpensive() {
        Article max = basket.get(0);
        for (int i = 0; i < basket.getAll().size(); i++) {


            if (basket.get(i).getPrice() > max.getPrice()) {
                max = basket.get(i);
            }
        }
        return max;
    }

    public ArticleDbContext getContext() {
        return context;
    }


    public void resumeSale() {
        if (this.heldBasket == null)
            throw new IllegalStateException("There is no sale on hold");

        this.basket = heldBasket;
        this.heldBasket = null;
        updateObservers(ShopEvent.RESUMED_SALE, basket);
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

    private void updateObservers(ShopEvent event, Object data) {
        observers.forEach(observer -> observer.update(event, data));
    }

    //No discount counted
    public double getTotalPrice() {
        return basket.getTotalPrice();
    }

    //Discounts added
    public double getDiscountedPrice() {
        this.applyKorting();
        return getTotalPrice();

    }
}