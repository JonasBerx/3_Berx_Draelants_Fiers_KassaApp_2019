package model;

public class LoadSaveFactory {
    public LoadSaveStrategy create(String type) {
        LoadSaveStrategy strategy = null;
        LoadSaveStrategyEnum strategyEnum = LoadSaveStrategyEnum.valueOf(type.toUpperCase());
        String path = strategyEnum.getThePath();
        Class theClass = strategyEnum.getTheClass();

        try {
            Object loadSaveObject = theClass.newInstance();
            strategy = (LoadSaveStrategy) loadSaveObject;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return strategy;

    }
}
