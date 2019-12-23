package view.jfx;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public interface IAlerts {
    default boolean confirm(String actionToConfirm) {
        ButtonType result = Helpers.alert(
                Alert.AlertType.CONFIRMATION,
                "Confirmeer",
                "Ben je zeker?",
                actionToConfirm
        );
        return result == ButtonType.OK;
    }

    default void warn(String warning, String detail) {
        Helpers.alert(
                Alert.AlertType.WARNING,
                "Waarschuwing",
                warning,
                detail
        );
    }

    default void error(String error, String detail) {
        Helpers.alert(
                Alert.AlertType.ERROR,
                "Fout",
                error,
                detail
        );
    }

    default void info(String info, String detail) {
        Helpers.alert(
                Alert.AlertType.INFORMATION,
                "Info",
                info,
                detail
        );
    }
}
