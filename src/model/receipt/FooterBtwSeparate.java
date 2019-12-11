package model.receipt;

import model.DomainFacade;

public class FooterBtwSeparate extends ReceiptDecorator {
    public FooterBtwSeparate(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    //TODO btw functions needed
    public String getReceipt(DomainFacade d) {
        return null;
    }
}
