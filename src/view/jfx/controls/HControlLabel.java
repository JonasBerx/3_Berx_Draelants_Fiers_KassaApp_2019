package view.jfx.controls;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class HControlLabel extends HBox {
    public HControlLabel(String label, Control control) {
        Label labelLbl = new Label(label);
        this.getChildren().addAll(labelLbl, control);
    }
}
