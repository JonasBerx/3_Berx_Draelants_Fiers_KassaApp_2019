package model;

import database.ArticleDbInMemory;
import excel.ExcelPlugin;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


/**
* @Author Jonas Berx
* @Version 1.1
* */
public class ExcelAdapter implements LoadSaveStrategy {
    private ArticleDbInMemory dbInMemory;
    private ExcelPlugin plugin;
    private File articles;



    ExcelAdapter() {
        plugin = new ExcelPlugin();
        dbInMemory = new ArticleDbInMemory();
        articles = new File("src/bestanden/articles.xls");


    }

    @Override
    public void load() {
        try {
            for (ArrayList<String> a : plugin.read(articles)) {
                //For testing purpose
                System.out.println(a.get(0) + "," + a.get(1) + "," + a.get(2) + "," + a.get(3) + "," + a.get(4));

                Article newArticle = new Article(Integer.parseInt(a.get(0)),
                        a.get(1), a.get(2), Double.parseDouble(a.get(3)), Integer.parseInt(a.get(4)));
                dbInMemory.addToMap(newArticle);

            }
        } catch (BiffException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() throws IOException {

    }



}
