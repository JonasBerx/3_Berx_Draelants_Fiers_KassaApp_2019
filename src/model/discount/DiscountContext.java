package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DiscountContext {
    Collection<DiscountStrategy> discounts;
    private DiscountsFactory factory = new DiscountsFactory();

    public DiscountContext(Collection<DiscountType> type) {
        factory.fromTypes(type);
        discounts = factory.getKortingen();
    }

    private Map<Article, Double> getBasePrices(Collection<Article> articles) {
        Map<Article, Double> prices = new HashMap<>();
        articles.forEach(a -> prices.put(a, a.getPrice()));
        return prices;
    }

    public Map<Article, Double> getDiscountedPrices(Basket basket) {
        Map<Article, Double> prices = getBasePrices(basket.getAll());
        for (DiscountStrategy discount : discounts) {
            discount.getDiscountedPrices(basket, prices);
        }
        return prices;
    }
}
