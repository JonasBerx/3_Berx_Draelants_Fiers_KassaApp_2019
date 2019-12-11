package model.receipt;

import model.properties.Properties;

import java.io.IOException;

public class ReceiptFactory {
    public Receipt MakeReceiptFactory() throws IOException {
        Properties.load();
        Receipt base = new BasicReceipt();
        if (Properties.getHeaderMesssageState()) {
            base = new HeaderMessage(base);
        }
        if (Properties.getHeaderDateTime()) {
            base = new HeaderDateTime(base);
        }
        if (Properties.getFooterPriceDiscountSeparate()) {
            base = new FooterPriceDiscountSeparate(base);
        }
        if (Properties.getFooterBtwSeparate()) {
            base = new FooterBtwSeparate(base);
        }
        if (Properties.getFooterClosure()) {
            base = new FooterClosure(base);
        }
        return base;
    }
}
