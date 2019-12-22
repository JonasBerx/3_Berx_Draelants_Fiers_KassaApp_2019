package application;

import view.ViewFactory;
import view.ViewType;

public class Main {
    public static void main(String[] args) {
        // Launch Jfx app by default
        ViewFactory.fromType(ViewType.JFX).main(args);
    }
}
