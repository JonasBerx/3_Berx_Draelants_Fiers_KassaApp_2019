package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.Collections;
import java.util.Map;

public class ThresholdDiscount extends FullBasketRatioDiscount {
    private double threshold;


    public ThresholdDiscount(double threshold, double ratio) {
        super(ratio);
        this.threshold = threshold;
    }

    public double getThreshold() {
        return threshold;
    }

    @Override
    public Map<Article, Double> getDiscountedStackPrices(Basket basket, Map<Article, Double> prevDiscountedStackPrices) {
        Map<Article, Double> stackPrices = Util.allStackPricesFromDiscountedStackPrices(basket, prevDiscountedStackPrices);
        double totalPrice = Util.sum(stackPrices.values());
        if (totalPrice < getThreshold())
            return Collections.emptyMap();

        return super.getDiscountedStackPrices(basket, prevDiscountedStackPrices);
    }
}
