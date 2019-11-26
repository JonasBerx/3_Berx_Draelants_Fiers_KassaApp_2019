package view.panels;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
/**
 * @Author Jonas Berx
 * @Version 1.0
 * Settings tab for Cashier
 * */
public class CashierSettingsPane extends GridPane {
    private final ComboBox options = new ComboBox();
    private Button saveButton = new Button("Save");

    public CashierSettingsPane() {

        options.getItems().addAll(
                "Excel",
                "Text",
                "Csv"

        );
        options.setValue("Csv");

        this.setHgap(5);
        this.setVgap(5);


        this.add(options,2,1,1,1);
        this.add(saveButton,2,2,1,2);
        System.out.println(options.getValue().toString().toUpperCase());
    }



}
