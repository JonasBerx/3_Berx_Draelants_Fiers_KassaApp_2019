package model.discount.combined;

import model.article.Article;
import model.basket.Basket;
import model.discount.DiscountStrategy;

import java.util.*;

public class LowestCombinedDiscount implements CombinedDiscountStrategy {
    @Override
    public List<DiscountStrategy> getDiscountCombination(Basket basket, Map<Article, Double> prevStackPrices, Collection<DiscountStrategy> discounts) {
        TreeMap<Double, List<DiscountStrategy>> absoluteDiscounts = new TreeMap<>();
        List<List<DiscountStrategy>> combinations = model.Util.generatePerm(new ArrayList<>(discounts));
        for (List<DiscountStrategy> combination : combinations) {
            double absoluteDiscount = Util.getCombinationAbsoluteDiscount(basket, prevStackPrices, combination);
            absoluteDiscounts.put(absoluteDiscount, combination);
        }
        return absoluteDiscounts.firstEntry().getValue();
    }
}
