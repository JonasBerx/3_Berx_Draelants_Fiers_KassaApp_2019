package model;

public class HeaderMessage extends ReceiptDecorator {
    public HeaderMessage(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainInterface d) {
        return null;
    }
}
