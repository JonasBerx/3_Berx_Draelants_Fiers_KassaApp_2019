package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author the team
 */

public abstract class Util {
    public static double getSingleArticlePrice(Map<Article, Double> discountedStackPrices, Article article) {
        Double stackPrice = discountedStackPrices.get(article);
        if (stackPrice == null)
            stackPrice = article.getPrice();

        return stackPrice;
    }

    public static double getNArticlesPrice(Map<Article, Double> discountedStackPrices, Article article, int amount) {
        return getSingleArticlePrice(discountedStackPrices, article) * amount;
    }

    public static double getStackPrice(Basket basket, Map<Article, Double> discountedStackPrices, Article article) {
        return getNArticlesPrice(discountedStackPrices, article, basket.getStackAmount(article));
    }

    public static Map<Article, Double> allStackPricesFromDiscountedStackPrices(Basket basket, Map<Article, Double> discountedStackPrices) {
        Map<Article, Double> stackPrices = new HashMap<>();
        for (Article article : basket.getAllUniqueArticles()) {
            stackPrices.put(article, getStackPrice(basket, discountedStackPrices, article));
        }
        return stackPrices;
    }

    public static double sum(Collection<Double> vals) {
        return vals.stream().mapToDouble(Double::new).sum();
    }
}
