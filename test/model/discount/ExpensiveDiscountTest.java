package model.discount;

import model.article.Article;
import model.basket.Basket;
import model.Prop;
import model.log.DummyLog;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExpensiveDiscountTest {
    public static double EPS = 0.01;

    private static ExpensiveDiscount disc;
    private static Basket basket;
    private static Article cheap;
    private static Article exp;

    @BeforeAll
    public static void beforeAll() throws IOException {
        Prop.load();
    }

    @BeforeEach
    public void beforeEach() {
        disc = new ExpensiveDiscount(0.2);
        basket = new Basket(new DummyLog());
        exp = new Article(1, "exp", "gr1", 40.0, 5);
        cheap = new Article(2, "cheap", "gr1", 5.0, 5);
    }

    @Test
    void getDiscountedStackPricesSingle() {
        basket.add(exp);
        Map<Article, Double> prices = disc.getDiscountedStackPrices(basket, Collections.emptyMap());
        assertEquals(32, prices.get(exp), EPS);
    }

    @Test
    void getDiscountedStackPricesTwo() {
        basket.add(exp);
        basket.add(cheap);
        Map<Article, Double> prices = disc.getDiscountedStackPrices(basket, Collections.emptyMap());
        assertFalse(prices.containsKey(cheap));
        assertEquals(32, prices.get(exp), EPS);
    }

    @Test
    void getDiscountedStackPricesPrev() {
        basket.add(exp);
        basket.add(cheap);
        Map<Article, Double> prev = new HashMap<>();
        prev.put(exp, 2.0);
        Map<Article, Double> prices = disc.getDiscountedStackPrices(basket, prev);
        System.out.println(prices);
        assertFalse(prices.containsKey(exp));
        assertEquals(4, prices.get(cheap), EPS);
    }
}