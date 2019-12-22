package db;

public enum ArticleDbEnum {
    INMEMORY(ArticleDbInMemory.class),
    ANOTHERONE(ArticleDbSQL.class);

    private final Class memoryClass;

    ArticleDbEnum(Class memoryClass) {
        this.memoryClass = memoryClass;
    }

    public Class getMemoryClass() {
        return this.memoryClass;
    }
}
