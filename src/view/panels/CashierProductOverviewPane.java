package view.panels;

import database.ArticleDbInMemory;
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
import model.CsvLoadSave;
import model.LoadSaveStrategy;
import sun.util.locale.provider.JRELocaleProviderAdapter;

import java.io.File;
import java.io.FileNotFoundException;
/**
 * @Author Dieter Draelants
 * Uitlezen van hashmap naar de artikelen pagina
 * - Change to
 */
//TODO Change to TableVIEW!

public class CashierProductOverviewPane extends GridPane {
	//private TableView<Product> table;
	CsvLoadSave strategy = new CsvLoadSave();
	File file = new File("src/bestanden/articles.csv");



	public CashierProductOverviewPane() throws FileNotFoundException {


		strategy.read(file);

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Products:"), 0, 0, 1, 1);
		this.add(new Label("Code"), 0, 1, 1, 1);
		this.add(new Label("Name"), 1, 1, 1, 1);
		this.add(new Label("Group"), 2, 1, 1, 1);
		this.add(new Label("Price"), 3, 1, 1, 1);
		this.add(new Label("Stock"), 4, 1, 1, 1);




		int i = 0;
		for (Article a : strategy.getArticles() ) {
			this.add(new Label(String.valueOf(a.getArticleCode())), 0, i + 2, 1, 1);
			this.add(new Label(a.getArticleName()), 1, i + 2, 1, 1);
			this.add(new Label(a.getGroup()), 2, i + 2, 1, 1);
			this.add(new Label(String.valueOf(a.getPrice())), 3, i + 2, 1, 1);
			this.add(new Label(String.valueOf(a.getQuantity())), 4, i + 2, 1, 1);

			i++;
		}


	}
	
	

}
