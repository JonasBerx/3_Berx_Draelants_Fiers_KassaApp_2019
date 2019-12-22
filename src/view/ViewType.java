package view;

import java.util.function.Consumer;

public enum ViewType {
    JFX(Jfx::main);

    private Consumer<String[]> main;

    ViewType(Consumer<String[]> main) {
        this.main = main;
    }

    public void main(String[] args) {
        this.main.accept(args);
    }
}
