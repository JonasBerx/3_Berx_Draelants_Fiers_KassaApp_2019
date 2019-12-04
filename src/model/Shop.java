package model;


import javafx.util.Pair;
import newDatabase.ArticleDbContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Shop implements Observable {
    LinkedList<Observer> observers = new LinkedList();
    private ArticleDbContext context;
    private Basket basket;
    private Basket heldBasket; // "pause sale" functionality

    public Shop() {
        try {
            StrategyProperties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        context = new ArticleDbContext(StrategyProperties.getMemory());

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
        updateObservers(ShopEvent.PUT_SALE_ON_HOLD, new Pair<Basket, Basket>(heldBasket, basket));
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

    public String getReceipt() {
        String receipt = "";
        receipt += String.format("Omschrijving       Aantal   Prijs(€)%n");
        receipt += String.format("************************************%n");
        for (Article a : basket.articles) {
            receipt += String.format("%-12s       %6d    %6.2f%n", a.getArticleName(), a.getQuantity(), a.getPrice());
        }
        receipt += String.format("************************************%n");
        receipt += String.format("Betaald (inclusief korting) : %.2f€", basket.getTotalPrice());
        return receipt;
    }


}
