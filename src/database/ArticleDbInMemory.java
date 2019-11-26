package database;

import model.Article;

import java.util.Collection;
import java.util.HashMap;

public class ArticleDbInMemory {

    private HashMap<Integer, Article> db;

    public ArticleDbInMemory() {
        db = new HashMap<Integer, Article>();
    }

    public ArticleDbInMemory(Collection<Article> article) {
        db = new HashMap<Integer, Article>();
    }

    public HashMap<Integer, Article> returnDb() {
        return db;
    }

    /**
     * @Author Jonas Berx
     * Implemented Strategy into Database
     *
     * */

    public void addToMap(Article article) {
        if (article != null) {
            db.put(article.getArticleCode(), article);
        }
    }

    public Article getArticle(int key) {
        return db.get(key);
    }




    public String toString() {
        String s = "";
        for (Article a : db.values())
            s += a.toString() + "\n";
        return s;
    }

}
