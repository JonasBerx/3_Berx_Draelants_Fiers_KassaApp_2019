package view;
	
import javafx.scene.control.Alert;
import model.DomainFacade;
import view.jfx.cashier.Stage;

import java.io.IOException;

public class Jfx extends javafx.application.Application implements ViewStrategy {
	@Override
	public void start(javafx.stage.Stage primaryStage)  {
		DomainFacade domainFacade = null;
		try {
			domainFacade = new DomainFacade();
		} catch (IOException e) {
			e.printStackTrace();

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error starting ShopApp");
			alert.setHeaderText("Fatal error, exiting");
			alert.setContentText(e.getMessage());

			alert.showAndWait();
		}
		Stage stage = new Stage(domainFacade);
		view.jfx.client.Stage clientStage = new view.jfx.client.Stage();
		new controller.client.Stage(domainFacade, clientStage);
	}

	public void main(String[] args) {
		launch(args);
	}
}
