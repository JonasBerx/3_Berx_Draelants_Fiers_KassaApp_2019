package model.receipt;

import model.DomainFacade;
import model.Prop;
import model.basket.Basket;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static model.Util.rep;

public class HeaderDateTime extends ReceiptDecorator {
    public HeaderDateTime(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(Basket basket) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd '            ' HH:mm:ss z");
        return String.join("\n", Arrays.asList(
                formatter.format(new Date()),
                rep("─", 36),
                super.getReceipt(basket)
        ));
    }
}
