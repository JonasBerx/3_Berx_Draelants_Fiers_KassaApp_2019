package model.basket.state;

import model.basket.Basket;

/**
 * @author Pieter Fiers
 * States
 * */

public class CancelledState extends BasketState {
    public CancelledState(Basket basket) {
        super(basket);
    }
}
