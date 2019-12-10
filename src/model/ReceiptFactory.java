package model;

import java.io.IOException;

public class ReceiptFactory {
    public Receipt MakeReceiptFactory() throws IOException {
        AppProperties.load();
        Receipt base = new BasicReceipt();
        if (AppProperties.getHeaderMesssageState()) {
            base = new HeaderMessage(base);
        }
        if (AppProperties.getHeaderDateTime()) {
            base = new HeaderDateTime(base);
        }
        if (AppProperties.getFooterPriceDiscountSeparate()) {
            base = new FooterPriceDiscountSeparate(base);
        }
        if (AppProperties.getFooterBtwSeparate()) {
            base = new FooterBtwSeparate(base);
        }
        if (AppProperties.getFooterClosure()) {
            base = new FooterClosure(base);
        }
        return base;
    }
}
