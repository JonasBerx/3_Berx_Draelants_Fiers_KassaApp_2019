package model;

import java.io.IOException;

public class HeaderMessage extends ReceiptDecorator {
    public HeaderMessage(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainInterface d) {
        try {
            AppProperties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format(AppProperties.getHeaderMessage() + "%n"));
        receipt.append(String.format("____________________________________%n"));
        receipt.append(this.getDescription(d)).append("%n");
        return receipt.toString();
    }
}
