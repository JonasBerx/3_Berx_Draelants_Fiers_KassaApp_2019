package model;

public class FooterBtwSeperate extends ReceiptDecorator {
    public FooterBtwSeperate(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    //TODO btw functions needed
    public String getReceipt(DomainInterface d) {
        return null;
    }
}
