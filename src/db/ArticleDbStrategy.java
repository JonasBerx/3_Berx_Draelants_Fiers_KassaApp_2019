package db;

import model.article.Article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author Jonas Berx
 * New Database
 * */

public interface ArticleDbStrategy {

    void add(Article article);

    void add(Collection<Article> articles);

    void remove(Article article);

    Article getArticel(int id);

    ArrayList<Article> getAll();

    HashMap<Integer, Article> returnDB();

    String toString();

}
