package model;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author Jonas Berx
 * @Version 1.0
 * Basic test for Strategy
 * */

public class writetest {
    public static void main(String[] args) throws IOException {

        LoadSaveStrategy loadSaveStrategy = new CsvLoadSave();
        LoadSaveStrategy tekstreader = new ArtikelLoadSave();
        LoadSaveStrategy adapter = new ExcelAdapter();

        ArrayList<Article> articles = new ArrayList<>();
        ArrayList<Article> excelArticles = new ArrayList<>();

        //Alle articles komen overeen behalve 1 en 7
        //Dis is fur testing purposes lmao
        Article excelArt1 = new Article(1, "artikel1Ex", "gr1", 12.50, 10);
        Article excelArt7 = new Article(1, "artikel7Ex", "gr1", 3.75, 10);

        Article article1 = new Article(1,"artikel1","gr1",12.5,10);
        Article article2 = new Article(2,"artikel2","gr1",22.4,10);
        Article article3 = new Article(3,"bartikel3","gr1",12.5,10);
        Article article4 = new Article(4,"artikel4","gr1",50.5,10);
        Article article5 = new Article(5,"artikel5","gr1",2.5,10);
        Article article6 = new Article(6,"bartikel6","gr1",2.5,10);
        Article article7 = new Article(7,"artikel7","gr1",3.75,10);
        Article article8 = new Article(8,"artikel8","gr2",18.5,10);
        Article article9 = new Article(9,"artikel9","gr2",114.5,10);
        Article article10 = new Article(10,"artikel10","gr2",66.25,10);

        //ExcelFiller
        excelArticles.add(excelArt1);
        excelArticles.add(article2);
        excelArticles.add(article3);
        excelArticles.add(article4);
        excelArticles.add(article5);
        excelArticles.add(article6);
        excelArticles.add(excelArt7);
        excelArticles.add(article8);
        excelArticles.add(article9);
        excelArticles.add(article10);



        //TXT&CSV Filler
        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);
        articles.add(article5);
        articles.add(article6);
        articles.add(article7);
        articles.add(article8);
        articles.add(article9);
        articles.add(article10);

//        Currently out of order - Implemented different strategy params
//
//        String path = "src/bestanden/articles.csv";
//        String pathTest = "src/bestanden/articlesTest.csv";
//        String txtpath = "src/bestanden/articles.txt";
//        File excel = new File("src/bestanden/articles.xls");





        /*
        * Tests for txt reader
        * */
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°\nTests for TXT Reader\n--------------------");

        tekstreader.load();

        tekstreader.save(articles);

        /*
         * Tests For csv reader
         * */
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°\nTests for CSV Reader\n---------------------------------");
        loadSaveStrategy.load();
        System.out.println(loadSaveStrategy.toString());
        loadSaveStrategy.save(articles);

        /*
        * Tests for the excel reader
        * !! Changed extension for compatibility
        * */
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°\nTests for Excel Adapter\n---------------------------");
        adapter.load();
        adapter.save(excelArticles);

    }
}
