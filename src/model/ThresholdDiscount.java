package model;

import java.io.IOException;
import java.util.ArrayList;

public class ThresholdDiscount implements KortingStrategy {
    int drempel = 100;

    @Override
    public ArrayList<Article> berekenKorting(Shop shop) {
        ArrayList<Article> drempels = new ArrayList<>();
        try{
            StrategyProperties.load();
            if (shop.getBasket().getTotalPrice() >= Double.parseDouble(StrategyProperties.getThresholdPrice())) {
                for (int i = 0; i < shop.getBasket().getAll().size(); i++) {
                    shop.getBasket().get(i).setPrice(shop.getBasket().get(i).getPrice() * (100 - Double.parseDouble(StrategyProperties.getExpensiveDiscount()))/100);
                    drempels.add(shop.getBasket().get(i));
                }
            }
            return drempels;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
