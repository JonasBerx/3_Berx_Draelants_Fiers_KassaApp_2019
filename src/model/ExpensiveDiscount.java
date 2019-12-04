package model;

public class ExpensiveDiscount implements KortingStrategy {
    int duurste = 25;

    @Override
    public int berekenKorting(Shop shop) {
        return 0;
    }
}
