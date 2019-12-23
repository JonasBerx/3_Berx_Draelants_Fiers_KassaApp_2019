package model.discount.combined;

import model.article.Article;
import model.basket.Basket;
import model.discount.DiscountStrategy;

import java.util.*;

/**
 * @author Pieter Fiers
 * @author Jonas Berx
 * */

public class HighestCombinedDiscount implements CombinedDiscountStrategy {
    @Override
    public List<DiscountStrategy> getDiscountCombination(Basket basket, Map<Article, Double> prevStackPrices, Collection<DiscountStrategy> discounts) {
        LowestCombinedDiscount lcomb = new LowestCombinedDiscount();
        List<DiscountStrategy> combination  = lcomb.getDiscountCombination(basket, prevStackPrices, discounts);
        Collections.reverse(combination);
        return combination;
    }
}
