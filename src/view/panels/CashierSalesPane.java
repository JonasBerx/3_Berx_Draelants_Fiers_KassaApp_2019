package view.panels;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CashierSalesPane extends GridPane {

    public CashierSalesPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);


        final TextField articleCode = new TextField();
        articleCode.setPromptText("Enter Article Code");
        articleCode.setPrefColumnCount(10);
        articleCode.getText();
        GridPane.setConstraints(articleCode, 0, 0);
        this.getChildren().add(articleCode);
        //Defining the Submit button
        Button submit = new Button("Search Article");
        GridPane.setConstraints(submit, 1, 0);
        this.getChildren().add(submit);
        //Defining the Clear button
        Button clear = new Button("Clear Field");
        GridPane.setConstraints(clear, 1, 1);
        this.getChildren().add(clear);


        //Setting an action for the Clear button
        clear.setOnAction(e -> articleCode.clear());

    }
}
