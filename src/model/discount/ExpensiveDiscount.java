package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.HashMap;
import java.util.Map;

public class ExpensiveDiscount extends RatioDiscount {
    public ExpensiveDiscount(double ratio) {
        super(ratio);
    }

    private Article getMostExpensive(Basket basket, Map<Article, Double> prevStackPrices) {
        Article mostExpArt = null;
        double highestPrice = 0.0;
        for (Article article : basket.getAllUniqueArticles()) {
            if (mostExpArt == null) {
                mostExpArt = article;
            }

            double price = getFullStackDiscountPrice(basket, prevStackPrices, article);
            if (price > highestPrice) {
                mostExpArt = article;
                highestPrice = price;
            }
        }
        return mostExpArt;
    }

    @Override
    public Map<Article, Double> getDiscountedStackPrices(Basket basket, Map<Article, Double> prevStackPrices) {
        Article mostExpesive = getMostExpensive(basket, prevStackPrices);
        double price = getNArticleStackDiscountPrice(basket, prevStackPrices, mostExpesive, 1);
        Map<Article, Double> stackPrices = new HashMap<>(prevStackPrices);
        stackPrices.put(mostExpesive, price);
        return stackPrices;
    }
}
