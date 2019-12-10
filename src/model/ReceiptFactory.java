package model;

import java.io.IOException;

public class ReceiptFactory {
    public Receipt MakeReceiptFactory() throws IOException {
        StrategyProperties.load();
        Receipt base = new BasicReceipt();
        if (StrategyProperties.getHeaderMesssageState().equals("true")) {
            base = new HeaderMessage(base);
        }
        if (StrategyProperties.getHeaderDateTime().equals("true")) {
            base = new HeaderDateTime(base);
        }
        if (StrategyProperties.getFooterPriceDiscountSeparate().equals("true")) {
            base = new FooterPriceDiscountSeparate(base);
        }
        if (StrategyProperties.getFooterBtwSeparate().equals("true")) {
            base = new FooterBtwSeparate(base);
        }
        if (StrategyProperties.getFooterClosure().equals("true")) {
            base = new FooterClosure(base);
        }
        return base;
    }
}
