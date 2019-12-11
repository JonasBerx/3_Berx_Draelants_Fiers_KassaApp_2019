package model.receipt;

import model.DomainFacade;

public class FooterBtwSeparate extends ReceiptDecorator {
    public FooterBtwSeparate(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    //TODO btw functions needed
    public String getReceipt(DomainFacade d) {
        StringBuilder receipt = new StringBuilder();
        receipt.append(this.getDescription(d));
        receipt.append(String.format("%n------------------------------------%n"));
        receipt.append("Placeholder want jullie moeten btw nog fixe");
        return receipt.toString();
    }
}
