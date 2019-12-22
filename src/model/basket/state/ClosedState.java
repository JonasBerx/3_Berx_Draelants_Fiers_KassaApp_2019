package model.basket.state;

import model.basket.Basket;

public class ClosedState extends BasketState {
    public ClosedState(Basket basket) {
        super(basket);
    }

    @Override
    public void cancel() {
        getBasket().setState(getBasket().getCancelledState());
    }

    @Override
    public void pay() {
        getBasket().setState(getBasket().getPayedState());
    }
}
