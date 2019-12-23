package model.receipt;

import model.Prop;

/**
 * @author the team
 */

public class ReceiptFactory {
    public Receipt fromProps()  {
        Receipt base = new BasicReceipt();
        if (Prop.RECEIPT_HEADER_MESSAGE.asBool()) {
            base = new HeaderMessage(base);
        }
        if (Prop.RECEIPT_HEADER_DATETIME.asBool()) {
            base = new HeaderDateTime(base);
        }
        if (Prop.RECEIPT_FOOTER_DISCOUNT.asBool()) {
            base = new FooterPriceDiscountSeparate(base);
        }
        if (Prop.RECEIPT_FOOTER_VAT.asBool()) {
            base = new FooterVatSeparate(base);
        }
        if (Prop.RECEIPT_HEADER_MESSAGE.asBool()) {
            base = new FooterClosure(base);
        }
        return base;
    }
}
