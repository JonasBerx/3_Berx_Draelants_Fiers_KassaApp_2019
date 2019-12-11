package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DomainFacade;

public class CashRegisterView {
	public CashRegisterView(DomainFacade domainFacade) {
		Stage stage = new Stage();
		stage.setTitle("KASSA VIEW");
		stage.setResizable(false);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 600);
		BorderPane borderPane = new CashRegisterMainPane(domainFacade);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
}
