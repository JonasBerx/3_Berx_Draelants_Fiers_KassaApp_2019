package model.basket.state;

import model.basket.Basket;
import model.log.Log;
import model.receipt.ReceiptFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClosedState extends BasketState {
    public ClosedState(Basket basket) {
        super(basket);
    }

    @Override
    public void cancel() {
        getBasket().setState(getBasket().getCancelledState());
    }

    @Override
    public void pay() {
        ReceiptFactory generateReceipt = new ReceiptFactory();
        System.out.println(generateReceipt.fromProps().getReceipt(getBasket()));

        getBasket().getArticleStacks().forEach((article, amount) -> {
            article.setQuantity(article.getQuantity() - amount);
        });

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        double discount = getBasket().getTotalPrice() - getBasket().getTotalDiscountedPrice();
        String msg = String.format(
                "Betaling: %s | Totaal bedrag: %.2f | Korting: %.2f | Totaal betaald: %.2f",
                formatter.format(new Date()), getBasket().getTotalPrice(), discount, getBasket().getTotalDiscountedPrice()
        );
        Log log = getBasket().getLog();
        log.log(msg);

        getBasket().setState(getBasket().getPayedState());
    }
}
