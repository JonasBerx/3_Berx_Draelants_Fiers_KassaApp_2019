package view.panels;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.DomainInterface;
import model.StrategyProperties;

import java.io.IOException;

/**
 * @Author Jonas Berx
 * @Version 1.0
 * Settings tab for Cashier
 * */
public class CashierSettingsPane extends GridPane {

    private final ComboBox options = new ComboBox<>();
    private final CheckBox expensiveCheckbox = new CheckBox();
    private Button saveButton = new Button("Save");
    private DomainInterface domainInterface;
    private final CheckBox groupCheckBox = new CheckBox();
    private final CheckBox thresholdCheckbox = new CheckBox();

    private TextField groupDiscount;
    private final ComboBox groups = new ComboBox<String>();
    private final ComboBox discountsThreshold = new ComboBox<>();
    private TextField expensiveDiscount;
    private TextField thresholdDiscountPrice;

    //Declarations for header/footer buttons/labels
    private TextField headerCustomMessage = new TextField();
    private final CheckBox headerDateTime = new CheckBox();
    private final CheckBox headerMessage = new CheckBox();
    private final CheckBox footerClosure = new CheckBox();
    private final CheckBox footerBtwSeparate = new CheckBox();
    private final CheckBox footerPriceDiscountSeparate = new CheckBox();


    public CashierSettingsPane(DomainInterface domainInterface) {
        this.domainInterface = domainInterface;
        try {
            StrategyProperties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        discountsThreshold.getItems().addAll(
                "1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", "12%", "13%", "14%", "15%", "16%", "17%", "18%", "19%", "20%","21%", "22%", "23%", "24%", "25%", "26%","27%", "28%", "29%", "30%"
        );

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


        groupDiscount = new TextField();
        expensiveDiscount = new TextField();
        thresholdDiscountPrice = new TextField();



        options.setValue("Select option");
        options.setValue(StrategyProperties.getStrategy());

        //check in config if box should be selected on startup
        expensiveCheckbox.setSelected( StrategyProperties.getDiscountExpensive());
        groupCheckBox.setSelected( StrategyProperties.getDiscountGroup());
        thresholdCheckbox.setSelected( StrategyProperties.getDiscountThreshold());

//        headerDateTime.setSelected();


        groups.setValue("Select Group");
        groups.setValue(StrategyProperties.getGroup());
        groupDiscount.setText(StrategyProperties.getGroupDiscount());

        expensiveDiscount.setText(StrategyProperties.getExpensiveDiscount());

        thresholdDiscountPrice.setText(StrategyProperties.getThresholdPrice());
        discountsThreshold.setValue(StrategyProperties.getThresholdDiscount() + "%");


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
        this.add(groupDiscount, 4, 4);

        this.add(expensiveCheckbox, 1, 5);
        this.add(expensiveLabel, 2, 5);
        this.add(expensiveDiscount, 4, 5);

        this.add(thresholdCheckbox, 1, 6);
        this.add(thresholdLabel, 2, 6);
        this.add(thresholdDiscountPrice, 4, 6);
        this.add(discountsThreshold, 3, 6);

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
            thresholdDiscountPrice.setDisable(true);
            discountsThreshold.setDisable(true);
        }
        //GroupCheckbox check
        if (!groupCheckBox.isSelected()) {
            groupDiscount.setDisable(true);
            groups.setDisable(true);
        }
        //expensiveCheckbox check
        if (!expensiveCheckbox.isSelected()) {
            expensiveDiscount.setDisable(true);
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
                groupDiscount.setDisable(false);
            } else  {
                groupDiscount.setDisable(true);
                groups.setDisable(true);
            }
        });
        thresholdCheckbox.setOnAction(event -> {
            if (thresholdCheckbox.isSelected()) {
                thresholdDiscountPrice.setDisable(false);
                discountsThreshold.setDisable(false);
            } else {
                thresholdDiscountPrice.setDisable(true);
                discountsThreshold.setDisable(true);
            }
        });
        expensiveCheckbox.setOnAction(event -> {
            if (expensiveCheckbox.isSelected()) {
                expensiveDiscount.setDisable(false);
            } else {
                expensiveDiscount.setDisable(true);
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
                System.out.println(groupDiscount.getText());

                System.out.println(discountsThreshold.getValue().toString().toUpperCase());
                System.out.println(thresholdDiscountPrice.getText());

                System.out.println(expensiveDiscount.getText());

                String thresholdDiscount = discountsThreshold.getValue().toString().toUpperCase();
                thresholdDiscount = String.valueOf(thresholdDiscount.charAt(0));
                System.out.println(thresholdDiscount);

                //Property setters for receipt
                StrategyProperties.setHeaderDateTime(headerDateTime.isSelected());
                StrategyProperties.setHeaderMesssageState(headerMessage.isSelected());
                StrategyProperties.setHeaderMessage(headerCustomMessage.getText());
                StrategyProperties.setFooterPriceDiscountSeparate(footerPriceDiscountSeparate.isSelected());
                StrategyProperties.setFooterBtwSeparate(footerBtwSeparate.isSelected());
                StrategyProperties.setFooterClosure(footerClosure.isSelected());


                StrategyProperties.setStrategy((options.getValue().toString().toUpperCase()));
                StrategyProperties.setGroup((groups.getValue().toString().toUpperCase()));
                StrategyProperties.setGroupDiscount((groupDiscount.getText().toUpperCase()));
                StrategyProperties.setThreshDiscount(thresholdDiscount);
                StrategyProperties.setThreshPrice((thresholdDiscountPrice.getText().toUpperCase()));
                StrategyProperties.setExpensiveDiscount((expensiveDiscount.getText().toUpperCase()));

                StrategyProperties.setDiscountGroup(groupCheckBox.isSelected());
                StrategyProperties.setDiscountThreshold(thresholdCheckbox.isSelected());
                StrategyProperties.setDiscountExpensive(expensiveCheckbox.isSelected());
                StrategyProperties.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



}
