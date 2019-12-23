package model.basket;

import model.Util;
import model.article.Article;
import model.basket.state.*;
import model.discount.DiscountContext;
import model.discount.DiscountFactory;
import model.discount.combined.CombinedDiscountContext;
import model.log.DummyLog;
import model.log.Log;
import model.observer.Observer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Pieter Fiers
 * Basket
 * */

public class Basket implements model.observer.Observable {
    Map<Article, Integer> articleStacks = new HashMap<>(); // Maps article to # of articles in stack
    LinkedList<Observer> observers = new LinkedList<>();
    Map<Article, Double> discountedPrices;
    private DiscountContext discountContext;
    private Log log;

    BasketState state;
    OpenState openState;
    ClosedState closedState;
    PayedState payedState;
    CancelledState cancelledState;

    public Basket(Log log) {
        this.log = log;

        openState = new OpenState(this);
        closedState = new ClosedState(this);
        payedState = new PayedState(this);
        cancelledState = new CancelledState(this);
        state = openState;

        updateDiscountContext();
    }

    public Log getLog() {
        return log;
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
        priceMutatingUpdate(BasketEvent.ADDED_ARTICLE, new BasketEventData(article));
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
        BasketEventData data = new BasketEventData(Collections.unmodifiableMap(amountsToRemove));
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

    public Map<Article, Double> getDiscountedPrices() {
        return discountedPrices;
    }

    public double getTotalDiscountedPrice() {
        if (discountedPrices == null)
            updateDiscountedPrices();
        return discountedPrices.values().stream().mapToDouble(Double::new).sum();
    }

    public void updateDiscountContext() {
        discountContext = DiscountFactory.fromProperties(this);
        updateObservers(BasketEvent.TOTAL_PRICE_CHANGED, null);
    }

    public void updateDiscountedPrices() {
        this.discountedPrices = discountContext.getStackPrices();
    }

    /*********
     * State *
     *********/
    //region State
    public BasketState getState() {
        return state;
    }

    public void setState(BasketState state) {
        BasketState oldState = getState();
        this.state = state;
        updateObservers(BasketEvent.STATE_CHANGED, new BasketEventData(oldState, state));
    }

    public void close() {
        state.close();
    }

    public void pay() {
        state.pay();
    }

    public void cancel() {
        state.cancel();
    }

    public OpenState getOpenState() {
        return openState;
    }

    public ClosedState getClosedState() {
        return closedState;
    }

    public PayedState getPayedState() {
        return payedState;
    }

    public CancelledState getCancelledState() {
        return cancelledState;
    }
    //endregion


    /************
     * Observer *
     ************/
    //region Observer
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
        for (Observer observer : observers) {
            observer.update(event, data);
        }
    }
    //endregion
}
