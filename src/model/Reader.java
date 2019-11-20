package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader extends ReaderStrategy {
    List<Article> articles = new ArrayList<>();
    private File toRead;
    private File toSave;
    private Scanner scanner;
    @Override
    public void read(String filePath) throws FileNotFoundException {
        this.toRead = new File(filePath);
        this.scanner = new Scanner(toRead);
        readFile();
    }

    @Override
    public void write(String filePath) {

        File toSave = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(toSave);
            fileWriter.write("test");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    private void readFile() {
        while (scanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(scanner.nextLine());
            lineScanner.useDelimiter(",");
            int articleId = Integer.parseInt(lineScanner.next());
            String articleName = lineScanner.next();
            String group = lineScanner.next();
            double price = Double.parseDouble(lineScanner.next());
            int stock = Integer.parseInt(lineScanner.next());
            articles.add(new Article(articleId, articleName, group, price, stock));

        }
    }
}
