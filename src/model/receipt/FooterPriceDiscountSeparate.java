package model.receipt;

import model.DomainFacade;

public class FooterPriceDiscountSeparate extends ReceiptDecorator {
    public FooterPriceDiscountSeparate(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainFacade d) {
        StringBuilder receipt = new StringBuilder();
        receipt.append(this.getDescription(d));
        receipt.append(String.format("************************************%n"));
        receipt.append(String.format("Price (excluding discount):  %.2f€%n", d.getBasketTotalPrice()));
        //TODO DiscountedPrice functie moet nog correcte discount teruggeven
        receipt.append(String.format("Total Discount            :    %.2f€%n", d.getBasketTotalPrice() - d.getBasketDiscountedPrice()));
        return receipt.toString();
    }
}
