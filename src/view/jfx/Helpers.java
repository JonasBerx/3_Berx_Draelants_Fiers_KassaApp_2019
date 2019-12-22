package view.jfx;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import model.article.Article;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public abstract class Helpers {
    public static ButtonType alert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> res = alert.showAndWait();
        return res.orElse(null);
    }

    public static <S, D> void setCellValueFactory(TableColumn<S, D> cell, Function<S, D> getValue) {
        cell.setCellValueFactory(a -> new ReadOnlyObjectWrapper<>(getValue.apply(a.getValue())));
    }
}
