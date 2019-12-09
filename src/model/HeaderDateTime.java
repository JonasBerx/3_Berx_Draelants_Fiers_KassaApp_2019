package model;

public class HeaderDateTime extends ReceiptDecorator {
    public HeaderDateTime(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainInterface d) {
        this.getDescription(d)
    }
}
