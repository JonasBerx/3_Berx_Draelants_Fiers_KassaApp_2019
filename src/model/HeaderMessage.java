package model;

import model.properties.Properties;

import java.io.IOException;

public class HeaderMessage extends ReceiptDecorator {
    public HeaderMessage(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainInterface d) {
        try {
            Properties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format(Properties.getHeaderMessage() + "%n"));
        receipt.append(String.format("____________________________________%n"));
        receipt.append(this.getDescription(d)).append("%n");
        return receipt.toString();
    }
}
