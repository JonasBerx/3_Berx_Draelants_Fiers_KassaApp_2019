package model.basket;

import model.Util;
import model.article.Article;
import model.discount.DiscountContext;
import model.discount.DiscountFactory;
import model.discount.combined.CombinedDiscountContext;
import model.observer.Observer;

import java.util.*;
import java.util.stream.Collectors;

public class Basket implements model.observer.Observable {
    Map<Article, Integer> articleStacks = new HashMap<>(); // Maps article to # of articles in stack
    LinkedList<Observer> observers = new LinkedList<>();
    Map<Article, Double> discountedPrices;
    private DiscountContext discountContext;

    public Basket() {
        discountContext = DiscountFactory.fromProperties(this);

        if (discountContext.getDiscount() instanceof CombinedDiscountContext) {
            CombinedDiscountContext comb = ((CombinedDiscountContext) discountContext.getDiscount());
        }
    }

    public Map<Article, Integer> getArticleStacks() {
        return Collections.unmodifiableMap(articleStacks);
    }

    public Collection<Article> getAllUniqueArticles() {
        return Collections.unmodifiableSet(articleStacks.keySet());
    }

    public int getStackAmount(Article article) {
        return getArticleStacks().get(article);
    }

    public boolean contains(Article article) {
        return articleStacks.containsKey(article);
    }

    public void add(Article article) {
        add(article, 1);
    }

    public void add(Article article, int amountToAdd) {
        int amount = articleStacks.getOrDefault(article, 0);
        articleStacks.put(article, amount + amountToAdd);
        priceMutatingUpdate(BasketEvent.ADDED_ARTICLE, new BasketEventData(null, null, article));
    }

    public Collection<Article> getByGroup(String group) {
        return articleStacks.keySet().stream().filter(a -> a.getGroup().equals(group)).collect(Collectors.toList());
    }
//
//    public ArrayList<Article> getByCode(int code) {
//        Article article = articleAmounts.keySet().stream().filter(a -> a.getArticleCode() == code)
//        return article;
//    }

    public void remove(Article article) {
        removeAmount(article, 1);
    }

    public void removeAmount(Article article, int amountToRemove) {
        Integer amount = articleStacks.get(article);
        if (amount != null) {
            int newAmount = amount - amountToRemove;
            if (newAmount < 1) {
                articleStacks.remove(article);
            } else {
                articleStacks.put(article, newAmount);
            }
        }

        if (articleStacks.isEmpty())
            updateObservers(BasketEvent.REMOVED_LAST_ARTICLE, null);
    }

    public void removeAll(Map<Article, Integer> amountsToRemove) {
        amountsToRemove = new HashMap<>(amountsToRemove);
        amountsToRemove.forEach(this::removeAmount);
        BasketEventData data = new BasketEventData(Collections.unmodifiableMap(amountsToRemove), null, null);
        priceMutatingUpdate(BasketEvent.REMOVED_ARTICLES, data);
    }

    public void removeAll(Collection<Article> articles) {
        removeAll(Util.flatListToAmountMap(articles));
    }

    public void clear() {
        articleStacks.clear();
        priceMutatingUpdate(BasketEvent.CLEARED_ARTICLES, null);
        updateObservers(BasketEvent.REMOVED_LAST_ARTICLE, null);
    }

    public double getTotalPrice() {
        double sum = 0;
        for (Map.Entry<Article, Integer> entry : articleStacks.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    public double getTotalDiscountedPrice() {
        return discountedPrices.values().stream().mapToDouble(Double::new).sum();
    }

    public void updateDiscountedPrices() {
        this.discountedPrices = discountContext.getStackPrices();
        System.out.println(this.discountedPrices);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void priceMutatingUpdate(BasketEvent event, BasketEventData data) {
        updateDiscountedPrices();

        updateObservers(event, data);
        updateObservers(BasketEvent.TOTAL_PRICE_CHANGED, null);
    }

    private void updateObservers(BasketEvent event, BasketEventData data) {
        observers.forEach(observer -> observer.update(event, data));
    }
}
