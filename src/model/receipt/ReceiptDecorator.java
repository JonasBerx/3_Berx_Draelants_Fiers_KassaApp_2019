package model.receipt;


import model.DomainFacade;
import model.basket.Basket;

/**
 * @author the team
 */

//Via this class every other extra can acces the basic receipt and add on to it
public abstract class ReceiptDecorator implements Receipt {
    protected Receipt receipt;


    public ReceiptDecorator(Receipt newReceipt) {
        receipt = newReceipt;
    }

    public String getReceipt(Basket basket) {
        return receipt.getReceipt(basket);
    }
}
