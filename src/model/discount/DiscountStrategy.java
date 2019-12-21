package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementations should override either getStackPrices() or getStackDiscounts()
 */
public interface DiscountStrategy {
    static Map<Article, Double> discountedStackPricesFromStackDiscounts(Basket basket, Map<Article, Double> prevStackPrices, Map<Article, Double> stackDisounts) {
        Map<Article, Double> stackPrices = new HashMap<>(prevStackPrices);
        for (Map.Entry<Article, Double> articleEntry : stackDisounts.entrySet()) {
            Article article = articleEntry.getKey();
            Double discount = articleEntry.getValue();
            Double price = Util.getStackPrice(basket, prevStackPrices, article);
            stackPrices.put(article, price - discount);
        }
        return stackPrices;
    }

    /**
     * Same as {@link DiscountStrategy#discountedStackPricesFromStackDiscounts} because:
     * €8 - €2 (discount) = €6
     * €8 - €6 (discounted price) = €2
     */
    static Map<Article, Double> stackDiscountsFromDisountedPrices(Basket basket, Map<Article, Double> prevStackPrices, Map<Article, Double> discountedPrices) {
        return discountedStackPricesFromStackDiscounts(basket, prevStackPrices, discountedPrices);
    }

    default Map<Article, Double> getDiscountedStackPrices(Basket basket, Map<Article, Double> prevStackPrices) {
        return discountedStackPricesFromStackDiscounts(basket, prevStackPrices, getStackDiscounts(basket, prevStackPrices));
    }

    // Infinite recursion must be solved by implementing class
    default Map<Article, Double> getStackDiscounts(Basket basket, Map<Article, Double> prevStackPrices) {
        return discountedStackPricesFromStackDiscounts(basket, prevStackPrices, getDiscountedStackPrices(basket, prevStackPrices));
    }
}
