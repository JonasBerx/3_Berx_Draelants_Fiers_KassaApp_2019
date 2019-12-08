package model;

public class BasicReceipt extends DomainInterface implements Receipt {
    //Basic receipt return
    public String getReceipt(DomainInterface d) {
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("Description       Quantity   Price(€)%n"));
        receipt.append(String.format("************************************%n"));
        for (Article a : d.getAllBasketArticles()) {
            receipt.append(String.format("%-12s       %6d    %6.2f%n", a.getArticleName(), a.getQuantity(), a.getPrice()));
        }
        receipt.append(String.format("************************************%n"));
        receipt.append(String.format("Payed (includes discount) :  %.2f€", d.getBasketTotalPrice()));
        return receipt.toString();
    }
}
