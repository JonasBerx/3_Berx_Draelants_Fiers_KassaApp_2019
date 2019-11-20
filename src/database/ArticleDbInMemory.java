package database;

import model.Article;
import model.OverviewTemplate;
import org.omg.CORBA.ARG_IN;

import java.util.Collection;
import java.util.HashMap;

public class ArticleDbInMemory extends OverviewTemplate {

    private HashMap db;

    public ArticleDbInMemory() {
        db = new HashMap();
    }

    public ArticleDbInMemory(Collection<Article> article) {
        db = new HashMap();
    }

    public HashMap returnDb() {
        return db;
    }
}
