package model;


import newDatabase.LoadSaveFactory;
import newDatabase.LoadSaveStrategy;

import java.io.IOException;
import java.util.ArrayList;

public class Shop {
    private LoadSaveFactory factory = new LoadSaveFactory();
    private LoadSaveStrategy strategy;
    ArrayList<Article> articles;
//    public static void main(String[] args) throws IOException {
//
//
//    }


    public Shop() {
        try {
            StrategyProperties.load();
            strategy = factory.create(StrategyProperties.getStrategy());
            strategy.load();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
