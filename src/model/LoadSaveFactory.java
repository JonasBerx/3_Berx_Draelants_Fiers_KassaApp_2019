package model;

public class LoadSaveFactory {
    public LoadSaveStrategy create(String type) {
        LoadSaveStrategy strategy = null;

        switch (type) {
            case "TEXT":
                strategy = (LoadSaveStrategy) new ArtikelLoadSave();
                break;
            case "EXCEL":
                strategy = new ExcelAdapter();
                break;
            case "CSV":
                strategy = new CsvLoadSave();
                break;
            default:
                strategy = (LoadSaveStrategy) new ArtikelLoadSave();
                break;
        }
        return strategy;

    }
}
