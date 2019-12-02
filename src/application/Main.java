package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.DomainInterface;
import view.CashRegisterView;
import view.ClientView;

import java.io.FileNotFoundException;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		DomainInterface domainInterface = new DomainInterface();
		CashRegisterView cashRegisterView = new CashRegisterView(domainInterface);
		ClientView clientView = new ClientView(domainInterface);

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
