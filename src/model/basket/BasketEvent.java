package model.basket;


/**
 * @author Pieter Fiers
 * Basket
 * */
public enum BasketEvent {
    ADDED_ARTICLE,
    REMOVED_ARTICLES,
    REMOVED_LAST_ARTICLE,
    CLEARED_ARTICLES,
    TOTAL_PRICE_CHANGED,
    STATE_CHANGED;
}
