package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.Collections;
import java.util.Map;

/**
 * Mock class
 */
public class NoDiscount implements DiscountStrategy {
    @Override
    public Map<Article, Double> getStackDiscounts(Basket basket, Map<Article, Double> prevStackPrices) {
        return Collections.emptyMap();
    }
}
