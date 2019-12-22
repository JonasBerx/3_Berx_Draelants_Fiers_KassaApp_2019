package view.jfx;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public interface IAlerts {
    default boolean confirm(String actionToConfirm) {
        ButtonType result = Helpers.alert(
                Alert.AlertType.CONFIRMATION,
                "Confirm",
                "Are you sure?",
                actionToConfirm
        );
        return result == ButtonType.OK;
    }

    default void warn(String warning) {
        ButtonType result = Helpers.alert(
                Alert.AlertType.CONFIRMATION,
                "Confirm",
                "Are you sure?",
                warning
        );
    }
}
