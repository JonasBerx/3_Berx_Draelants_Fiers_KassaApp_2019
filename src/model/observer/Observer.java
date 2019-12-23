package model.observer;

/**
 * @author the team
 */

public interface Observer {
    /**
     * Push update.
     * EventData is needed when it's impossible to
     * use pull design because for example an item is
     * removed (and therefore impossible to pull).
     * @param event event
     * @param data push data
     */
    void update(Enum<?> event, EventData data);
}
