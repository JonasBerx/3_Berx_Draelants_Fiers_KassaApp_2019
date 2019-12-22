package view.jfx.cashier;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.DomainFacade;

public class Main extends BorderPane {
    private ArticlesOverview articlesOverview;
    private Settings settingsPane;
    private Sales salesPane;


    public Main(DomainFacade domainFacade) {
        articlesOverview = new ArticlesOverview();
        salesPane = new Sales();
        settingsPane = new Settings();

	    TabPane tabPane = new TabPane();
        Tab kassaTab = new Tab("Kassa", salesPane);
        Tab artikelTab = new Tab("Artikelen", articlesOverview);
        Tab instellingTab = new Tab("Instellingen", settingsPane);
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}

    public ArticlesOverview getArticlesOverview() {
        return articlesOverview;
    }

    public Settings getSettingsPane() {
        return settingsPane;
    }

    public Sales getSalesPane() {
        return salesPane;
    }
}
