package model.receipt;

import model.DomainFacade;
import model.Prop;
import model.article.Article;
import model.basket.Basket;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static model.Util.rep;

public class BasicReceipt implements Receipt {
    //Basic receipt return
    public String getReceipt(Basket basket) {
        List<String> items = new LinkedList<>(Arrays.asList(
                "Omschrijving       Aantal      Prijs",
                rep("*", getWidth())
        ));
        boolean combined = Prop.RECEIPT_STACK_COMBINED_PRICE.asBool();
        boolean discounted = Prop.RECEIPT_STACK_DISCOUNTED_PRICE.asBool();
        Map<Article, Double> discounts = basket.getDiscountedPrices();
        basket.getArticleStacks().forEach((a, amount) -> {
            double stackPrice = amount * a.getPrice();
            double discountedPrice = discounts.get(a);
            items.add(String.format("%-12s       %6d     %6.2f", a.getName(), amount, a.getPrice()));
            if (combined && amount > 1)
                items.add(String.format("                   totaal     %6.2f", stackPrice));
            if (discounted && discountedPrice != stackPrice)
                items.add(String.format("              met korting     %6.2f", discountedPrice));
        });
        items.addAll(Arrays.asList(
                rep("*", 36),
                String.format("Betaald (inclusief korting)   %.2f", basket.getTotalDiscountedPrice())
        ));
        return String.join("\n", items);
    }
}
