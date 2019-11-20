package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader extends ReaderStrategy {
    private List<Article> articles = new ArrayList<>();
    private File toRead;
    private File toSave;
    private Scanner scanner;
    private FileWriter fileWriter;
    @Override
    public void read(String filePath) throws FileNotFoundException {
        this.toRead = new File(filePath);
        this.scanner = new Scanner(toRead);
        readFile();
    }

    @Override
    public void write(String filePath) throws IOException {

        this.toSave = new File(filePath);
        this.fileWriter = new FileWriter(toSave);
        fileWriter.write("test");
        fileWriter.close();
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
