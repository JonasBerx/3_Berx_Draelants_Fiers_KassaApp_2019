package model;

public class ShopTest {
    public static void main(String[] args) {
        //Test for Decorator receipt
        DomainInterface d = new DomainInterface();
        d.addBasketArticle(new Article(11, "CoolItemJaja", "gr2", 511.2, 112));
        Receipt r = new HeaderDateTime(new HeaderMessage(new FooterClosure(new BasicReceipt())));
        System.out.println(r.getReceipt(d));

    }
}
