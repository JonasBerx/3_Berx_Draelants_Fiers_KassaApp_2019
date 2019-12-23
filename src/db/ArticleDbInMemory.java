package db;

import model.Prop;
import model.article.Article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author Jonas Berx
 * New Database
 * */

public class ArticleDbInMemory implements ArticleDbStrategy {
    private HashMap<Integer, Article> db;

    ArticleDbInMemory() {
        LoadSaveStrategy strategy = LoadSaveFactory.fromType(Prop.LOAD_SAVE_STRATEGY.asEnum(LoadSaveStrategyEnum.class));

        db = new HashMap<>();
        add(strategy.load());
    }

    @Override
    public void add(Article article) {
        if (article != null) {
            db.put(article.getCode(), article);
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
            db.remove(article.getCode());
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
