package model;

public class GroepsKorting implements KortingStrategy {
    int groep = 3;
    int korting = 5;

    @Override
    public int berekenKorting(Shop shop) {

        if (shop.getBasket() != null) {

        }
        return 0;
    }
}
