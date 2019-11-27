package database;

import model.Article;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/*
* @Author Jonas Berx
* Created StrategyPattern
*
* */
public interface LoadSaveStrategy {

    void load() throws FileNotFoundException;

    void save(ArrayList<Article> articles) throws IOException;





}
