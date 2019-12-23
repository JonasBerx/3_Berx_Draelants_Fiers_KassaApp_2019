package model.discount;

import model.Prop;
import model.article.Article;
import model.basket.Basket;
import model.log.DummyLog;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GroupDiscountTest {
    public static double EPS = 0.01;

    private static GroupDiscount disc;
    private static Basket basket;
    private static Article gr1a;
    private static Article gr1b;
    private static Article gr2a;

    @BeforeAll
    public static void beforeAll() throws IOException {
        Prop.load();
    }

    @BeforeEach
    public void beforeEach() {
        disc = new GroupDiscount("gr1",0.2);
        basket = new Basket(new DummyLog());
        gr1a = new Article(1, "gr1a", "gr1", 40.0, 5);
        gr1b = new Article(2, "gr1b", "gr1", 5.0, 5);
        gr2a = new Article(2, "gr2a", "gr2", 18.0, 5);
    }

    @Test
    void getDiscountedStackPrices() {
        basket.add(gr1a);
        Map<Article, Double> prices = disc.getDiscountedStackPrices(basket, Collections.emptyMap());
        assertEquals(32, prices.get(gr1a), EPS);
    }

    @Test
    void getDiscountedStackPricesMultiple() {
        basket.add(gr1a);
        basket.add(gr1b);
        Map<Article, Double> prices = disc.getDiscountedStackPrices(basket, Collections.emptyMap());
        assertEquals(32, prices.get(gr1a), EPS);
        assertEquals(4, prices.get(gr1b), EPS);
    }

    @Test
    void getDiscountedStackPricesNonGroup() {
        basket.add(gr1a);
        basket.add(gr2a);
        Map<Article, Double> prices = disc.getDiscountedStackPrices(basket, Collections.emptyMap());
        assertEquals(32, prices.get(gr1a), EPS);
        if (prices.containsKey(gr2a))
            assertEquals(18, prices.get(gr2a), EPS);
    }

    @Test
    void getDiscountedStackPricesPrev() {
        basket.add(gr1a);
        Map<Article, Double> prev = new HashMap<>();
        prev.put(gr1a, 25.0);
        Map<Article, Double> prices = disc.getDiscountedStackPrices(basket, prev);
        assertEquals(20, prices.get(gr1a), EPS);
    }
}