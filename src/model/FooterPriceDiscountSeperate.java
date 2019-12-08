package model;

public class FooterPriceDiscountSeperate extends ReceiptDecorator {
    public FooterPriceDiscountSeperate(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainInterface d) {
        StringBuilder receipt = new StringBuilder();
        receipt.append(this.getDescription(d));
        receipt.append(String.format("%n************************************%n"));
        receipt.append(String.format("Price (excluding discount) :  %.2f€%n", d.getShop().getTotalPrice()));
        receipt.append(String.format("Total Discount             :  %.2f€%n", d.getShop().getTotalPrice()));
        return receipt.toString();


    }
}
