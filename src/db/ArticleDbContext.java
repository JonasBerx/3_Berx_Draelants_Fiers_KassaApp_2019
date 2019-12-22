package db;

import model.article.Article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ArticleDbContext {
    private ArticleDbFactory factory = new ArticleDbFactory();
    private DbStrategy strategy;

    public ArticleDbContext(String type) {
       strategy = factory.create(type);
    }


    public void add(Article article) {
        strategy.add(article);
    }

    public void remove(Article article) {
        strategy.remove(article);
    }

    public void add(Collection<Article> articles) {
        strategy.add(articles);
    }

    public Article get(int id) {
        return strategy.getArticel(id);
    }

    public ArrayList<Article> getAll() {
        return strategy.getAll();
    }

    public HashMap<Integer, Article> returnDb() {
        return strategy.returnDB();
    }
}
