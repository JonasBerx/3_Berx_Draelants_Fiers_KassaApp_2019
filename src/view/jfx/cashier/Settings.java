package view.jfx.cashier;

import db.LoadSaveStrategyEnum;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.Prop;
import view.jfx.IAlerts;
import view.jfx.controls.Header;
import view.jfx.controls.IntegerSpinner;
import view.jfx.controls.VControlLabel;

import java.io.IOException;

/**
 * @author Jonas Berx
 * @version 1.0
 * Settings tab for Cashier
 **/
public class Settings extends GridPane implements IAlerts {
    private final ComboBox<LoadSaveStrategyEnum> loadSaveCmb;

    private final CheckBox groupDiscountChk;
    private final ComboBox<String> groupDiscountGroupCmb;
    private final Spinner<Integer> groupDiscountAmountSpn;

    private final CheckBox thresholdDisountChk;
    private final Spinner<Integer> thresholdDiscountThresholdSpn;
    private final Spinner<Integer> thresholdDiscountAmountSpn;

    private final CheckBox expensiveDiscountChk;
    private final Spinner<Integer> expensiveDiscountAmountSpn;

    //Declarations for header/footer buttons/labels
    private final CheckBox headerDateTimeChk;
    private final TextField headerMessageTxt;
    private final CheckBox headerMessageChk;
    private final CheckBox footerMessageChk;
    private final CheckBox footerVatChk;
    private final CheckBox footerDiscountChk;

    private final Button saveButton;


    public Settings() {
        setPadding(new Insets(10));
        setHgap(2);
        setVgap(4);


        //region Load save
        this.add(new Label("Read from:"), 1, 1);
        loadSaveCmb = new ComboBox<>();
        loadSaveCmb.getItems().addAll(LoadSaveStrategyEnum.values());
        loadSaveCmb.setValue(Prop.LOAD_SAVE_STRATEGY.asEnum(LoadSaveStrategyEnum.class));
        this.add(loadSaveCmb, 2, 1);
        //endregion


        //region Discounts
        this.add(new Header("Kortingen"), 1, 2, 2, 1);


        groupDiscountChk = new CheckBox("Groepkorting");
        groupDiscountChk.setSelected(Prop.DISCOUNT_GROUP.asBool());
        this.add(groupDiscountChk, 1, 4);

        groupDiscountGroupCmb = new ComboBox<>();
        groupDiscountGroupCmb.setValue(Prop.DISCOUNT_GROUP_NAME.get());
        this.add(new VControlLabel("Groep", groupDiscountGroupCmb), 3, 4);

        groupDiscountAmountSpn = new IntegerSpinner(0, 100, Prop.DISCOUNT_GROUP_AMOUNT.asInt(), 0);
        this.add(new VControlLabel("Percentage", groupDiscountAmountSpn), 4, 4);

        if (!groupDiscountChk.isSelected()) {
            groupDiscountAmountSpn.setDisable(true);
            groupDiscountGroupCmb.setDisable(true);
        }


        expensiveDiscountChk = new CheckBox("Duurstekorting");
        expensiveDiscountChk.setSelected(Prop.DISCOUNT_EXPENSIVE.asBool());
        this.add(expensiveDiscountChk, 1, 5);

        expensiveDiscountAmountSpn = new IntegerSpinner(0, 100, Prop.DISCOUNT_EXPENSIVE_AMOUNT.asInt(), 0);
        this.add(new VControlLabel("Percentage", expensiveDiscountAmountSpn), 4, 5);

        if (!expensiveDiscountChk.isSelected()) {
            expensiveDiscountAmountSpn.setDisable(true);
        }


        thresholdDisountChk = new CheckBox("Drempelkorting");
        thresholdDisountChk.setSelected(Prop.DISCOUNT_THRESHOLD.asBool());
        this.add(thresholdDisountChk, 1, 6);

        thresholdDiscountThresholdSpn = new IntegerSpinner(0, Integer.MAX_VALUE, Prop.DISCOUNT_THRESHOLD_THRESHOLD.asInt(), 0);
        this.add(new VControlLabel("Drempel", thresholdDiscountThresholdSpn), 3, 6);

        thresholdDiscountAmountSpn = new IntegerSpinner(0, 100, Prop.DISCOUNT_THRESHOLD_AMOUNT.asInt(), 0);
        this.add(new VControlLabel("Percentage", thresholdDiscountAmountSpn), 4, 6);

        if (!thresholdDisountChk.isSelected()) {
            thresholdDiscountThresholdSpn.setDisable(true);
            thresholdDiscountAmountSpn.setDisable(true);
        }
        //endregion


        //region Receipt header
        this.add(new Header("Kassabon - Header"), 1, 8, 2, 1);

        headerDateTimeChk = new CheckBox("Datum en tijd");
        headerDateTimeChk.setSelected(Prop.RECEIPT_HEADER_DATETIME.asBool());
        this.add(headerDateTimeChk, 1, 9);

        headerMessageChk = new CheckBox("Algemene boodschap");
        headerMessageChk.setSelected(Prop.RECEIPT_HEADER_MESSAGE.asBool());
        this.add(headerMessageChk, 1, 10);
        headerMessageTxt = new TextField();
        headerMessageTxt.setText(Prop.RECEIPT_HEADER_MESSAGE_TXT.asString());
        this.add(headerMessageTxt, 4, 10);
        if(!headerMessageChk.isSelected()) {
            headerMessageTxt.setDisable(true);
        }
        //endregion


        //region Receipt footer
        this.add(new Header("Kassabon - Footer"), 1, 11, 2, 1);

        footerDiscountChk = new CheckBox("Korting en prijs exclusief korting");
        footerDiscountChk.setSelected(Prop.RECEIPT_FOOTER_DISCOUNT.asBool());
        this.add(footerDiscountChk, 1, 12);

        footerVatChk = new CheckBox("BTW en prijs exclusief BTW");
        footerVatChk.setSelected(Prop.RECEIPT_FOOTER_VAT.asBool());
        this.add(footerVatChk, 1, 13);

        footerMessageChk = new CheckBox("Algemene boodschap");
        footerMessageChk.setSelected(Prop.RECEIPT_FOOTER_MESSAGE.asBool());
        this.add(footerMessageChk, 1, 14);
        //endregion


        //region Save
        saveButton = new Button("Save");
        this.add(saveButton, 2, 15);
        //endregion


        /*
        THIS SECTION HANDLES THE EVENTS BEHIND THE BUTTONS
         */
        groupDiscountChk.setOnAction(event -> {
            if (groupDiscountChk.isSelected()) {
                groupDiscountGroupCmb.setDisable(false);
                groupDiscountAmountSpn.setDisable(false);
            } else  {
                groupDiscountAmountSpn.setDisable(true);
                groupDiscountGroupCmb.setDisable(true);
            }
        });
        thresholdDisountChk.setOnAction(event -> {
            if (thresholdDisountChk.isSelected()) {
                thresholdDiscountThresholdSpn.setDisable(false);
                thresholdDiscountAmountSpn.setDisable(false);
            } else {
                thresholdDiscountThresholdSpn.setDisable(true);
                thresholdDiscountAmountSpn.setDisable(true);
            }
        });
        expensiveDiscountChk.setOnAction(event -> {
            if (expensiveDiscountChk.isSelected()) {
                expensiveDiscountAmountSpn.setDisable(false);
            } else {
                expensiveDiscountAmountSpn.setDisable(true);
            }
        });

        headerMessageChk.setOnAction(event -> {
            if(headerMessageChk.isSelected()) {
                headerMessageTxt.setDisable(false);
            } else {
                headerMessageTxt.setDisable(true);
            }
        });
    }


    public LoadSaveStrategyEnum getLoadSaveType() {
        return loadSaveCmb.getValue();
    }

    public boolean getGroupDiscountEnabled() {
        return groupDiscountChk.isSelected();
    }

    public String getGroupDiscountGroup() {
        return groupDiscountGroupCmb.getValue();
    }

    public int getGroupDiscountAmount() {
        return groupDiscountAmountSpn.getValue();
    }

    public boolean getThresholdDisountEnabled() {
        return thresholdDisountChk.isSelected();
    }

    public int getThresholdDiscountThreshold() {
        return thresholdDiscountThresholdSpn.getValue();
    }

    public int getThresholdDiscountAmount() {
        return thresholdDiscountAmountSpn.getValue();
    }

    public boolean getExpensiveDiscountEnabled() {
        return expensiveDiscountChk.isSelected();
    }

    public int getExpensiveDiscountAmount() {
        return expensiveDiscountAmountSpn.getValue();
    }

    public boolean getHeaderDateTimeEnabled() {
        return headerDateTimeChk.isSelected();
    }

    public String getHeaderMessageTxt() {
        return headerMessageTxt.toString();
    }

    public boolean getHeaderMessageEnabled() {
        return headerMessageChk.isSelected();
    }

    public boolean getFooterMessageEnabled() {
        return footerMessageChk.isSelected();
    }

    public boolean getFooterVatEnabled() {
        return footerVatChk.isSelected();
    }

    public boolean getFooterDiscountEnabled() {
        return footerDiscountChk.isSelected();
    }


    // Handlers
    public void setOnSave(Runnable action) {
        saveButton.setOnAction(event -> {
            action.run();
        });
    }
}
