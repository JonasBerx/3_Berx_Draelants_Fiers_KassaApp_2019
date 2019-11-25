package model;

import database.ArticleDbInMemory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * @Author Jonas Berx
 * Basic test for Strategy
 *
 * */

public class writetest {
    public static void main(String[] args) throws IOException {

        ReaderStrategy readerStrategy = new Reader();
        ReaderStrategy dbReader = new ArticleDbInMemory();

        /*
        * Test for the FileReader
        * */
        readerStrategy.read("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articles.csv");
        readerStrategy.write("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articlesTest.csv");

        /*
        * Tests for the DB in Memory
        * */
        dbReader.read("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articles.csv");
        dbReader.write("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articlesTest.csv");
    }
}
