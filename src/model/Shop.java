package model;


import newDatabase.ArticleDbContext;
import newDatabase.LoadSaveFactory;
import newDatabase.LoadSaveStrategy;

import java.io.IOException;
import java.util.ArrayList;

public class Shop {
    private ArticleDbContext context;

    public Shop() {
        try {
            StrategyProperties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        context = new ArticleDbContext(StrategyProperties.getMemory());

    }

    public ArticleDbContext getContext() {
        return context;
    }


}
