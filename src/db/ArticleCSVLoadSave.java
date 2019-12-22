package db;

import model.article.Article;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArticleCSVLoadSave extends TekstLoadSaveTemplate {
    private File file;


    public ArticleCSVLoadSave() {
        super("src/bestanden/articles.csv");

    }

    public ArticleCSVLoadSave(String path) {
        super(path);
        file = new File(path);
        articles = new ArrayList<>();
    }

    @Override
    public ArrayList<Article> load() {
        File toRead = new File(getPath());
        try {
            Scanner sc = new Scanner(toRead);
            while (sc.hasNextLine()) {
                Scanner lineScanner = new Scanner(sc.nextLine().trim());
                lineScanner.useDelimiter(",");
                int articleId = Integer.parseInt(lineScanner.next());
                String articleName = lineScanner.next();
                String group = lineScanner.next();
                double price = Double.parseDouble(lineScanner.next());
                int stock = Integer.parseInt(lineScanner.next());

                articles.add(new Article(articleId, articleName, group, price, stock));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public void save(ArrayList<Article> articles) {

        try {
            FileWriter writer = new FileWriter(file);
            StringBuilder data = new StringBuilder();
            //For testing purposes

            for (Article article : articles) {
                data.append(article.toPrettyString()).append("\n");
            }
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Article> getItems() {
        return articles;
    }
}
