package view.jfx.cashier;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import model.DomainFacade;

public class Stage {
	private Main main;
	private javafx.stage.Stage stage;

	public Stage(DomainFacade domainFacade) {
		stage = new javafx.stage.Stage();
		stage.setTitle("KASSA VIEW");
		stage.setResizable(false);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 600);
		main = new Main(domainFacade);
		BorderPane borderPane = main;
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void setOnClose(Runnable handler) {
		stage.setOnCloseRequest(e -> handler.run());
	}

	public void close() {
		stage.close();
	}

	public Main getMain() {
		return main;
	}
}
