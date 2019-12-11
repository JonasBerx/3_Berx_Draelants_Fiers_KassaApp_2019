package newDatabase;

import model.article.Article;

import java.io.File;
import java.util.ArrayList;

public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy{
    private String path;
    public File file;
    public ArrayList<Article> articles;

   public abstract ArrayList<Article> load();
   public abstract void save(ArrayList<Article> articles);

    TekstLoadSaveTemplate() {
        this.path = "src/bestanden/articles.txt";

        articles = new ArrayList<>();
    }


    TekstLoadSaveTemplate(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty path");
        }
        this.path = path;
        articles = new ArrayList<>();

    }



    String getPath() {
        return this.path;
    }




}
