package model.discount.combined;

import model.article.Article;
import model.basket.Basket;
import model.discount.DiscountStrategy;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pieter Fiers
 * @author Jonas Berx
 * */

public class CombinedDiscountContext implements DiscountStrategy {
    private Collection<DiscountStrategy> discounts;
    private CombinedDiscountStrategy combinator;


    public CombinedDiscountContext(Collection<DiscountStrategy> discounts, CombinedDiscountStrategy combinator) {
        this.discounts = discounts;
        this.combinator = combinator;
    }

    public Collection<DiscountStrategy> getDiscounts() {
        return discounts;
    }

    public CombinedDiscountStrategy getCombinator() {
        return combinator;
    }

    @Override
    public Map<Article, Double> getDiscountedStackPrices(Basket basket, Map<Article, Double> prevStackPrices) {
        List<DiscountStrategy> combinedDiscounts = getCombinator().getDiscountCombination(basket, prevStackPrices, getDiscounts());
        Map<Article, Double> stackPrices = new HashMap<>(prevStackPrices);
        for (DiscountStrategy discount : combinedDiscounts) {
            stackPrices = discount.getDiscountedStackPrices(basket, stackPrices);
        }
        return stackPrices;
    }
}
