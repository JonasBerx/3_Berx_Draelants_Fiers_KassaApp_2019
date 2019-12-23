package model.receipt;

import model.DomainFacade;
import model.Prop;
import model.basket.Basket;

import java.util.Arrays;

import static model.Util.rep;

public class FooterVatSeparate extends ReceiptDecorator {
    public FooterVatSeparate(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(Basket basket) {
        return String.join("\n", Arrays.asList(
                super.getReceipt(basket),
                String.format("Prijs excl. BTW              %6.2f€", basket.getTotalDiscountedPrice() * 0.95),
                String.format("BTW (5%%)                     %6.2f€", basket.getTotalDiscountedPrice() * 0.05)
        ));
    }
}
