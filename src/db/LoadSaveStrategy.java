package db;

import model.article.Article;

import java.util.ArrayList;

/**
 * @author Jonas Berx
 * New Database
 * */

public interface LoadSaveStrategy {

    ArrayList<Article> load();

    void save(ArrayList<Article> articles);
}
