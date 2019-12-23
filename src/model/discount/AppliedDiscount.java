package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.Map;

/**
 * @author the team
 */
public class AppliedDiscount {
    private final DiscountStrategy discount;
    private final Basket basket;
    private final Map<Article, Double> prevStackPrices;

    private Map<Article, Double> discountedStackPrices;
    private Map<Article, Double> stackDiscounts;

    public AppliedDiscount(DiscountStrategy discount, Basket basket, Map<Article, Double> prevStackPrices) {
        this.discount = discount;
        this.basket = basket;
        this.prevStackPrices = prevStackPrices;
    }

    public DiscountStrategy getDiscount() {
        return discount;
    }

    public Map<Article, Double> getDiscountedStackPrices() {
        if (discountedStackPrices == null) {
            discountedStackPrices = discount.getDiscountedStackPrices(basket, prevStackPrices);
        }

        return discountedStackPrices;
    }

    public Map<Article, Double> getStackDiscounts() {
        if (stackDiscounts == null) {
            stackDiscounts = discount.getStackDiscounts(basket, prevStackPrices);
        }

        return stackDiscounts;
    }
}
