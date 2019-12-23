package db;

public enum ArticleDbType {
    INMEMORY(ArticleDbInMemory.class),
    ANOTHERONE(ArticleDbSQL.class);

    private final Class<? extends ArticleDbStrategy> articleDbClass;

    ArticleDbType(Class<? extends ArticleDbStrategy> articleDbClass) {
        this.articleDbClass = articleDbClass;
    }

    public Class<? extends ArticleDbStrategy> getArticleDbClass() {
        return this.articleDbClass;
    }
}
