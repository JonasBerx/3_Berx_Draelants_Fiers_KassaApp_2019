package database;

import model.Article;

import java.util.Collection;
import java.util.HashMap;

public class ArticleDbInMemory {

    private HashMap<Integer, Article> db;

    public ArticleDbInMemory() {
        db = new HashMap<>();
    }

    public ArticleDbInMemory(Collection<Article> article) {
        db = new HashMap<>();
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


    public void addArticles(Collection<Article> articles) {
        int i;
        for (Article a : articles) {
            i = a.getArticleCode();
            db.put(i, a);
        }

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Article a : db.values())
            s.append(a.toString()).append("\n");
        return s.toString();
    }

}
