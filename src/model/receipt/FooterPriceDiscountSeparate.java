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
        receipt.append(String.format("%n************************************%n"));
        //TODO Onduidelijk of de functie getTotalPrice(Including korting is of niet)
        receipt.append(String.format("Price (excluding discount) :  %.2f€%n", d.getBasketTotalPrice()));
        //TODO Hiervoor heb ik nog functie voor nodig om total korting
        receipt.append(String.format("Total Discount             :  %.2f€%n", d.getBasketTotalPrice() - d.getBasketDiscountedPrice()));
        return receipt.toString();
    }
}
