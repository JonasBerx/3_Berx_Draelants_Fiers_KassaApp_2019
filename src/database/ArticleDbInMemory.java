package database;

import model.Article;
import model.ReaderStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

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
