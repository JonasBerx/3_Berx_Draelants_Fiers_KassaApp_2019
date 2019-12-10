package model;


import java.io.*;
import java.util.ArrayList;
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
        return properties.getProperty("GROUPNUMBER");
    }
    public static void setGroup(String value) {
        properties.setProperty("GROUPNUMBER", value);
    }
    public static String getGroupDiscount() {
        return properties.getProperty("GROUPDISCOUNT");
    }
    public static void setGroupDiscount(String value) {
        properties.setProperty("GROUPDISCOUNT", value);
    }
    public static String getExpensiveDiscount() {
        return properties.getProperty("EXPENSIVEDISCOUNT");
    }
    public static void setExpensiveDiscount(String value) {
        properties.setProperty("EXPENSIVEDISCOUNT", value);
    }
    public static String getThresholdPrice() {
        return properties.getProperty("THRESHOLDPRICE");
    }


    public static void setThreshPrice(String value) {
        properties.setProperty("THRESHOLDPRICE", value);
    }

    public static String getThresholdDiscount() {
        return properties.getProperty("THRESHOLDDISCOUNT");
    }
    public static void setThreshDiscount(String value) {
        properties.setProperty("THRESHOLDDISCOUNT", value);
    }

    public static ArrayList<String> getDiscounts() {
        ArrayList<String> discounts = new ArrayList<>();
        if (properties.getProperty("EXPENSIVE").equals("true")) {
            discounts.add("EXPENSIVE");
        }
        if (properties.getProperty("GROUP").equals("true")) {
            discounts.add("GROUP");
        }
        if (properties.getProperty("THRESHOLD").equals("true")) {
            discounts.add("THRESHOLD");
        }
        return discounts;
    }


    public static void setDiscountGroup(boolean selected) {
        properties.setProperty("GROUP", String.valueOf(selected));
    }

    public static void setDiscountThreshold(boolean selected) {
        properties.setProperty("THRESHOLD", String.valueOf(selected));
    }

    public static void setDiscountExpensive(boolean selected) {
        properties.setProperty("EXPENSIVE", String.valueOf(selected));
    }

    public static boolean getDiscountGroup() {
        return Boolean.parseBoolean(properties.getProperty("GROUP"));
    }public static boolean getDiscountThreshold() {
        return Boolean.parseBoolean(properties.getProperty("THRESHOLD"));
    }public static boolean getDiscountExpensive() {
        return Boolean.parseBoolean(properties.getProperty("EXPENSIVE"));
    }
}
