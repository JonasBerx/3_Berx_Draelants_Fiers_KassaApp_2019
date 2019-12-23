package db;


public abstract class LoadSaveFactory {
    public static LoadSaveStrategy fromType(LoadSaveStrategyEnum type) {
        try{
            return type.getLoadSaveClass().newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            String err = String.format("Cannot instantiate LoadSave type <%s>", type.name());
            throw new RuntimeException(err, e);
        }
    }
}
