package model.discount.combined;

import model.discount.DiscountStrategy;

import java.util.Collection;

public abstract class CombinedDiscountFactory {
    public static CombinedDiscountContext fromType(Collection<DiscountStrategy> discounts, CombinedDiscountType type) {
        try {
            return new CombinedDiscountContext(discounts, type.getCombinedDiscountClass().newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            String err = String.format("Cannot instantiate CombinedDiscount type <%s>", type.name());
            throw new RuntimeException(err, e);
        }
    }
}
