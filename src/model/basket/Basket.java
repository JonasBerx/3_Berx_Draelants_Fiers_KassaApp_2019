package model.basket;

import javafx.util.Pair;
import model.article.Article;
import model.observer.Observer;
import model.discount.DiscountContext;
import model.properties.Properties;

import java.util.*;

public class Basket implements model.observer.Observable {
    ArrayList<Article> articles = new ArrayList<>();
    LinkedList<Observer> observers = new LinkedList();
    Map<Article, Double> discountedPrices;
    private DiscountContext discountContext;

    public Basket() {
        discountContext = new DiscountContext(Properties.getDiscountTypes());
    }

    public Collection<Article> getAll() {
        return Collections.unmodifiableList(articles);
    }

    public void add(Article article) {
        articles.add(article);
        priceMutatingUpdate(BasketEvent.ADDED_ARTICLE, article);
    }

    public Article get(int index) {
        return articles.get(index);
    }

    public ArrayList<Article> getByGroup(String group) {
        ArrayList<Article> groupList = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getGroup().equals(group)) {
                groupList.add(articles.get(i));
            }
        }
        return groupList;
    }

    public ArrayList<Article> getByNumber(int artNumber) {
        ArrayList<Article> numberList = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getArticleCode() == artNumber) {
                numberList.add(articles.get(i));
            }
        }
        return numberList;
    }

    public void remove(Article article) {
        articles.remove(article);
        priceMutatingUpdate(BasketEvent.REMOVED_ARTICLE, article);
    }

    public void removeIndex(int index) {
        Article removed = articles.get(index);
        articles.remove(index);
        priceMutatingUpdate(BasketEvent.REMOVED_ARTICLE_INDEX, new Pair<Article, Integer>(removed, index));
    }

    public void removeAll(Collection<Article> articles) {
        this.articles.removeAll(articles);
        priceMutatingUpdate(BasketEvent.REMOVED_ARTICLES, Collections.unmodifiableCollection(new LinkedList<Article>(articles)));
    }

    public void removeIndices(List<Integer> indices) {
        indices = new ArrayList<>(indices);
        indices.sort(Comparator.reverseOrder());
        List<Article> removed = new LinkedList<>();
        for (int i : indices) {
            removed.add(articles.get(i));
            articles.remove(i);
        }
        Pair<List<Integer>, List<Article>> eventData = new Pair<>(Collections.unmodifiableList(indices), Collections.unmodifiableList(removed));
        priceMutatingUpdate(BasketEvent.REMOVED_ARTICLE_INDICES, eventData);
    }

    public void clear() {
        articles.clear();
        priceMutatingUpdate(BasketEvent.CLEARED_ARTICLES, null);
    }

    public double getTotalPrice() {
        return articles.stream().mapToDouble(Article::getPrice).sum();
    }

    public double getTotalDiscountedPrice() {
        return discountedPrices.values().stream().mapToDouble(Double::new).sum();
    }

    public void updateDiscountedPrices() {
        this.discountedPrices = discountContext.getDiscountedPrices(this);
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
        updateDiscountedPrices();

        updateObservers(event, data);
        updateObservers(BasketEvent.TOTAL_PRICE_CHANGED, new Pair<Double, Double>(getTotalPrice(), getTotalDiscountedPrice()));
    }

    private void updateObservers(BasketEvent event, Object data) {
        observers.forEach(observer -> observer.update(event, data));
    }
}
