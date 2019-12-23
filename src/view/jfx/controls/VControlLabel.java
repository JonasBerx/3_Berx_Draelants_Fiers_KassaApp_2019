package view.jfx.controls;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class VControlLabel extends VBox {
    public VControlLabel(String label, Control control) {
        Label labelLbl = new Label(label);
        this.getChildren().addAll(labelLbl, control);
    }
}
