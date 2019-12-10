package newDatabase;

import model.Article;
import model.properties.Properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ArticleDbInMemory implements DbStrategy {

    private LoadSaveFactory factory = new LoadSaveFactory();
    private LoadSaveStrategy strategy;


    private HashMap<Integer, Article> db;

    ArticleDbInMemory() {
        try {
            Properties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        strategy = factory.create(Properties.getLoader());
        System.out.println(Properties.getLoader());

        db = new HashMap<>();
        add(strategy.load());
    }

    @Override
    public void add(Article article) {
        if (article != null) {
            db.put(article.getArticleCode(), article);
        } else {
            throw new IllegalArgumentException("This is an empty Article!");
        }
    }

    @Override
    public void add(Collection<Article> articles) {
        if (articles.size() > 0) {
            for (Article a : articles) {
                add(a);
            }
        } else {
            throw new IllegalArgumentException("This list is empty!");
        }
    }

    @Override
    public void remove(Article article) {

        if (article != null) {
            db.remove(article.getArticleCode());
        } else {
            throw new IllegalArgumentException("This article can't be removed because it is empty!");
        }

    }

    @Override
    public Article getArticel(int id) {
        return db.get(id);
    }

    @Override
    public ArrayList<Article> getAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public HashMap<Integer, Article> returnDB() {
        return db;
    }
}
