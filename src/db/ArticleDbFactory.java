package db;

/**
 * @author Jonas Berx
 * New Database
 * */

abstract class ArticleDbFactory {
    public static ArticleDbStrategy fromType(ArticleDbType type) {
        try {
            return type.getArticleDbClass().newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            String err = String.format("Cannot instantiate ArticleDb type <%s>", type.name());
            throw new RuntimeException(err, e);
        }
    }
}
