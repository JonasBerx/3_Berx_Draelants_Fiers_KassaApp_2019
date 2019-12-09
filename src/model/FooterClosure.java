package model;

public class FooterClosure extends ReceiptDecorator {
    public FooterClosure(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainInterface d) {
        return null;
    }
}
