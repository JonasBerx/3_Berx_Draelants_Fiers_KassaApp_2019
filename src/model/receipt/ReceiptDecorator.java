package model.receipt;


import model.DomainFacade;

//Via this class every other extra can acces the basic receipt and add on to it
public abstract class ReceiptDecorator implements Receipt {
    protected Receipt receipt;

    public ReceiptDecorator(Receipt newReceipt) {
        receipt = newReceipt;
    }

    public String getDescription(DomainFacade d) {
        return receipt.getReceipt(d);
    }
}
