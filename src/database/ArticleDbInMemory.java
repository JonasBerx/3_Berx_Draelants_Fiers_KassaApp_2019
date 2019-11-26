package database;

import model.Article;
import model.ReaderStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class ArticleDbInMemory extends ReaderStrategy {

    private HashMap<Integer, Article> db;

    public ArticleDbInMemory() {
        db = new HashMap<Integer, Article>();
    }

    public ArticleDbInMemory(Collection<Article> article) {
        db = new HashMap<Integer, Article>();
    }

    public HashMap<Integer, Article> returnDb() {
        return db;
    }

    /*
    * @Author Jonas Berx
    * Implemented Strategy into Database
    *
    * */
    @Override
    public void read(String filePath) throws FileNotFoundException {
        File toRead = new File(filePath);
        Scanner scanner = new Scanner(toRead);
        while (scanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(scanner.nextLine());
            lineScanner.useDelimiter(",");
            int articleId = Integer.parseInt(lineScanner.next());
            String articleName = lineScanner.next();
            String group = lineScanner.next();
            double price = Double.parseDouble(lineScanner.next());
            int stock = Integer.parseInt(lineScanner.next());
            db.put(articleId, new Article(articleId, articleName, group, price, stock));



        }
        /*
        * For testing purpose only
        * for (Article article : db.values()) {
        *   System.out.println(article.toString());
        * }
        * */


    }

    /*
    * Should change this, probably bad implent
    * */
    @Override
    public void write(String filePath) throws IOException {
        String data = "";
        FileWriter writer = new FileWriter(filePath);
        for (Article article : db.values()) {
            data += article.toString() + "\n";
        }
        writer.write(data);
        writer.close();

    }

    public String toString() {
        String s = "";
        for (Article a : db.values())
            s += a.toString() + "\n";
        return s;
    }

}
