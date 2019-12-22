package view;

import java.util.function.Consumer;

public enum ViewType {
    JFX(Jfx.class),
    CONSOLE(Console.class);

    private Class<? extends ViewStrategy> viewStrategyClass;

    ViewType(Class<? extends ViewStrategy> viewStrategyClass) {
        this.viewStrategyClass = viewStrategyClass;
    }

    public Class<? extends ViewStrategy> getViewStrategyClass() {
        return viewStrategyClass;
    }
}
