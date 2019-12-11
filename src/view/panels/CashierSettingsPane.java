package view.panels;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.DomainFacade;
import model.properties.Properties;
import model.properties.Property;

import java.io.IOException;

/**
 * @Author Jonas Berx
 * @Version 1.0
 * Settings tab for Cashier
 * */
//TODO Create controller
public class CashierSettingsPane extends GridPane {

    private final ComboBox options = new ComboBox<>();
    private final CheckBox expensiveCheckbox = new CheckBox();
    private Button saveButton = new Button("Save");
    private DomainFacade domainFacade;
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


    public CashierSettingsPane(DomainFacade domainFacade) {
        this.domainFacade = domainFacade;
        try {
            Properties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        options.setValue(Properties.getLoader());

        //check in config if box should be selected on startup
        expensiveCheckbox.setSelected(Properties.getBoolean(Property.DISCOUNT_EXPENSIVE));
        groupCheckBox.setSelected(Properties.getBoolean(Property.DISCOUNT_GROUP));
        thresholdCheckbox.setSelected(Properties.getBoolean(Property.DISCOUNT_THRESHOLD));

        headerDateTime.setSelected(Properties.getHeaderDateTime());
        headerMessage.setSelected(Properties.getHeaderMesssageState());
        footerPriceDiscountSeparate.setSelected(Properties.getFooterPriceDiscountSeparate());
        footerBtwSeparate.setSelected(Properties.getFooterBtwSeparate());
        footerClosure.setSelected(Properties.getFooterClosure());

        headerCustomMessage.setText(Properties.getHeaderMessage());


        groups.setValue("Select Group");
        groups.setValue(Properties.getGroup());

        groupDiscountAmount.getValueFactory().setValue(Properties.getInt(Property.DISCOUNT_GROUP_AMOUNT));
        expensiveDiscountAmount.getValueFactory().setValue(Properties.getInt(Property.DISCOUNT_EXPENSIVE_AMOUNT));
        thresholdDiscountAmount.getValueFactory().setValue(Properties.getInt(Property.DISCOUNT_THRESHOLD_AMOUNT));
        thresholdDiscountThreshold.getValueFactory().setValue(Properties.getInt(Property.DISCOUNT_THRESHOLD_THRESHOLD));


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

                System.out.println(options.getValue().toString().toUpperCase());

                System.out.println(groups.getValue().toString().toUpperCase());
                System.out.println(groupDiscountAmount.getValue());

                System.out.println(thresholdDiscountAmount.getValue().toString().toUpperCase());
                System.out.println(thresholdDiscountThreshold.getValue());

                System.out.println(expensiveDiscountAmount.getValue());

                String thresholdDiscount = thresholdDiscountAmount.getValue().toString().toUpperCase();
                thresholdDiscount = String.valueOf(thresholdDiscount.charAt(0));
                System.out.println(thresholdDiscount);

                //Property setters for receipt
                Properties.setHeaderDateTime(headerDateTime.isSelected());
                Properties.setHeaderMesssageState(headerMessage.isSelected());
                Properties.setHeaderMessage(headerCustomMessage.getText());
                Properties.setFooterPriceDiscountSeparate(footerPriceDiscountSeparate.isSelected());
                Properties.setFooterBtwSeparate(footerBtwSeparate.isSelected());
                Properties.setFooterClosure(footerClosure.isSelected());


                Properties.setLoader((options.getValue().toString().toUpperCase()));
                Properties.setGroup((groups.getValue().toString().toUpperCase()));
                Properties.setInt(Property.DISCOUNT_GROUP_AMOUNT, groupDiscountAmount.getValue());
                Properties.setThreshDiscount(thresholdDiscount);
                Properties.setInt(Property.DISCOUNT_THRESHOLD_THRESHOLD, thresholdDiscountThreshold.getValue());
                Properties.setInt(Property.DISCOUNT_EXPENSIVE_AMOUNT, expensiveDiscountAmount.getValue());

                Properties.setBoolean(Property.DISCOUNT_GROUP, groupCheckBox.isSelected());
                Properties.setBoolean(Property.DISCOUNT_THRESHOLD, thresholdCheckbox.isSelected());
                Properties.setBoolean(Property.DISCOUNT_EXPENSIVE, expensiveCheckbox.isSelected());

                Properties.save();
                System.out.println("saved props");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



}
