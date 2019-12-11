package view;
	
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.DomainFacade;

import java.io.IOException;

public class ShopApp extends Application {
	@Override
	public void start(Stage primaryStage)  {
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
		CashRegisterView cashRegisterView = new CashRegisterView(domainFacade);
		ClientView clientView = new ClientView(domainFacade);
	}
}
