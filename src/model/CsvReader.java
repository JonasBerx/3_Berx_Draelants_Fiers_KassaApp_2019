package model;

import database.ArticleDbInMemory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author Jonas Berx
 * @Version 1.0
 * Created Reader Class that implements Strategy
 *
 * */

public class CsvReader implements ReaderStrategy {
    private List<Article> articles = new ArrayList<>();
    ArticleDbInMemory dbInMemory = new ArticleDbInMemory();
    private File toRead;
    private File toSave;
    private Scanner scanner;
    private FileWriter fileWriter;
    @Override
    public void read(File file) throws FileNotFoundException {
        this.toRead = file;
        this.scanner = new Scanner(toRead);
        readFile();

        // For testing purposes only

//        for (Article article : articles) {
//            System.out.println(article.toString());
//        }


    }

    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public void write(File file) throws IOException {

        this.toSave = file;
        this.fileWriter = new FileWriter(toSave);

        String data = "";

        //TODO written for arraylist, change to hashmap later
        for (Article article : articles) {
            data += article.toString() + "\n";
        }

        fileWriter.write(data);
        fileWriter.close();
    }


    private void readFile() {
        while (scanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(scanner.nextLine().trim());
            lineScanner.useDelimiter(",");
            int articleId = Integer.parseInt(lineScanner.next());
            String articleName = lineScanner.next();
            String group = lineScanner.next();
            double price = Double.parseDouble(lineScanner.next());
            int stock = Integer.parseInt(lineScanner.next());
            articles.add(new Article(articleId, articleName, group, price, stock));
            dbInMemory.addToMap(new Article(articleId, articleName, group, price, stock));

        }
    }

    public String toString() {
        return dbInMemory.toString();
    }
}
