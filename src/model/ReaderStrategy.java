package model;

import java.io.FileNotFoundException;
import java.io.IOException;


/*
* @Author Jonas Berx
* Created StrategyPattern
*
* */
public abstract class ReaderStrategy {
    String path;
    ReaderStrategy(){}

    public abstract void read(String filePath) throws FileNotFoundException;

    public abstract void write(String filePath) throws IOException;




}
