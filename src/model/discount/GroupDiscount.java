package model.discount;

import model.article.Article;
import model.basket.Basket;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GroupDiscount extends RatioDiscount {
    private String group;


    public GroupDiscount(String group, double ratio) {
        super(ratio);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public Map<Article, Double> getDiscountedStackPrices(Basket basket, Map<Article, Double> prevStackPrices) {
        Map<Article, Double> stackPrices = new HashMap<>();
        Collection<Article> applicable = basket.getByGroup(group);
        for (Article article : applicable) {
            stackPrices.put(article, getFullStackDiscountPrice(basket, prevStackPrices, article));
        }
        return stackPrices;
    }
}
