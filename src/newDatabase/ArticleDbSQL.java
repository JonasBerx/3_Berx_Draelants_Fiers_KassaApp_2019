package newDatabase;

import model.Article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author Jonas Berx
 * @version 1.0
 *
 * Class made to show the ability to implement different Database structures
 *
 * */

public class ArticleDbSQL implements DbStrategy {

    public ArticleDbSQL(String type) {
        // What if we add a new MYSQL database??

    }
    @Override
    public void add(Article article) {
        System.out.println("This will not be implemented");
    }

    @Override
    public void add(Collection<Article> articles) {
        System.out.println("This will not be implemented");
    }

    @Override
    public void remove(Article article) {
        System.out.println("This will not be implemented");
    }

    @Override
    public ArrayList<Article> getAll() {
        System.out.println("This will not be implemented");
        return new ArrayList<>();
    }

    @Override
    public HashMap<Integer, Article> returnDB() {
        System.out.println("This will not be implemented");
        return new HashMap<>();
    }
}
