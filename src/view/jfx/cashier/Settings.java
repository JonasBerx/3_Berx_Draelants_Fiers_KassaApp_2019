package view.jfx.cashier;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.DomainFacade;
import model.properties.PropertiesOld;
import model.properties.Property;

import java.io.IOException;

/**
 * @Author Jonas Berx
 * @Version 1.0
 * Settings tab for Cashier
 * */
//TODO Create controller
public class Settings extends GridPane {
    private final ComboBox options = new ComboBox<>();
    private final CheckBox expensiveCheckbox = new CheckBox();
    private Button saveButton = new Button("Save");
    private final CheckBox groupCheckBox = new CheckBox();
    private final CheckBox thresholdCheckbox = new CheckBox();

    private Spinner<Integer> groupDiscountAmount;
    private final ComboBox groups = new ComboBox<String>();
    private Spinner<Integer> thresholdDiscountAmount;
    private Spinner<Integer> expensiveDiscountAmount;
    private Spinner<Integer> thresholdDiscountThreshold;

    //Declarations for header/footer buttons/labels
    private TextField headerCustomMessage = new TextField();
    private final CheckBox headerDateTime = new CheckBox();
    private final CheckBox headerMessage = new CheckBox();
    private final CheckBox footerClosure = new CheckBox();
    private final CheckBox footerBtwSeparate = new CheckBox();
    private final CheckBox footerPriceDiscountSeparate = new CheckBox();


    public Settings() {
        groups.getItems().addAll(
                "Group 1",
                "Group 2"
        );

        options.getItems().addAll(
                "Excel",
                "Txt",
                "Csv"

        );



        Label groupLabel = new Label("Group Discount %");
        Label expensiveLabel = new Label("Most expensive discount %");
        Label thresholdLabel = new Label("Threshold discount price €");


        groupDiscountAmount = new Spinner<Integer>();
        SpinnerValueFactory factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 0);
        groupDiscountAmount.setValueFactory(factory);
        groupDiscountAmount.setEditable(true);
        TextFormatter formatter = new TextFormatter(factory.getConverter(), factory.getValue());
        groupDiscountAmount.getEditor().setTextFormatter(formatter);
        factory.valueProperty().bindBidirectional(formatter.valueProperty());

        expensiveDiscountAmount = new Spinner<Integer>();
        expensiveDiscountAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));
        thresholdDiscountThreshold = new Spinner<Integer>();
        thresholdDiscountThreshold.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
        thresholdDiscountAmount = new Spinner<Integer>();
        thresholdDiscountAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100));


        options.setValue("Select option");
        options.setValue(PropertiesOld.getLoader());

        //check in config if box should be selected on startup
        expensiveCheckbox.setSelected(Property.DISCOUNT_EXPENSIVE.asBool());
        groupCheckBox.setSelected(Property.DISCOUNT_GROUP.asBool());
        thresholdCheckbox.setSelected(Property.DISCOUNT_THRESHOLD.asBool());

        headerDateTime.setSelected(PropertiesOld.getHeaderDateTime());
        headerMessage.setSelected(PropertiesOld.getHeaderMesssageState());
        footerPriceDiscountSeparate.setSelected(PropertiesOld.getFooterPriceDiscountSeparate());
        footerBtwSeparate.setSelected(PropertiesOld.getFooterBtwSeparate());
        footerClosure.setSelected(PropertiesOld.getFooterClosure());

        headerCustomMessage.setText(PropertiesOld.getHeaderMessage());


        groups.setValue("Select Group");
        groups.setValue(PropertiesOld.getGroup());

        groupDiscountAmount.getValueFactory().setValue(Property.DISCOUNT_GROUP_AMOUNT.asInt());
        expensiveDiscountAmount.getValueFactory().setValue(Property.DISCOUNT_EXPENSIVE_AMOUNT.asInt());
        thresholdDiscountAmount.getValueFactory().setValue(Property.DISCOUNT_THRESHOLD_AMOUNT.asInt());
        thresholdDiscountThreshold.getValueFactory().setValue(Property.DISCOUNT_THRESHOLD_THRESHOLD.asInt());


        this.setHgap(2);
        this.setVgap(4);

        this.add(new Label("Read from:"), 1, 1);
        this.add(options, 2, 1);

        //All discount tralala
        Label discountHeader = new Label("Discount Options");
        discountHeader.setFont(new Font("Arial", 20));
        this.add(discountHeader, 1, 2, 2, 1);
        this.add(groupCheckBox, 1, 4);
        this.add(groupLabel, 2, 4);
        this.add(groups, 3, 4);
        this.add(groupDiscountAmount, 4, 4);

        this.add(expensiveCheckbox, 1, 5);
        this.add(expensiveLabel, 2, 5);
        this.add(expensiveDiscountAmount, 4, 5);

        this.add(thresholdCheckbox, 1, 6);
        this.add(thresholdLabel, 2, 6);
        this.add(thresholdDiscountThreshold, 4, 6);
        this.add(thresholdDiscountAmount, 3, 6);

        //All customize header stuff
        Label receiptcustomHeader = new Label("Receipt print Options - Header");
        receiptcustomHeader.setFont(new Font("Arial", 20));
        this.add(receiptcustomHeader, 1, 8, 2, 1);
        //checkboxes + labels
        this.add(headerDateTime, 1, 9);
        this.add(new Label("Show/hide show date and time on receipt"), 2, 9);
        this.add(headerMessage, 1, 10);
        this.add(new Label("Show/hide create custom message"), 2, 10);
        this.add(headerCustomMessage, 4, 10);

        //All customize footer stuff
        Label receiptcustomFooter = new Label("Receipt print Options - Footer");
        receiptcustomFooter.setFont(new Font("Arial", 20));
        this.add(receiptcustomFooter, 1, 11, 2, 1);
        //checkboxes + labels
        this.add(footerPriceDiscountSeparate, 1, 12);
        this.add(new Label("Show/hide price and discount separately"), 2, 12);
        this.add(footerBtwSeparate, 1, 13);
        this.add(new Label("Show/hide btw separate"), 2, 13);
        this.add(footerClosure, 1, 14);
        this.add(new Label("Show/hide closure message"), 2, 14);
        //save button placement
        this.add(saveButton, 2, 15);

        /*
        THIS SECTiON MAKES SURE THE TEXTFIELDS/... stay disabled on startup
         */
        //ThresholdCheckbox check
        if (!thresholdCheckbox.isSelected()) {
            thresholdDiscountThreshold.setDisable(true);
            thresholdDiscountAmount.setDisable(true);
        }
        //GroupCheckbox check
        if (!groupCheckBox.isSelected()) {
            groupDiscountAmount.setDisable(true);
            groups.setDisable(true);
        }
        //expensiveCheckbox check
        if (!expensiveCheckbox.isSelected()) {
            expensiveDiscountAmount.setDisable(true);
        }
        //Only need to check for headermessage cause this is the only textfield
        if(!headerMessage.isSelected()) {
            headerCustomMessage.setDisable(true);
        }



        /*
        THIS SECTION HANDLES THE EVENTS BEHIND THE BUTTONS
         */
        groupCheckBox.setOnAction(event -> {
            if (groupCheckBox.isSelected()) {
                groups.setDisable(false);
                groupDiscountAmount.setDisable(false);
            } else  {
                groupDiscountAmount.setDisable(true);
                groups.setDisable(true);
            }
        });
        thresholdCheckbox.setOnAction(event -> {
            if (thresholdCheckbox.isSelected()) {
                thresholdDiscountThreshold.setDisable(false);
                thresholdDiscountAmount.setDisable(false);
            } else {
                thresholdDiscountThreshold.setDisable(true);
                thresholdDiscountAmount.setDisable(true);
            }
        });
        expensiveCheckbox.setOnAction(event -> {
            if (expensiveCheckbox.isSelected()) {
                expensiveDiscountAmount.setDisable(false);
            } else {
                expensiveDiscountAmount.setDisable(true);
            }
        });

        headerMessage.setOnAction(event -> {
            if(headerMessage.isSelected()) {
                headerCustomMessage.setDisable(false);
            } else {
                headerCustomMessage.setDisable(true);
            }
        });

        saveButton.setOnAction(event -> {
            try {
                String thresholdDiscount = thresholdDiscountAmount.getValue().toString().toUpperCase();
                thresholdDiscount = String.valueOf(thresholdDiscount.charAt(0));

                //Property setters for receipt
                PropertiesOld.setHeaderDateTime(headerDateTime.isSelected());
                PropertiesOld.setHeaderMesssageState(headerMessage.isSelected());
                PropertiesOld.setHeaderMessage(headerCustomMessage.getText());
                PropertiesOld.setFooterPriceDiscountSeparate(footerPriceDiscountSeparate.isSelected());
                PropertiesOld.setFooterBtwSeparate(footerBtwSeparate.isSelected());
                PropertiesOld.setFooterClosure(footerClosure.isSelected());


                PropertiesOld.setLoader((options.getValue().toString().toUpperCase()));
                PropertiesOld.setGroup((groups.getValue().toString().toUpperCase()));
                Property.DISCOUNT_GROUP_AMOUNT.set(groupDiscountAmount.getValue());
                PropertiesOld.setThreshDiscount(thresholdDiscount);
                Property.DISCOUNT_THRESHOLD_THRESHOLD.set(thresholdDiscountThreshold.getValue());
                Property.DISCOUNT_EXPENSIVE_AMOUNT.set(expensiveDiscountAmount.getValue());

                Property.DISCOUNT_GROUP.set(groupCheckBox.isSelected());
                Property.DISCOUNT_THRESHOLD.set(thresholdCheckbox.isSelected());
                Property.DISCOUNT_EXPENSIVE.set(expensiveCheckbox.isSelected());

                PropertiesOld.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
