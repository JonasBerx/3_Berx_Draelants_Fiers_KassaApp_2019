package model.discount;

public enum DiscountType {
    GROUP(GroupDiscount.class),
    THRESHOLD(ThresholdDiscount.class),
    EXPENSIVE(ExpensiveDiscount.class);

    private Class<? extends DiscountStrategy> discountTypeClass;

    DiscountType(Class<? extends DiscountStrategy> discountTypeClass) {
        this.discountTypeClass = discountTypeClass;
    }

    public Class<? extends DiscountStrategy> getDiscountTypeClass() {
        return discountTypeClass;
    }
}
