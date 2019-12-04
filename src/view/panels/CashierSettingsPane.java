package view.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
import model.DomainInterface;
import model.Shop;
import model.StrategyProperties;

import java.io.IOException;

/**
 * @Author Jonas Berx
 * @Version 1.0
 * Settings tab for Cashier
 * */
public class CashierSettingsPane extends GridPane {

    private final ComboBox options = new ComboBox();
    private Button saveButton = new Button("Save");
    private DomainInterface domainInterface;

    private TextField groupDiscount;
    private final ComboBox groups = new ComboBox();
    private final ComboBox discountsThreshold = new ComboBox();
    private TextField expensiveDiscount;
    private TextField thresholdDiscountPrice;


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

        groups.setValue("Select Group");

        groups.setValue(StrategyProperties.getGroup());
        groupDiscount.setText(StrategyProperties.getGroupDiscount());

        expensiveDiscount.setText(StrategyProperties.getExpensiveDiscount());

        thresholdDiscountPrice.setText(StrategyProperties.getThreshold());
        discountsThreshold.setValue(StrategyProperties.getThresholdDiscount() + "%");
        this.setHgap(5);
        this.setVgap(5);


        this.add(options,2,1,1,1);
        this.add(saveButton,2,8,1,2);
        this.add(groupLabel, 2, 4);
        this.add(groups, 3, 4);
        this.add(groupDiscount, 4, 4);
        this.add(expensiveLabel, 2, 5);
        this.add(expensiveDiscount, 4, 5);
        this.add(thresholdLabel, 2, 6);
        this.add(thresholdDiscountPrice,4,6);
        this.add(discountsThreshold, 3, 6);

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

                StrategyProperties.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



}
