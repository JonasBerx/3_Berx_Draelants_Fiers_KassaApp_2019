package model;

import db.ArticleDbType;
import db.LoadSaveStrategyEnum;
import model.discount.combined.CombinedDiscountType;

import java.io.*;
import java.util.Properties;

public enum Prop {
    ARTICLE_DB(ArticleDbType.INMEMORY),
    LOAD_SAVE_STRATEGY(LoadSaveStrategyEnum.TXT),

    DISCOUNT_EXPENSIVE(true),
    DISCOUNT_EXPENSIVE_AMOUNT(10),
    DISCOUNT_GROUP(true),
    DISCOUNT_GROUP_NAME("gr1"),
    DISCOUNT_GROUP_AMOUNT(15),
    DISCOUNT_THRESHOLD(true),
    DISCOUNT_THRESHOLD_THRESHOLD(200),
    DISCOUNT_THRESHOLD_AMOUNT(5),
    DISCOUNTS_COMBINATION_TYPE(CombinedDiscountType.HIGHEST),

    RECEIPT_STACK_DISCOUNTED_PRICE(true),
    RECEIPT_STACK_COMBINED_PRICE(true),

    RECEIPT_HEADER_MESSAGE(true),
    RECEIPT_HEADER_MESSAGE_TXT("Welkom bij LTIAS shop!"),
    RECEIPT_HEADER_DATETIME(true),
    RECEIPT_FOOTER_DISCOUNT(true),
    RECEIPT_FOOTER_VAT(true),
    RECEIPT_FOOTER_MESSAGE(true),
    RECEIPT_FOOTER_MESSAGE_TXT("Bedankt voor uw vertrouwen in LTIAS!");

    private String defaultValue;
    private static java.util.Properties properties;
    static InputStream inputStream;
    static OutputStream outputStream;

    Prop(Object defaultValue) {
        String val = defaultValue.toString();

        if (defaultValue instanceof Enum) {
            val = ((Enum<?>)defaultValue).name();
        }

        this.defaultValue = val;
    }

    public String getDefaultValue() {
        return defaultValue;
    }


    //region Load / Save
    public static void load() throws IOException {
        try {
            properties = new Properties(getDefaults());
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

    public static Properties getDefaults() {
        Properties defaults = new Properties();
        for (Prop prop : values()) {
            defaults.setProperty(prop.name(), prop.getDefaultValue());
        }
        return defaults;
    }
    //endregion


    //region Getters / Setters
    public String get() {
        try {
            return properties.getProperty(name());
        } catch (NullPointerException ex) {
            throw new RuntimeException(String.format("Error getting property %s", name()), ex);
        }
    }

    public String asString() {
        return get();
    }

    public double asDouble() {
        return Double.parseDouble(get());
    }

    public int asInt() {
        return Integer.parseInt(get());
    }

    public boolean asBool() {
        return Boolean.parseBoolean(get());
    }

    public <E extends Enum<E>> E asEnum(Class<E> enumClass){
        return Enum.valueOf(enumClass, get());
    }

    public void set(String val) {
        properties.setProperty(name(), val);
    }

    public void set(Double value) {
        set(value.toString());
    }

    public void set(int value) {
        set(Integer.toString(value));
    }

    public void set(boolean value) {
        set(Boolean.toString(value));
    }

    public void set(Enum<?> value) {
        set(value.name());
    }
    //endregion
}
