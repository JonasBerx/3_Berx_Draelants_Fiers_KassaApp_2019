package model;

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

    ExcelPlugin plugin;


    public ExcelAdapter(ExcelPlugin newPlugin) {
        plugin = newPlugin;

    }

    @Override
    public void read(File file) throws IOException, BiffException {
        plugin.read(file);
    }

    @Override
    public void write(File file) throws IOException {
        plugin.write(file);
    }
}
