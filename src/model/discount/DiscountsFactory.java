package model.discount;

import model.properties.Properties;
import model.properties.Property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class DiscountsFactory {
    Collection<DiscountStrategy> kortingen = new LinkedList<>();

    void create(ArrayList<String> types) {
        for (String t : types) {
            switch (t) {
                case "GROUP":
                    kortingen.add(new GroupDiscount());
                    break;
                case "THRESHOLD":
                    kortingen.add(new ThresholdDiscount());
                    break;
                case "EXPENSIVE":
                    kortingen.add(new ExpensiveDiscount());
                    break;
            }
        }
    }

    public static Collection<DiscountStrategy> fromTypes(Collection<DiscountType> types) {
        Collection<DiscountStrategy> discountStrategies = new LinkedList<>();
        types.forEach(type -> {
            try {
                discountStrategies.add(type.getDiscountTypeClass().newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return discountStrategies;
    }

    public Collection<DiscountStrategy> getKortingen() {
        return kortingen;
    }
}
