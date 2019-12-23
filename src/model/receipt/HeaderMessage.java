package model.receipt;

import model.DomainFacade;
import model.Prop;
import model.basket.Basket;

import java.util.Arrays;

import static model.Util.rep;

/**
 * @author the team
 */
public class HeaderMessage extends ReceiptDecorator {

    public HeaderMessage(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(Basket basket) {
        return String.join("\n", Arrays.asList(
                Prop.RECEIPT_HEADER_MESSAGE_TXT.asString(),
                rep("─", 36),
                super.getReceipt(basket)
        ));
    }
}
