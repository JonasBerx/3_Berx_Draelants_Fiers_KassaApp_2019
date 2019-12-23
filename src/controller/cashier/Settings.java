package controller.cashier;

import model.DomainFacade;
import model.Prop;

import java.io.IOException;

public class Settings {
    private DomainFacade model;
    private view.jfx.cashier.Settings view;

    public Settings(DomainFacade model, view.jfx.cashier.Settings view) {
        this.model = model;
        this.view = view;
        view.setOnSave(this::saveHandler);
    }

    private void saveHandler() {
        Prop.LOAD_SAVE_STRATEGY.set(view.getLoadSaveType());

        Prop.RECEIPT_HEADER_DATETIME.set(view.getHeaderDateTimeEnabled());
        Prop.RECEIPT_HEADER_MESSAGE.set(view.getHeaderMessageEnabled());
        Prop.RECEIPT_HEADER_MESSAGE_TXT.set(view.getHeaderMessageTxt());
        Prop.RECEIPT_FOOTER_DISCOUNT.set(view.getFooterDiscountEnabled());
        Prop.RECEIPT_FOOTER_VAT.set(view.getFooterVatEnabled());
        Prop.RECEIPT_FOOTER_MESSAGE.set(view.getFooterMessageEnabled());

        Prop.DISCOUNT_GROUP.set(view.getGroupDiscountEnabled());
        Prop.DISCOUNT_GROUP_NAME.set(view.getGroupDiscountGroup());
        Prop.DISCOUNT_GROUP_AMOUNT.set(view.getGroupDiscountAmount());

        Prop.DISCOUNT_THRESHOLD.set(view.getThresholdDisountEnabled());
        Prop.DISCOUNT_THRESHOLD_AMOUNT.set(view.getThresholdDiscountAmount());
        Prop.DISCOUNT_THRESHOLD_THRESHOLD.set(view.getThresholdDiscountThreshold());

        Prop.DISCOUNT_EXPENSIVE.set(view.getExpensiveDiscountEnabled());
        Prop.DISCOUNT_EXPENSIVE_AMOUNT.set(view.getExpensiveDiscountAmount());

        try {
            Prop.save();
        } catch (IOException e) {
            e.printStackTrace();
            view.error("De instellingen konden niet worden opgeslagen.", e.toString());
            return;
        }

        model.updateDiscountContext();

        view.info("De instellingen werden opgeslagen.", null);
    }
}
