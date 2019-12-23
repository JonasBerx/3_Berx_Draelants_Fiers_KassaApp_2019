package model.discount;

import model.basket.Basket;
import model.discount.combined.CombinedDiscountContext;
import model.discount.combined.CombinedDiscountFactory;
import model.discount.combined.CombinedDiscountType;
import model.Prop;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author the team
 */
public abstract class DiscountFactory {
    public static DiscountContext fromProperties(Basket basket) {
        Collection<DiscountStrategy> discounts = new LinkedList<>();

        boolean doExpensive = Prop.DISCOUNT_EXPENSIVE.asBool();
        if (doExpensive) {
            double amount = Prop.DISCOUNT_EXPENSIVE_AMOUNT.asInt();
            double ratio = amount / 100;
            discounts.add(new ExpensiveDiscount(ratio));
        }

        boolean doGroup = Prop.DISCOUNT_GROUP.asBool();
        if (doGroup) {
            String group = Prop.DISCOUNT_GROUP_NAME.asString();
            double amount = Prop.DISCOUNT_GROUP_AMOUNT.asDouble();
            double ratio = amount / 100;
            discounts.add(new GroupDiscount(group, ratio));
        }

        boolean doThreshold = Prop.DISCOUNT_THRESHOLD.asBool();
        if (doThreshold) {
            int thresholdThreshold = Prop.DISCOUNT_THRESHOLD_THRESHOLD.asInt();
            double thresholdAmount = Prop.DISCOUNT_THRESHOLD_AMOUNT.asDouble();
            double thresholdRatio = thresholdAmount / 100;
            discounts.add(new ThresholdDiscount(thresholdThreshold, thresholdRatio));
        }

        CombinedDiscountType combinationType = Prop.DISCOUNTS_COMBINATION_TYPE.asEnum(CombinedDiscountType.class);
        CombinedDiscountContext combinedDiscount = CombinedDiscountFactory.fromType(discounts, combinationType);

        return new DiscountContext(combinedDiscount, basket);
    }
}
