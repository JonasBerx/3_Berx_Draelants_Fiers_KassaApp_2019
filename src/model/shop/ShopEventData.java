package model.shop;

import model.basket.Basket;
import model.observer.EventData;

public class ShopEventData extends EventData {
    private Basket oldBasket;

    public ShopEventData(Basket oldBasket) {
        this.oldBasket = oldBasket;
    }

    public Basket getOldBasket() {
        return oldBasket;
    }
}
