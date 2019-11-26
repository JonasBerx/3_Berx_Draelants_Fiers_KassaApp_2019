package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.CashRegisterView;
import view.CustomerView;

import java.io.FileNotFoundException;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		CashRegisterView cashRegisterView = new CashRegisterView();
		CustomerView customerView = new CustomerView();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
