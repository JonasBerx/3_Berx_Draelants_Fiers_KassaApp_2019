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

    ArticleDbInMemory dbInMemory = new ArticleDbInMemory();
    public ArtikelLoadSave() {
        this.path = "src/bestanden/articles.txt";
    }

    public ArtikelLoadSave(String path) {
        this.path = path;
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
    public void save() {
        File toSave = new File(path);
        try {
            FileWriter writer = new FileWriter(toSave);
            String data = "";
            //For testing purposes

            for (Article article : dbInMemory.returnDb().values()) {
                data += article.toString()+"\n";
                System.out.println(article.toString());
            }
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
