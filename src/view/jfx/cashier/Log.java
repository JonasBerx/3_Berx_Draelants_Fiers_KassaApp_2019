package view.jfx.cashier;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;

public class Log extends TextArea {
    public Log() {
        setPadding(new Insets(10));
        setEditable(false);
    }

    public void addEntry(String msg) {
        appendText(msg + "\n");
    }
}
