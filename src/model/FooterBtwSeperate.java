package model;

public class FooterBtwSeperate extends ReceiptDecorator {
    public FooterBtwSeperate(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainInterface d) {
        return null;
    }
}
