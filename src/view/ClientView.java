package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DomainFacade;

public class ClientView {
	public ClientView(DomainFacade domainFacade) {
		Stage stage = new Stage();
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		BorderPane borderPane = new ClientMainPain(domainFacade);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
