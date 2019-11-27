package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Article;
import model.CsvLoadSave;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Author Dieter Draelants
 * Tableview panel reads data from strategy item and puts it in table
 */

public class CashierProductOverviewPane extends GridPane {
	private TableView<Article> table = new TableView<>();
	private CsvLoadSave strategy = new CsvLoadSave();
	File file = new File("src/bestanden/articles.csv");


	public CashierProductOverviewPane() throws FileNotFoundException {
		//Load articles from chosen filetype
		strategy.load();

		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);


		//Pretty header for columns
		TableColumn productInfo = new TableColumn("Products");

		//Making all columns
		TableColumn<Article, Integer> codeCol = new TableColumn<>("Article Code");
		TableColumn<Article, String> nameCol = new TableColumn<>("Article Name");
		TableColumn<Article, String> groupCol = new TableColumn<>("Group");
		TableColumn<Article, Double> priceCol = new TableColumn<>("Price");
		TableColumn<Article, Integer> stockCol = new TableColumn<>("Stock");

		//Setting width for table en columns
		table.setMaxSize(600, 450);

		codeCol.setMinWidth(table.getMaxWidth() / 5);
		nameCol.setMinWidth(table.getMaxWidth() / 5);
		groupCol.setMinWidth(table.getMaxWidth() / 5);
		priceCol.setMinWidth(table.getMaxWidth() / 5);
		stockCol.setMinWidth(table.getMaxWidth() / 5);



		//Setting the data value for the table
		codeCol.setCellValueFactory(new PropertyValueFactory("articleCode"));
		nameCol.setCellValueFactory(new PropertyValueFactory("articleName"));
		groupCol.setCellValueFactory(new PropertyValueFactory("group"));
		priceCol.setCellValueFactory(new PropertyValueFactory("price"));
		stockCol.setCellValueFactory(new PropertyValueFactory("quantity"));

		//adding Everything together
		productInfo.getColumns().addAll(codeCol, nameCol, groupCol, priceCol, stockCol);
		table.setItems(getArticleList());
		table.getColumns().addAll(productInfo);
		this.add(table, 0, 0);

	}

	//Function that handles the data for the table
	private ObservableList<Article> getArticleList() {
		ObservableList<Article> articles = FXCollections.observableArrayList();
		articles.addAll(strategy.getMemory().values());
		return articles;
	}

}
