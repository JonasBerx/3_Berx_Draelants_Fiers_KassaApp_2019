package model.observer;

/**
 * @author the team
 */

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}
