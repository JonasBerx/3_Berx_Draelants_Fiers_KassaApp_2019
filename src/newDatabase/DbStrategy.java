package newDatabase;

import model.article.Article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface DbStrategy {

    void add(Article article);

    void add(Collection<Article> articles);

    void remove(Article article);

    Article getArticel(int id);

    ArrayList<Article> getAll();

    HashMap<Integer, Article> returnDB();

    String toString();

}
