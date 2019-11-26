package model;

import database.ArticleDbInMemory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
* @Author Jonas Berx
* @Version 1.0
* Created LoadSave Class from template Design pattern
*
* */


public class ArtikelLoadSave extends TekstLoadSaveTemplate implements LoadSaveStrategy {
    private File file;
    private ArticleDbInMemory dbInMemory = new ArticleDbInMemory();
    ArtikelLoadSave() {
        this.path = "src/bestanden/articles.txt";
        file = new File(path);
    }

    public ArtikelLoadSave(String path) {
        this.path = path;
        file = new File(path);
    }

    @Override
    public void load() {
        File toRead = new File(path);
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
                dbInMemory.addToMap(new Article(articleId, articleName, group, price, stock));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(ArrayList<Article> articles) {

        try {
            FileWriter writer = new FileWriter(file);
            StringBuilder data = new StringBuilder();
            //For testing purposes

            for (Article article : articles) {
                data.append(article.toString()).append("\n");
                System.out.println(article.toString());
            }
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
