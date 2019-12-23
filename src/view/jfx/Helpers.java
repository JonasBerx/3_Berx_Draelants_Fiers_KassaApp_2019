package view.jfx;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import model.article.Article;

import java.util.Optional;
import java.util.function.Function;

public abstract class Helpers {
    public static ButtonType alert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> res = alert.showAndWait();
        return res.orElse(null);
    }

    public static <S, D> void setCellValueFactory(TableColumn<S, D> cell, Function<S, D> getter) {
        cell.setCellValueFactory(a -> new ReadOnlyObjectWrapper<>(getter.apply(a.getValue())));
    }

    public static <S, D> TableColumn<S, D> tableColumn(String name, Function<S, D> getter, DoubleBinding width) {
        TableColumn<S, D> column = new TableColumn<>(name);
        setCellValueFactory(column, getter);
        column.prefWidthProperty().bind(width);
        return column;
    }
}
