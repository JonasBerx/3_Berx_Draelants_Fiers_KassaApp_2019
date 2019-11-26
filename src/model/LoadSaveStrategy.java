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

    void load() throws FileNotFoundException;

    void save() throws IOException;




}
