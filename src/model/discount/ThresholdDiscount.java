package model.discount;

import model.article.Article;
import model.basket.Basket;
import model.properties.Properties;
import model.properties.Property;

import java.util.Collection;
import java.util.Map;

public class ThresholdDiscount implements DiscountStrategy {
    private double getDiscountedPrice(double price) {
        double discount = Properties.getDouble(Property.DISCOUNT_THRESHOLD_AMOUNT);
        return price * (100 - discount / 100);
    }

    private double getTotalPrice(Collection<Article> articles) {
        return articles.stream().mapToDouble(Article::getPrice).sum();
    }

    @Override
    public Map<Article, Double> getDiscountedPrices(Basket basket, Map<Article, Double> articlePrices) {
        Collection<Article> articles = articlePrices.keySet();
        double threshold = Properties.getDouble(Property.DISCOUNT_THRESHOLD_THRESHOLD);
        double totalPrice = getTotalPrice(articles);
        if (totalPrice >= threshold) {
            for (Article article : articles) {
                double oldPrice = articlePrices.get(article);
                double newPrice = getDiscountedPrice(oldPrice);
                articlePrices.put(article, newPrice);
            }
        }
        return articlePrices;
    }

    /*
    Old version

    @Override
    public ArrayList<Article> berekenKorting(Shop shop) {
        ArrayList<Article> drempels = new ArrayList<>();
        try{
            Properties.load();
            if (shop.getBasket().getTotalPrice() >= Double.parseDouble(Properties.getThresholdPrice())) {
                for (int i = 0; i < shop.getBasket().getAll().size(); i++) {
                    shop.getBasket().get(i).setPrice(shop.getBasket().get(i).getPrice() * (100 - Double.parseDouble(Properties.getExpensiveDiscount()))/100);
                    drempels.add(shop.getBasket().get(i));
                }
            }
            return drempels;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    */
}
