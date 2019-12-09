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

        expensiveCheckbox.setSelected( StrategyProperties.getDiscountExpensive());
        groupCheckBox.setSelected( StrategyProperties.getDiscountGroup());
        thresholdCheckbox.setSelected( StrategyProperties.getDiscountThreshold());

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

        //All customize footer stuff
        Label receiptcustomFooter = new Label("Receipt print Options - Footer");
        receiptcustomFooter.setFont(new Font("Arial", 20));
        this.add(receiptcustomFooter, 1, 11, 2, 1);


        //save button placement
        this.add(saveButton, 2, 15);

        //ALl checkbox actions
        if (thresholdCheckbox.isSelected()) {
            System.out.println(groupCheckBox.isSelected());
            thresholdDiscountPrice.setDisable(false);
            discountsThreshold.setDisable(false);
        } else {
            thresholdDiscountPrice.setDisable(true);
            discountsThreshold.setDisable(true);
        }
        if (groupCheckBox.isSelected()) {
            System.out.println(groupCheckBox.isSelected());
            groups.setDisable(false);
            groupDiscount.setDisable(false);
        } else{
            groupDiscount.setDisable(true);
            groups.setDisable(true);
        }
        if (expensiveCheckbox.isSelected()) {
            System.out.println(groupCheckBox.isSelected());
            expensiveDiscount.setDisable(false);
        } else {
            expensiveDiscount.setDisable(true);
        }


        groupCheckBox.setOnAction(event -> {
            if (groupCheckBox.isSelected()) {
                System.out.println(groupCheckBox.isSelected());
                groups.setDisable(false);
                groupDiscount.setDisable(false);
            } else if (!groupCheckBox.isSelected()){
                groupDiscount.setDisable(true);
                groups.setDisable(true);
            }
        });
        thresholdCheckbox.setOnAction(event -> {
            if (thresholdCheckbox.isSelected()) {
                System.out.println(thresholdCheckbox.isSelected());
                thresholdDiscountPrice.setDisable(false);
                discountsThreshold.setDisable(false);
            } else if (!thresholdCheckbox.isSelected()){
                thresholdDiscountPrice.setDisable(true);
                discountsThreshold.setDisable(true);
            }
        });
        expensiveCheckbox.setOnAction(event -> {
            if (expensiveCheckbox.isSelected()) {
                System.out.println(expensiveCheckbox.isSelected());
                expensiveDiscount.setDisable(false);

            } else if (!expensiveCheckbox.isSelected()){

                expensiveDiscount.setDisable(true);
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
