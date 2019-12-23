package model.receipt;

import model.DomainFacade;
import model.Prop;
import model.basket.Basket;

import java.util.Arrays;

import static model.Util.rep;

/**
 * @author the team
 */

public class FooterPriceDiscountSeparate extends ReceiptDecorator {
    public FooterPriceDiscountSeparate(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(Basket basket) {
        return String.join("\n", Arrays.asList(
                super.getReceipt(basket),
                String.format("Prijs (exclusief korting)   €%6.2f", basket.getTotalPrice()),
                String.format("Totale korting               €%6.2f", basket.getTotalPrice() - basket.getTotalDiscountedPrice())
        ));
    }
}
