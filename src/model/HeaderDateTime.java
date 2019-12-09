package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeaderDateTime extends ReceiptDecorator {
    public HeaderDateTime(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainInterface d) {
        StringBuilder receipt = new StringBuilder();
        //Date Formater
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd '            ' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        receipt.append(String.format(formatter.format(date) + "%n"));
        receipt.append(String.format("____________________________________%n"));
        receipt.append(this.getDescription(d));
        return receipt.toString();
    }
}
