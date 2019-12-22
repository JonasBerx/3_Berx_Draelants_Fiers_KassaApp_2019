package model.basket.state;

import model.basket.Basket;

public abstract class BasketState {
    private Basket basket;

    public BasketState(Basket basket) {
        this.basket = basket;
    }

    public Basket getBasket() {
        return basket;
    }

    public String getStateName() {
        String fullName = this.getClass().getName();
        return fullName.substring(0, fullName.length() - "state".length());
    }

    private UnsupportedOperationException getTransitionException(String action) {
        return new UnsupportedOperationException(String.format("Cannot %s a basket in the \"%s\" state.", action, getStateName()));
    }

    public void close() {
        throw getTransitionException("close");
    }

    public void pay() {
        throw getTransitionException("pay");
    }

    public void cancel() {
        throw getTransitionException("cancel");
    }
}
