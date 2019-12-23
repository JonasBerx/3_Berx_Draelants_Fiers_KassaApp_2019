package model.receipt;

import model.DomainFacade;
import model.Prop;
import model.basket.Basket;

import java.util.Arrays;

import static model.Util.rep;

public class FooterClosure extends ReceiptDecorator {
    public FooterClosure(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(Basket basket) {
        return String.join("\n", Arrays.asList(
                super.getReceipt(basket),
                rep("─", 36),
                Prop.RECEIPT_FOOTER_MESSAGE_TXT.asString()
        ));
    }
}
