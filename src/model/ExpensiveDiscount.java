package model;

import java.io.IOException;
import java.util.ArrayList;

public class ExpensiveDiscount implements KortingStrategy {
    int duurste = 25;

    @Override
    public ArrayList<Article> berekenKorting(Shop shop) {
        ArrayList<Article> expensive = new ArrayList<>();

        try {
            AppProperties.load();
            expensive.add(shop.getMostExpensive());
            for (Article article : expensive) {
                article.setPrice(article.getPrice() * (100 - Double.parseDouble(AppProperties.getExpensiveDiscount())) / 100);
            }
            return expensive;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return expensive;
    }
}
