package model.log;

import javafx.beans.InvalidationListener;
import model.observer.Observable;
import model.observer.Observer;

import java.util.LinkedList;
import java.util.List;

public class Log implements Observable {
    List<String> items;
    LinkedList<Observer> observers;


    public Log() {
        items = new LinkedList<>();
        observers = new LinkedList<>();
    }

    public List<String> getItems() {
        return items;
    }

    public void log(String msg) {
        items.add(msg);
        updateObservers(LogEvent.ITEM_ADDED, new LogEventData(msg));
    }

    private void updateObservers(LogEvent event, LogEventData data) {
        for (Observer observer : observers) {
            observer.update(event, data);
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.add(observer);
    }
}
