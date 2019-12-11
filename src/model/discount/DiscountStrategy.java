package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.Map;

public interface DiscountStrategy {
    Map<Article, Double> getDiscountedPrices(Basket basket, Map<Article, Double> articlePrices);
}
