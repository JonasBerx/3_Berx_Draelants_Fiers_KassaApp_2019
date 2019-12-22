package model.basket.state;

import model.basket.Basket;

public class OpenState extends BasketState {
    public OpenState(Basket basket) {
        super(basket);
    }

    @Override
    public void close() {
        getBasket().setState(getBasket().getClosedState());
    }
}
