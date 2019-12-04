package model;

public class ShopTest {
    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.getBasket().add(new Article(11, "CoolItemJaja", "gr2", 511.2, 112));
        shop.getBasket().add(new Article(11, "Dus niet", "gr2", 5.2, 2));
        shop.getBasket().add(new Article(11, "Tuurlijk", "gr1", 51.2, 12));
        System.out.println(shop.getReceipt());
    }
}
