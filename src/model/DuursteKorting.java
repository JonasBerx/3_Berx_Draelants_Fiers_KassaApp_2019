package model;

public class DuursteKorting implements KortingStrategy {
    int duurste = 25;

    @Override
    public int berekenKorting(Shop shop) {
        return 0;
    }
}
