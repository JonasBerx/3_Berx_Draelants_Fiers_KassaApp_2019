package model.discount.combined;

import model.Util;
import model.article.Article;
import model.basket.Basket;
import model.discount.DiscountStrategy;

import java.util.*;

/**
 * @author Pieter Fiers
 * @author Jonas Berx
 * */

public class SingleLowestCombinedDiscount implements CombinedDiscountStrategy {
    @Override
    public List<DiscountStrategy> getDiscountCombination(Basket basket, Map<Article, Double> prevStackPrices, Collection<DiscountStrategy> discounts) {
        TreeMap<Double, DiscountStrategy> absoluteDiscounts = new TreeMap<>();
        for (DiscountStrategy discount : discounts) {
            double absoluteDiscount = Util.sum(discount.getDiscountedStackPrices(basket, prevStackPrices).values());
            absoluteDiscounts.put(absoluteDiscount, discount);
        }
        return Collections.singletonList(absoluteDiscounts.firstEntry().getValue());
    }
}
