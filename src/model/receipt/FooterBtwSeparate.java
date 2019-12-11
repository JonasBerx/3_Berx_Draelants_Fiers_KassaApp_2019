package model.receipt;

import model.DomainFacade;

public class FooterBtwSeparate extends ReceiptDecorator {
    public FooterBtwSeparate(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainFacade d) {
        StringBuilder receipt = new StringBuilder();
        receipt.append(this.getDescription(d));
        receipt.append(String.format("------------------------------------%n"));
        receipt.append(String.format("Price excl. VAT           :  %6.2f€%n", d.getBasketTotalPrice() * 0.95));
        receipt.append(String.format("VAT price (5%%)            :  %6.2f€%n", d.getBasketTotalPrice() * 0.05));
        return receipt.toString();
    }
}
