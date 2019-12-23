package controller.cashier;

import model.DomainFacade;
import model.log.LogEvent;
import model.log.LogEventData;
import model.observer.EventData;
import model.observer.Observer;

public class Log implements Observer {
    private view.jfx.cashier.Log view;


    public Log(DomainFacade model, view.jfx.cashier.Log view) {
        this.view = view;

        model.addLogObserver(this);
    }

    @Override
    public void update(Enum<?> event, EventData data) {
        if (event instanceof LogEvent) {
            LogEventData logEventData = (LogEventData) data;
            view.addEntry(logEventData.getAddedItem());
        }
    }
}
