package model;

import database.ArticleDbInMemory;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

/**
 * @Author Jonas Berx
 * @Version 1.0
 * Basic test for Strategy
 *
 * */

public class writetest {
    public static void main(String[] args) throws IOException, BiffException {

        ReaderStrategy readerStrategy = new CsvReader();


        TekstLoadSaveTemplate tekstreader = new ArtikelLoadSave("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articles.txt");

        File csvRead = new File("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articles.csv");
        File csvWrite = new File("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articlesTest.csv");

        tekstreader.load();
        tekstreader.save();
        /*
        * Test for the FileReader
        * */
        readerStrategy.read(csvRead);
        readerStrategy.write(csvWrite);

        /*
        * Tests for the DB in Memory
        * */
//        dbReader.read("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articles.csv");
//        dbReader.write("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articlesTest.csv");
    }
}
