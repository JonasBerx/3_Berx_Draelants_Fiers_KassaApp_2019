package db;

import model.article.Article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author Jonas Berx
 * @version 1.0
 *
 * Class made to show the ability to implement different Database structures
 *
 **/
public class ArticleDbSQL implements ArticleDbStrategy {
    public ArticleDbSQL(String type) {
        throw new UnsupportedOperationException("Will not be implemented");
    }

    @Override
    public void add(Article article) {
        throw new UnsupportedOperationException("Will not be implemented");
    }

    @Override
    public void add(Collection<Article> articles) {
        throw new UnsupportedOperationException("Will not be implemented");
    }

    @Override
    public void remove(Article article) {
        throw new UnsupportedOperationException("Will not be implemented");
    }

    @Override
    public Article getArticel(int id) {
        throw new UnsupportedOperationException("Will not be implemented");
    }

    @Override
    public ArrayList<Article> getAll() {
        throw new UnsupportedOperationException("Will not be implemented");
    }

    @Override
    public HashMap<Integer, Article> returnDB() {
        throw new UnsupportedOperationException("Will not be implemented");
    }
}
