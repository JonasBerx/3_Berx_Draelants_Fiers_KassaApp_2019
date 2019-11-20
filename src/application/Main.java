package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.CashRegisterView;
import view.CustomerView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		CashRegisterView cashRegisterView = new CashRegisterView();
		CustomerView customerView = new CustomerView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
