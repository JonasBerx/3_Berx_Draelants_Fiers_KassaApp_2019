package model;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class writetest {
    public static void main(String[] args) throws FileNotFoundException {

        ReaderStrategy readerStrategy = new Reader();

        readerStrategy.read("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articles.csv");
        readerStrategy.write("C:\\Users\\JojoS\\Desktop\\2TI-BS\\OO - Ontwerpen\\KassaApplication\\src\\bestanden\\articlesTest.csv");
    }
}
