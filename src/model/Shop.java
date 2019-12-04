package model;


import newDatabase.ArticleDbContext;

import java.io.IOException;

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

    public ArticleDbContext getContext() {
        return context;
    }
}
