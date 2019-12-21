package model.receipt;

import model.DomainFacade;
import model.article.Article;

public class BasicReceipt implements Receipt {
    //Basic receipt return
    public String getReceipt(DomainFacade d) {
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("Description       Quantity     Price%n"));
        receipt.append(String.format("************************************%n"));
        for (Article a : d.getAllUniqueBasketArticles()) {
            receipt.append(String.format("%-12s       %6d    %6.2f€%n", a.getName(), a.getQuantity(), a.getPrice()));
        }
        receipt.append(String.format("************************************%n"));
        //TODO getbaskettotalprice is not including discount so needs to be changed
        receipt.append(String.format("Payed (includes discount) :  %.2f€%n", d.getBasketTotalPrice()));
        return receipt.toString();
    }
}
