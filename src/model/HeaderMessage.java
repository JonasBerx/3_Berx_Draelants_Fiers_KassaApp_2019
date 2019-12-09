package model;

public class HeaderMessage extends ReceiptDecorator {
    public HeaderMessage(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override //TODO need to figure out how to get message out of config in this
    public String getReceipt(DomainInterface d) {
        return null;
    }
}
