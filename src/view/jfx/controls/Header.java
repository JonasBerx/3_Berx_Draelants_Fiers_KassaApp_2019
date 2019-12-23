package view.jfx.controls;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Header extends Label {
    public Header(String txt) {
        super(txt);
        setFont(new Font("Arial", 20));
    }
}
