package model;

import database.ArticleDbInMemory;
import excel.ExcelPlugin;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

/**
 * @Author Jonas Berx
 * @Version 1.0
 * Basic test for Strategy
 * */

public class writetest {
    public static void main(String[] args) throws IOException, BiffException {

        ReaderStrategy readerStrategy = new CsvReader();
        ExcelPlugin plugin = new ExcelPlugin();
        ExcelAdapter adapter = new ExcelAdapter(plugin);
        String path = "src/bestanden/articles.csv";
        String pathTest = "src/bestanden/articlesTest.csv";
        String txtpath = "src/bestanden/articles.txt";
        File excel = new File("src/bestanden/articles.xls");


        TekstLoadSaveTemplate tekstreader = new ArtikelLoadSave("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articles.txt");

        File csvRead = new File(path);
        File csvWrite = new File(pathTest);


        /*
        * Tests for txt reader
        * */

        tekstreader.path = txtpath;
        tekstreader.load();
        tekstreader.save();

        /*
         * Tests For csv reader
         * */
        readerStrategy.read(csvRead);
        System.out.println(readerStrategy.toString());
        readerStrategy.write(csvWrite);

        /*
        * Tests for the excel reader
        * !! Changed extension for compatibility
        * */
        adapter.read(excel);

    }
}
