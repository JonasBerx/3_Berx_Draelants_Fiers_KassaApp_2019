package model.discount.combined;

import model.article.Article;
import model.basket.Basket;
import model.discount.DiscountStrategy;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Pieter Fiers
 * @author Jonas Berx
 * */

public interface CombinedDiscountStrategy {
    List<DiscountStrategy> getDiscountCombination(Basket basket, Map<Article, Double> prevStackPrices, Collection<DiscountStrategy> discounts);
}
