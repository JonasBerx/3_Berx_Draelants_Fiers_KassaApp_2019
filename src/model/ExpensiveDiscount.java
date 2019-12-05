package model;

import java.io.IOException;
import java.util.ArrayList;

public class ExpensiveDiscount implements KortingStrategy {
    int duurste = 25;

    @Override
    public ArrayList<Article> berekenKorting(Shop shop) {
        ArrayList<Article> expensive = new ArrayList<>();

        try {
            StrategyProperties.load();
            expensive.add(shop.getMostExpensive());
            for (int i = 0; i < expensive.size(); i++) {
                expensive.get(i).setPrice(expensive.get(i).getPrice() * (100- Double.parseDouble(StrategyProperties.getExpensiveDiscount()))/100);
            }
            return expensive;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return expensive;
    }
}
