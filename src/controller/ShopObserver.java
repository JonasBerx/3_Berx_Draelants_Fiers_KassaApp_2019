package controller;

import model.basket.Basket;
import model.observer.EventData;
import model.observer.Observer;
import model.shop.ShopEvent;
import model.shop.ShopEventData;

public interface ShopObserver extends Observer {
    void handleBasketSwitchEvent(Basket oldBasket);

    @Override
    default void update(Enum<?> event, EventData data) {
        if (event instanceof ShopEvent) {
            ShopEvent shopEvent = (ShopEvent) event;
            ShopEventData shopEventData = (ShopEventData) data;
            switch (shopEvent) {
                case PUT_SALE_ON_HOLD:
                case RESUMED_SALE:
                case TRANSACTION_FINISHED:
                    handleBasketSwitchEvent(shopEventData.getOldBasket());
                    break;
            }
        }
    }
}
