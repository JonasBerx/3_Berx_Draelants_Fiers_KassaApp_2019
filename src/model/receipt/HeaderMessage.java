package model.receipt;

import model.DomainFacade;
import model.properties.PropertiesOld;

import java.io.IOException;

public class HeaderMessage extends ReceiptDecorator {
    public HeaderMessage(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainFacade d) {
        try {
            PropertiesOld.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format(PropertiesOld.getHeaderMessage() + "%n"));
        receipt.append(String.format("____________________________________%n"));
        receipt.append(this.getDescription(d));
        return receipt.toString();
    }
}
