package model;

import java.io.IOException;

public class ShopTest {
    public static void main(String[] args) {
        //Test for Decorator receipt
        DomainInterface d = null;
        try {
            d = new DomainInterface();
        } catch (IOException e) {
            e.printStackTrace();
        }
        d.addBasketArticle(new Article(11, "CoolItemJaja", "gr2", 511.2, 112));
        Receipt r = new HeaderDateTime(new HeaderMessage(new FooterClosure(new BasicReceipt())));
        System.out.println(r.getReceipt(d));

    }
}
