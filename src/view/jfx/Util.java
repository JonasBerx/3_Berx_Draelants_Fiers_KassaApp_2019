package view.jfx;

import java.lang.reflect.InvocationTargetException;

public abstract class Util {
    public static <E extends Enum<E>> E[] getEnumValues(Class<E> enumClass) {
        try {
            Object valuesObj = enumClass.getMethod("values").invoke(null);
            @SuppressWarnings("unchecked")
            E[] values = (E[]) valuesObj;
            return values;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassCastException e) {
            throw new RuntimeException(String.format("Cannot get enum values from %s", enumClass));
        }
    }
}
