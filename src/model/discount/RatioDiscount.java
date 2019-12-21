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

    public double getFullStackDiscountPrice(Basket basket, Map<Article, Double> prevStackPrices, Article article) {
        return Util.getStackPrice(basket, prevStackPrices, article) * (1 - getRatio());
    }

    public double getNArticleStackDiscountPrice(Basket basket, Map<Article, Double> prevStackPrices, Article article, int amount) {
        double singleArticlePrice = Util.getSingleArticlePrice(prevStackPrices, article);
        return (singleArticlePrice * (1 - getRatio()) * amount) + (singleArticlePrice * (1 - getRatio()) * basket.getStackAmount(article) - amount);
    }
}
