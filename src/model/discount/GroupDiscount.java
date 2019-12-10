package model.discount;

import model.basket.Basket;
import model.properties.Properties;
import model.Article;
import model.properties.Property;

import java.util.Collection;
import java.util.Map;


public class GroupDiscount implements DiscountStrategy {
    private double getDiscountedPrice(double price) {
        double discount = Properties.getDouble(Property.DISCOUNT_GROUP_AMOUNT);
        return price * (100 - discount / 100);
    }

    @Override
    public Map<Article, Double> getDiscountedPrices(Basket basket, Map<Article, Double> articlePrices) {
        Collection<Article> groupArticles = basket.getByGroup(Properties.getString(Property.DISCOUNT_GROUP_NUMBER));
        for (Article article : groupArticles) {
            double oldPrice = articlePrices.get(article);
            double newPrice = getDiscountedPrice(oldPrice);
            articlePrices.put(article, newPrice);
        }
        return articlePrices;
    }

    /*
    Old version

    @Override
    public ArrayList<Article> berekenKorting(Shop shop) {
        try {
            Properties.load();
            ArrayList<Article> group = shop.getByGroup(Properties.getGroup());
            if (shop.getBasket() != null) {

                for (Article article : group) {
                    article.setPrice(article.getPrice() * ((100 - Double.parseDouble(Properties.getGroupDiscount())) / 100));
                }
            }
            return group;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null; // Error bij verwerken - Still to implement

    }
    */
}
