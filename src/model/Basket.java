package model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Basket implements Observable {
    LinkedList<Article> articles = new LinkedList<>();
    LinkedList<Observer> observers = new LinkedList();

    public Collection<Article> getAll() {
        return Collections.unmodifiableList(articles);
    }

    public void add(Article article) {
        articles.add(article);
        priceMutatingUpdate(BasketEvent.ADDED_ARTICLE, article);
    }

    public void remove(Article article) {
        articles.remove(article);
        priceMutatingUpdate(BasketEvent.REMOVED_ARTICLE, article);
    }

    public void removeAll(Collection<Article> articles) {
        this.articles.removeAll(articles);
        priceMutatingUpdate(BasketEvent.REMOVED_ALL_ARTICLES, Collections.unmodifiableCollection(articles));
    }

    public void clear() {
        articles.clear();
        priceMutatingUpdate(BasketEvent.CLEARED_ARTICLES, null);
    }

    public double getTotalPrice() {
        return articles.stream().mapToDouble(Article::getPrice).sum();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void priceMutatingUpdate(BasketEvent event, Object data) {
        updateObservers(event, data);
        updateObservers(BasketEvent.TOTAL_PRICE_CHANGED, getTotalPrice());
    }

    private void updateObservers(BasketEvent event, Object data) {
        observers.forEach(observer -> observer.update(event, data));
    }
}
