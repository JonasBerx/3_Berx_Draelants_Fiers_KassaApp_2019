package model;

public class FooterBtwSeparate extends ReceiptDecorator {
    public FooterBtwSeparate(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    //TODO btw functions needed
    public String getReceipt(DomainInterface d) {
        return null;
    }
}
