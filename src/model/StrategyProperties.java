package model;


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

    public static String getGroup() {
        return properties.getProperty("GROUP");
    }
    public static void setGroup(String value) {
        properties.setProperty("GROUP", value);
    }
    public static String getGroupDiscount() {
        return properties.getProperty("GDISCOUNT");
    }
    public static void setGroupDiscount(String value) {
        properties.setProperty("GDISCOUNT", value);
    }
    public static String getExpensiveDiscount() {
        return properties.getProperty("EXPDISCOUNT");
    }
    public static void setExpensiveDiscount(String value) {
        properties.setProperty("EXPDISCOUNT", value);
    }
    public static String getThreshold() {
        return properties.getProperty("THRESHPRICE");
    }
    public static void setThreshPrice(String value) {
        properties.setProperty("THRESHPRICE", value);
    }

    public static String getThresholdDiscount() {
        return properties.getProperty("THRESHDISCOUNT");
    }
    public static void setThreshDiscount(String value) {
        properties.setProperty("THRESHDISCOUNT", value);
    }


}
