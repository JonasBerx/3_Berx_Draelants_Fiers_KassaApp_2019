package model.discount;

import model.basket.Basket;
import model.discount.combined.CombinedDiscountContext;
import model.discount.combined.CombinedDiscountFactory;
import model.discount.combined.CombinedDiscountType;
import model.properties.PropertiesOld;
import model.properties.Property;

import java.util.Collection;
import java.util.LinkedList;

public abstract class DiscountFactory {
    public static DiscountContext fromProperties(Basket basket) {
        Collection<DiscountStrategy> discounts = new LinkedList<>();

        boolean doExpensive = Property.DISCOUNT_EXPENSIVE.asBool();
        if (doExpensive) {
            double amount = Property.DISCOUNT_EXPENSIVE_AMOUNT.asInt();
            double ratio = amount / 100;
            discounts.add(new ExpensiveDiscount(ratio));
        }

        boolean doGroup = Property.DISCOUNT_GROUP.asBool();
        if (doGroup) {
            String group = Property.DISCOUNT_GROUP_NAME.asString();
            double amount = Property.DISCOUNT_GROUP_AMOUNT.asDouble();
            double ratio = amount / 100;
            discounts.add(new GroupDiscount(group, ratio));
        }

        boolean doThreshold = Property.DISCOUNT_THRESHOLD.asBool();
        if (doThreshold) {
            int thresholdThreshold = Property.DISCOUNT_THRESHOLD_THRESHOLD.asInt();
            double thresholdAmount = Property.DISCOUNT_THRESHOLD_AMOUNT.asDouble();
            double thresholdRatio = thresholdAmount / 100;
            discounts.add(new ThresholdDiscount(thresholdThreshold, thresholdRatio));
        }

        CombinedDiscountType combinationType = Property.DISCOUNTS_COMBINATION_TYPE.asEnum(CombinedDiscountType.class);
        CombinedDiscountContext combinedDiscount = CombinedDiscountFactory.fromType(discounts, combinationType);

        DiscountContext context = new DiscountContext(combinedDiscount, basket);

        return context;
    }
}
