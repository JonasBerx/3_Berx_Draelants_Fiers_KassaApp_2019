package model.properties;


import model.discount.combined.CombinedDiscountType;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;

public class PropertiesOld {
    static InputStream inputStream;
    static java.util.Properties properties;
    static OutputStream outputStream;

    public static void load() throws IOException {
        try {
            properties = new java.util.Properties(getDefaults());
            inputStream = new FileInputStream("src/bestanden/config.properties");
            properties.load(inputStream);
        } finally {
            inputStream.close();
        }
    }

    public static void save() throws IOException {
        outputStream = new FileOutputStream("src/bestanden/config.properties");
        properties.store(outputStream, null);
    }

    public static java.util.Properties getDefaults() {
        java.util.Properties defaults = new java.util.Properties();
        for (Property property : Property.values()) {
            defaults.setProperty(property.name(), property.getDefaultValue());
        }
        return defaults;
    }

    public static String getLoader() {

        return properties.getProperty("LOADER");
    }

    public static String getMemory() {
        return properties.getProperty("MEMORY");
    }

    public static void setMemory(String value) {
        properties.setProperty("MEMORY", value);
    }

    public static void setLoader(String value) {
//        System.out.println(properties);
        properties.setProperty("LOADER", value);
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

    //Property Getters for Receipt
    public static String getHeaderMessage() {
        return properties.getProperty("HEADERMESSAGE");
    }

    public static boolean getHeaderMesssageState() {
        return Boolean.parseBoolean(properties.getProperty("HEADERMESSAGESTATE"));
    }

    public static boolean getHeaderDateTime() {
        return Boolean.parseBoolean(properties.getProperty("HEADERDATETIME"));
    }

    public static boolean getFooterPriceDiscountSeparate() {
        return Boolean.parseBoolean(properties.getProperty("FOOTERPRICEDISCOUNTSEPARATE"));
    }

    public static boolean getFooterBtwSeparate() {
        return Boolean.parseBoolean(properties.getProperty("FOOTERBTWSEPARATE"));
    }

    public static boolean getFooterClosure() {
        return Boolean.parseBoolean(properties.getProperty("FOOTERCLOSURE"));
    }

    //Property Setters for Receipt
    public static void setHeaderMessage(String txt) {
        properties.setProperty("HEADERMESSAGE", txt);
    }

    public static void setHeaderMesssageState(boolean b) {
        properties.setProperty("HEADERMESSAGESTATE", String.valueOf(b));
    }

    public static void setHeaderDateTime(boolean b) {
        properties.setProperty("HEADERDATETIME", String.valueOf(b));
    }

    public static void setFooterPriceDiscountSeparate(boolean b) {
        properties.setProperty("FOOTERPRICEDISCOUNTSEPARATE", String.valueOf(b));
    }

    public static void setFooterBtwSeparate(boolean b) {
        properties.setProperty("FOOTERBTWSEPARATE", String.valueOf(b));
    }

    public static void setFooterClosure(boolean b) {
        properties.setProperty("FOOTERCLOSURE", String.valueOf(b));
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
}
