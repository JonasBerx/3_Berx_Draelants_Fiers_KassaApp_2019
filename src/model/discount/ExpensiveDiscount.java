package model.discount;

import model.properties.Properties;
import model.article.Article;
import model.basket.Basket;
import model.properties.Property;

import java.util.Map;

public class ExpensiveDiscount implements DiscountStrategy {
    private double getDiscountedPrice(double price) {
        double discount = Properties.getDouble(Property.DISCOUNT_EXPENSIVE_AMOUNT);
        return price * (100 - discount / 100);
    }

    private Article getMostExpensive(Map<Article, Double> articlePrices) {
        Article mostExpensive = null;
        for (Map.Entry<Article, Double> price : articlePrices.entrySet()) {
            if (mostExpensive == null) {
                mostExpensive = price.getKey();
                continue;
            }

            if (price.getValue() > mostExpensive.getPrice()) {
                mostExpensive = price.getKey();
            }
        }
        return mostExpensive;
    }

    @Override
    public Map<Article, Double> getDiscountedPrices(Basket basket, Map<Article, Double> articlePrices) {
        Article mostExpensive = getMostExpensive(articlePrices);
        double oldPrice = articlePrices.get(mostExpensive);
        double newPrice = getDiscountedPrice(oldPrice);
        articlePrices.put(mostExpensive , newPrice);
        return articlePrices;
    }

    /*
    Old version

    @Override
    public ArrayList<Article> berekenKorting(Shop shop) {
        ArrayList<Article> expensive = new ArrayList<>();

        try {
            Properties.load();
            expensive.add(shop.getMostExpensive());
            for (Article article : expensive) {
                article.setPrice(article.getPrice() * (100 - Double.parseDouble(Properties.getExpensiveDiscount())) / 100);
            }
            return expensive;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return expensive;
    }
    */
}
