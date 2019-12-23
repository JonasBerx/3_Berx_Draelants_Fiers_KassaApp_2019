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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ThresholdDiscountTest {
    public static double EPS = 0.01;

    private static ThresholdDiscount disc;
    private static Basket basket;
    private static Article cheap;
    private static Article exp;
    private static Article moderate;

    @BeforeAll
    public static void beforeAll() throws IOException {
        Prop.load();
    }

    @BeforeEach
    public void beforeEach() {
        disc = new ThresholdDiscount(20,0.2);
        basket = new Basket(new DummyLog());
        exp = new Article(1, "exp", "gr1", 40.0, 5);
        cheap = new Article(2, "cheap", "gr1", 5.0, 5);
        moderate = new Article(3, "moderate", "gr1", 18.0, 5);
    }

    @Test
    void getDiscountedStackPricesThresholdReached() {
        basket.add(exp);
        Map<Article, Double> prices = disc.getDiscountedStackPrices(basket, Collections.emptyMap());
        assertEquals(32, prices.get(exp), EPS);
    }

    @Test
    void getDiscountedStackPricesThresholdNotReached() {
        basket.add(cheap);
        Map<Article, Double> prices = disc.getDiscountedStackPrices(basket, Collections.emptyMap());
        if (prices.containsKey(cheap))
            assertEquals(5, prices.get(cheap), EPS);
    }

    @Test
    void getDiscountedStackPricesThresholdReachedMultiple() {
        basket.add(cheap);
        basket.add(moderate);
        Map<Article, Double> prices = disc.getDiscountedStackPrices(basket, Collections.emptyMap());
        assertEquals(4, prices.get(cheap), EPS);
        assertEquals(14.4, prices.get(moderate), EPS);
    }
}