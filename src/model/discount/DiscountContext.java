package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.Collections;
import java.util.Map;
/**
 * @author the team
 */

public class DiscountContext {
    private DiscountStrategy discount;
    private Basket basket;


    public DiscountContext(DiscountStrategy discount, Basket basket) {
        this.discount = discount;
        this.basket = basket;
    }

    public Map<Article, Double> getStackPrices() {
        return Util.allStackPricesFromDiscountedStackPrices(basket, getDiscountedStackPrices());
    }

    public Map<Article, Double> getDiscountedStackPrices() {
        return discount.getDiscountedStackPrices(getBasket(), Collections.emptyMap());
    }

    public Map<Article, Double> getStackDiscounts() {
        return discount.getStackDiscounts(getBasket(), Collections.emptyMap());
    }

    public DiscountStrategy getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountStrategy discount) {
        this.discount = discount;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
