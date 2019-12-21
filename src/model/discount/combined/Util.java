package model.discount.combined;

import model.article.Article;
import model.basket.Basket;
import model.discount.DiscountStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Util {
    public static double getCombinationAbsoluteDiscount(Basket basket, Map<Article, Double> prevStackPrices, List<DiscountStrategy> discounts) {
        Map<Article, Double> stackPrices = new HashMap<>(prevStackPrices);
        for (DiscountStrategy discount : discounts) {
            stackPrices = discount.getStackDiscounts(basket, stackPrices);
        }
        return stackPrices.values().stream().mapToDouble(Double::new).sum();
    }
}
