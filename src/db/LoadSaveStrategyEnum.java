package db;


public enum LoadSaveStrategyEnum {
    EXCEL(ArticleExcelLoadSave.class),
    TXT(ArticleTekstLoadSave.class),
    CSV( ArticleCSVLoadSave.class);


    private final Class<? extends LoadSaveStrategy> loadSaveClass;

    LoadSaveStrategyEnum(Class<? extends LoadSaveStrategy> loadSaveClass) {
        this.loadSaveClass = loadSaveClass;
    }

    public Class<? extends LoadSaveStrategy> getLoadSaveClass() {
        return this.loadSaveClass;
    }
}
