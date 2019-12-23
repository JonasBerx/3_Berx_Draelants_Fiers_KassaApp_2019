package model.discount.combined;


/**
 * @author Pieter Fiers
 * @author Jonas Berx
 * */

public enum CombinedDiscountType {
    SINGLE_LOWEST(SingleLowestCombinedDiscount.class),
    SINGLE_HIGHEST(SingleHighestCombinedDiscount.class),
    LOWEST(LowestCombinedDiscount.class),
    HIGHEST(HighestCombinedDiscount.class);

    private Class<? extends CombinedDiscountStrategy> combinedDiscountClass;

    CombinedDiscountType(Class<? extends CombinedDiscountStrategy> combinedDiscountClass) {
        this.combinedDiscountClass = combinedDiscountClass;
    }

    public Class<? extends CombinedDiscountStrategy> getCombinedDiscountClass() {
        return combinedDiscountClass;
    }
}
