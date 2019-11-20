package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;	

public class CustomerView {
	private Stage stage = new Stage();		
		
	public CustomerView(){
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);			
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
