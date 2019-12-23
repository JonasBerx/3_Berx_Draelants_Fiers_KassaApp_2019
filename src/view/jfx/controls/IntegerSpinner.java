package view.jfx.controls;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;

public class IntegerSpinner extends Spinner<Integer> {
    public IntegerSpinner(int min, int max, int initial, int fallback) {
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initial);
        this.setValueFactory(factory);
        this.setEditable(true);
        TextFormatter<Integer> formatter = new TextFormatter<>(factory.getConverter(), factory.getValue());
        this.getEditor().setTextFormatter(formatter);
        this.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            String corrected = newVal.replaceAll("[^\\d]", "");
            if (corrected.isEmpty()) corrected = Integer.toString(fallback);
            this.getEditor().textProperty().setValue(corrected);
        });
        factory.valueProperty().bindBidirectional(formatter.valueProperty());
    }
}
