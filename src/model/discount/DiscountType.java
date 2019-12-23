package model.discount;

/**
 * @author the team
 */

public enum DiscountType {
    GROUP(GroupDiscount.class),
    THRESHOLD(ThresholdDiscount.class),
    EXPENSIVE(ExpensiveDiscount.class);

    private Class<? extends DiscountStrategy> discountClass;

    DiscountType(Class<? extends DiscountStrategy> discountClass) {
        this.discountClass = discountClass;
    }

    public Class<? extends DiscountStrategy> getDiscountClass() {
        return discountClass;
    }
}
