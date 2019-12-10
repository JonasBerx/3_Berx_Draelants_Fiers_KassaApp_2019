package model;

import java.io.IOException;

public class HeaderMessage extends ReceiptDecorator {
    public HeaderMessage(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override //TODO need to figure out how to get message out of config in this
    public String getReceipt(DomainInterface d) {
        try {
            StrategyProperties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format(StrategyProperties.getHeaderMessage() + "%n"));
        receipt.append(String.format("____________________________________%n"));
        receipt.append(this.getDescription(d) + "%n");
        return receipt.toString();
    }
}
