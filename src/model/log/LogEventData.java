package model.log;

import model.observer.EventData;

public class LogEventData extends EventData {
    private String addedItem;

    public LogEventData(String addedItem) {
        this.addedItem = addedItem;
    }

    public String getAddedItem() {
        return addedItem;
    }
}
