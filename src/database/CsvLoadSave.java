package database;

import model.Article;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author Jonas Berx
 * @Version 1.0
 * Created Reader Class that implements Strategy
 *
 * */

public class CsvLoadSave implements LoadSaveStrategy {

    private ArticleDbInMemory dbInMemory;
    private File toRead;
    private File toSave;
    private Scanner scanner;
    private FileWriter fileWriter;

    public CsvLoadSave() {
        toRead = new File("src/bestanden/articles.csv");
        toSave = new File("src/bestanden/articlesTest.csv");
        dbInMemory = new ArticleDbInMemory();
    }

    @Override
    public void load() throws FileNotFoundException {

        this.scanner = new Scanner(toRead);
        readFile();

        // For testing purposes only

//        for (Article article : articles) {
//            System.out.println(article.toString());
//        }


    }

    public HashMap<Integer, Article> getMemory() {
        return dbInMemory.returnDb();
    }

    @Override
    public void save(ArrayList<Article> articles) throws IOException {

        this.fileWriter = new FileWriter(toSave);

        String data = "";


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
            dbInMemory.addToMap(new Article(articleId, articleName, group, price, stock));

        }
    }

    public String toString() {
        return dbInMemory.toString();
    }

}
