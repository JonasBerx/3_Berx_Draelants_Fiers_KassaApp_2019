package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/*
* @Author Jonas Berx
* Created StrategyPattern
*
* */
public interface LoadSaveStrategy {

    public abstract void read(File file) throws FileNotFoundException;

    public abstract void write(File file) throws IOException;




}
