package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class ReaderStrategy {

    public abstract void read(String filePath) throws FileNotFoundException;

    public abstract void write(String filePath) throws IOException;


}
