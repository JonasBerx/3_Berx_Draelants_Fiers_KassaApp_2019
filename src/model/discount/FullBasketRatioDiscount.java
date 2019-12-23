package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.Map;

/**
 * @author the team
 */

public class FullBasketRatioDiscount extends RatioDiscount {
    public FullBasketRatioDiscount(double ratio) {
        super(ratio);
    }

    @Override
    public Map<Article, Double> getDiscountedStackPrices(Basket basket, Map<Article, Double> prevDiscountedStackPrices) {
        Map<Article, Double> stackPrices = Util.allStackPricesFromDiscountedStackPrices(basket, prevDiscountedStackPrices);
        for (Map.Entry<Article, Double> entry : stackPrices.entrySet()) {
            stackPrices.put(entry.getKey(), entry.getValue() * getPriceRatio());
        }
        return stackPrices;
    }
}
