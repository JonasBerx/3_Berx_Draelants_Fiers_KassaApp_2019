package view.jfx.cashier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.article.Article;

import java.util.List;

import static view.jfx.Helpers.setCellValueFactory;

/**
 * @Author Dieter Draelants
 * Tableview panel reads data from strategy item and puts it in table
 * And sorts it
 */
//TODO Create controller
public class ArticlesOverview extends GridPane {
	private ObservableList<Article> articles;

	public ArticlesOverview() {
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
		TableView<Article> table = new TableView<>();
		table.setMaxSize(600, 450);

		codeCol.setMinWidth(table.getMaxWidth() / 5);
		nameCol.setMinWidth(table.getMaxWidth() / 5);
		groupCol.setMinWidth(table.getMaxWidth() / 5);
		priceCol.setMinWidth(table.getMaxWidth() / 5);
		stockCol.setMinWidth(table.getMaxWidth() / 5);
		//Setting the data value for the table

		setCellValueFactory(codeCol, Article::getCode);
		setCellValueFactory(nameCol, Article::getName);
		setCellValueFactory(groupCol, Article::getGroup);
		setCellValueFactory(priceCol, Article::getPrice);
		setCellValueFactory(stockCol, Article::getQuantity);
		//adding Everything together
		productInfo.getColumns().addAll(codeCol, nameCol, groupCol, priceCol, stockCol);
		articles = FXCollections.observableArrayList();
		table.setItems(articles);
		table.getColumns().addAll(productInfo);
		this.add(table, 0, 0);
	}

	public void populateArticles(List<Article> articles) {
		this.articles.clear();
		this.articles.addAll(articles);
		this.articles.sort(Article::compareTo);
	}
}
