package view;

public class ViewFactory {
    public static ViewContext fromType(ViewType type) {
        try {
            return new ViewContext(type.getViewStrategyClass().newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            String err = String.format("Cannot instantiate CombinedDiscount type <%s>", type.name());
            throw new RuntimeException(err, e);
        }
    }
}
