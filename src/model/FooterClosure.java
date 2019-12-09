package model;

public class FooterClosure extends ReceiptDecorator {
    public FooterClosure(Receipt newReceipt) {
        super(newReceipt);
    }

    @Override
    public String getReceipt(DomainInterface d) {
        StringBuilder receipt = new StringBuilder();
        receipt.append(this.getDescription(d));
        receipt.append(String.format("%n------------------------------------%n"));
        receipt.append("Bedankt voor uw aankoop!");
        return receipt.toString();
    }
}
