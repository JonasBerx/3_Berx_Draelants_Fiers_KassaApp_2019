package view.panels;

import database.ArticleDbInMemory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Article;
import sun.util.locale.provider.JRELocaleProviderAdapter;

import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
/**
 * @Author Dieter Draelants
 * Uitlezen van hashmap naar de artikelen pagina
 * - Change to
 */
//TODO Change to TableVIEW!

public class CashierProductOverviewPane extends GridPane {
	private TableView<Article> articleTable = new TableView<>();
	private ArticleDbInMemory db = new ArticleDbInMemory();
	private ObservableList<Article> articles = FXCollections.observableArrayList();

	public CashierProductOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);


//        COlumn headers
		this.add(new Label("Products:"), 0, 0, 1, 1);
		this.add(new Label("Code"), 0, 1, 1, 1);
		this.add(new Label("Name"), 1, 1, 1, 1);
		this.add(new Label("Group"), 2, 1, 1, 1);
		this.add(new Label("Price"), 3, 1, 1, 1);
		this.add(new Label("Stock"), 4, 1, 1, 1);


		articleTable.setItems(articles);

		TableColumn<Article,Integer> firstNameCol = new TableColumn<Article, Integer>("Code");
		firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
		TableColumn<Person,String> lastNameCol = new TableColumn<Person,String>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));

		table.getColumns().setAll(firstNameCol, lastNameCol);

	}
	
	

}
