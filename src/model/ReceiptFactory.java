package model;

import java.io.IOException;

public class ReceiptFactory {
    public Receipt MakeReceiptFactory() throws IOException {
        StrategyProperties.load();
        Receipt base = new BasicReceipt();
        if (StrategyProperties.getHeaderMesssageState()) {
            base = new HeaderMessage(base);
        }
        if (StrategyProperties.getHeaderDateTime()) {
            base = new HeaderDateTime(base);
        }
        if (StrategyProperties.getFooterPriceDiscountSeparate()) {
            base = new FooterPriceDiscountSeparate(base);
        }
        if (StrategyProperties.getFooterBtwSeparate()) {
            base = new FooterBtwSeparate(base);
        }
        if (StrategyProperties.getFooterClosure()) {
            base = new FooterClosure(base);
        }
        return base;
    }
}
