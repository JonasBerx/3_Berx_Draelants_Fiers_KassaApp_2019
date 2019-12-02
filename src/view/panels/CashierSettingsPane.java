package view.panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public CashierSettingsPane(DomainInterface domainInterface) {
        this.domainInterface = domainInterface;
        try {
            StrategyProperties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        options.getItems().addAll(
                "Excel",
                "Txt",
                "Csv"

        );

        options.setValue("Select option");
        options.setValue(StrategyProperties.getStrategy());

        this.setHgap(5);
        this.setVgap(5);


        this.add(options,2,1,1,1);
        this.add(saveButton,2,2,1,2);

        saveButton.setOnAction(event -> {
            try {

                System.out.println(options.getValue().toString().toUpperCase());
                StrategyProperties.setStrategy((options.getValue().toString().toUpperCase()));
                StrategyProperties.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



}
