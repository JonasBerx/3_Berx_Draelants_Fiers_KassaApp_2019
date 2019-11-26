package model;

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

        LoadSaveStrategy loadSaveStrategy = new CsvLoadSave();
        TekstLoadSaveTemplate tekstreader = new ArtikelLoadSave();
        ExcelAdapter adapter = new ExcelAdapter();

        String path = "src/bestanden/articles.csv";
        String pathTest = "src/bestanden/articlesTest.csv";
        String txtpath = "src/bestanden/articles.txt";
        File excel = new File("src/bestanden/articles.xls");




        /*
        * Tests for txt reader
        * */
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°\nTests for TXT Reader\n--------------------");

        tekstreader.load();
        tekstreader.save();

        /*
         * Tests For csv reader
         * */
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°\nTests for CSV Reader\n---------------------------------");
        loadSaveStrategy.load();
        System.out.println(loadSaveStrategy.toString());
        loadSaveStrategy.save();

        /*
        * Tests for the excel reader
        * !! Changed extension for compatibility
        * */
        System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°\nTests for Excel Adapter\n---------------------------");
        adapter.load();

    }
}
