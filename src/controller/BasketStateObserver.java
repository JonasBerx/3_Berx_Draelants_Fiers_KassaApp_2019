package controller;

import model.basket.Basket;
import model.basket.BasketEvent;
import model.basket.BasketEventData;
import model.basket.state.BasketState;
import model.observer.EventData;
import model.observer.Observer;

public interface BasketStateObserver extends Observer {
    void handleStateChangeEvent(BasketState oldState, BasketState newState);

    @Override
    default void update(Enum<?> event, EventData data) {
        if (event instanceof BasketEvent) {
            BasketEvent basketEvent = (BasketEvent) event;
            BasketEventData basketEventData = (BasketEventData) data;
            switch (basketEvent) {
                case STATE_CHANGED:
                    handleStateChangeEvent(basketEventData.getOldState(), basketEventData.getNewState());
            }
        }
    }
}
