package model.basket.state;

import model.basket.Basket;

/**
 * @author Pieter Fiers
 * States
 * */

public class OpenState extends BasketState {
    public OpenState(Basket basket) {
        super(basket);
    }

    @Override
    public void close() {
        if (getBasket().getAllUniqueArticles().size() == 0)
            throw new IllegalStateException("Cannot close a basket without articles.");

        getBasket().setState(getBasket().getClosedState());
    }
}
