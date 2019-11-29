package model;

import database.LoadSaveStrategyEnum;

import java.io.*;
import java.util.Properties;

public class StrategyProperties {

    static InputStream inputStream;
    static Properties properties;
    static OutputStream outputStream;


    public static void load() throws IOException {
        try {
            properties = new Properties();

            inputStream = new FileInputStream("src/bestanden/config.properties");

            if (inputStream != null) {
                properties.load(inputStream);

            } else {
                throw new FileNotFoundException("Not found yeet");
            }


        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }

    public static void save() throws IOException {
        outputStream = new FileOutputStream("src/bestanden/config.properties");
        properties.store(outputStream,null);
    }

    public static String getStrategy() {

        return properties.getProperty("STRATEGY");
    }

    public static String getMemory() {
        return properties.getProperty("MEMORY");
    }

    public static void setMemory(String value) {
        properties.setProperty("MEMORY", value);
    }

    public static void setStrategy(String value) {
//        System.out.println(properties);
        properties.setProperty("STRATEGY", value);
    }
}
