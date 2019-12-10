package model;

import java.io.IOException;
import java.util.ArrayList;

public class GroupDiscount implements KortingStrategy {
    @Override
    public ArrayList<Article> berekenKorting(Shop shop) {
        try {
            AppProperties.load();
            ArrayList<Article> group = shop.getByGroup(AppProperties.getGroup());
            if (shop.getBasket() != null) {

                for (Article article : group) {
                    article.setPrice(article.getPrice() * ((100 - Double.parseDouble(AppProperties.getGroupDiscount())) / 100));
                }
            }
            return group;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null; // Error bij verwerken - Still to implement

    }
}
