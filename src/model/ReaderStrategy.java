package model;

import java.io.FileNotFoundException;

public abstract class ReaderStrategy {

    public abstract void read(String filePath) throws FileNotFoundException;

    public abstract void write(String filePath);


}
