package model.discount;

import model.Article;
import model.Shop;
import model.basket.Basket;

import java.util.ArrayList;
import java.util.Map;

public interface DiscountStrategy {
    Map<Article, Double> getDiscountedPrices(Basket basket, Map<Article, Double> articlePrices);
}
