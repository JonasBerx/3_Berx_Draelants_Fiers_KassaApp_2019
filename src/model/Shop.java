package model;


import newDatabase.ArticleDbContext;
import newDatabase.LoadSaveFactory;
import newDatabase.LoadSaveStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Shop {
    private ArticleDbContext context;
    private Basket basket;

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

    public ArticleDbContext getContext() {
        return context;
    }
}
