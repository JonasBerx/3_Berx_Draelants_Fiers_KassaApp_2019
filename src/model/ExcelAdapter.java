package model;

import database.ArticleDbInMemory;
import excel.ExcelPlugin;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
* @Author Jonas Berx
* @Version 1.0
* */
public class ExcelAdapter implements ReaderStrategy {
    ArticleDbInMemory dbInMemory;
    ExcelPlugin plugin;


    public ExcelAdapter(ExcelPlugin newPlugin) {
        plugin = newPlugin;
        dbInMemory = new ArticleDbInMemory();

    }

    @Override
    public void read(File file) {
        try {
            plugin.read(file);
            for (ArrayList<String> a : plugin.read(file)) {
                System.out.println(a);
            }
        } catch (BiffException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(File file) throws IOException {
//        plugin.write(file);
    }



}
