package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.Map;

public abstract class RatioDiscount implements DiscountStrategy {
    private double ratio;

    public RatioDiscount(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }

    public double getPriceRatio() {
        return (1 - getRatio());
    }

    public double getFullStackDiscountPrice(Basket basket, Map<Article, Double> prevStackPrices, Article article) {
        return Util.getStackPrice(basket, prevStackPrices, article) * getPriceRatio();
    }

    public double getSingleArticleDisountStackPrice(Basket basket, Map<Article, Double> prevStackPrices, Article article) {
        return getNArticleDiscountStackPrice(basket, prevStackPrices, article, 1);
    }

    public double getNArticleDiscountStackPrice(Basket basket, Map<Article, Double> prevStackPrices, Article article, int amount) {
        double singleArticlePrice = Util.getSingleArticlePrice(prevStackPrices, article);
        return (singleArticlePrice * getPriceRatio() * amount) + (singleArticlePrice * getPriceRatio() * (basket.getStackAmount(article) - amount));
    }
}
