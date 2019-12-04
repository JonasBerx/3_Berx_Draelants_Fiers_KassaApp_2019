package model;


import newDatabase.ArticleDbContext;

import java.io.IOException;
import java.util.ArrayList;

public class Shop {
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
}
