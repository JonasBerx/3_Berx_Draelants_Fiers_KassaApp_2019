package model;

import java.io.IOException;
import java.util.ArrayList;

public class GroupDiscount implements KortingStrategy {
    @Override
    public ArrayList<Article> berekenKorting(Shop shop) {
        try {
            StrategyProperties.load();
            ArrayList<Article> group = shop.getByGroup(StrategyProperties.getGroup());
            if (shop.getBasket() != null) {

                for (Article article : group) {
                    article.setPrice(article.getPrice() * ((100 - Double.parseDouble(StrategyProperties.getGroupDiscount())) / 100));
                }
            }
            return group;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null; // Error bij verwerken - Still to implement

    }
}
