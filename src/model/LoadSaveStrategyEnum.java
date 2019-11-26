package model;

import excel.ExcelPlugin;

public enum LoadSaveStrategyEnum {
    EXCEL(ExcelAdapter.class,"src/bestanden/articles.xls"),
    TXT(ArtikelLoadSave.class,"src/bestanden/articles.txt"),
    CSV( CsvLoadSave.class,"src/bestanden/articles.csv");

    private String path;
    private final Class aClass;
    LoadSaveStrategyEnum(Class aClass, String path) {
        this.path = path;
        this.aClass = aClass;
    }

    public String getThePath() {
        return path;
    }

    public Class getTheClass() {
        return this.aClass;
    }

}
