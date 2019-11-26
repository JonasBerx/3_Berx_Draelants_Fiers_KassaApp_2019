package model;

import database.ArticleDbInMemory;
import excel.ExcelPlugin;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
* @Author Jonas Berx
* @Version 1.0
* */
public class ExcelAdapter implements LoadSaveStrategy {
    ArticleDbInMemory dbInMemory;
    ExcelPlugin plugin;
    File articles;



    public ExcelAdapter() {
        plugin = new ExcelPlugin();
        dbInMemory = new ArticleDbInMemory();
        articles = new File("src/bestanden/articles.xls");


    }

    @Override
    public void load() {
        try {

            for (ArrayList<String> a : plugin.read(articles)) {
                System.out.println(a);
            }
        } catch (BiffException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() throws IOException {

    }



}
