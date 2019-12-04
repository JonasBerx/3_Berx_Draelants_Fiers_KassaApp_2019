package model;

public class ThresholdDiscount implements KortingStrategy {
    int drempel = 100;

    @Override
    public int berekenKorting(Shop shop) {
        return 0;
    }
}
