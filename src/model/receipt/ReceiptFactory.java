package model.receipt;

import model.properties.PropertiesOld;

import java.io.IOException;

public class ReceiptFactory {
    public Receipt MakeReceiptFactory() throws IOException {
        PropertiesOld.load();
        Receipt base = new BasicReceipt();
        if (PropertiesOld.getHeaderMesssageState()) {
            base = new HeaderMessage(base);
        }
        if (PropertiesOld.getHeaderDateTime()) {
            base = new HeaderDateTime(base);
        }
        if (PropertiesOld.getFooterPriceDiscountSeparate()) {
            base = new FooterPriceDiscountSeparate(base);
        }
        if (PropertiesOld.getFooterBtwSeparate()) {
            base = new FooterBtwSeparate(base);
        }
        if (PropertiesOld.getFooterClosure()) {
            base = new FooterClosure(base);
        }
        return base;
    }
}
