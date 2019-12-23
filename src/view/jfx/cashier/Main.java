package view.jfx.cashier;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.DomainFacade;

import java.util.Arrays;
import java.util.List;

public class Main extends BorderPane {
    private ArticlesOverview articlesOverview;
    private Settings settingsPane;
    private Sale salePane;
    private Log logPane;


    public Main(DomainFacade domainFacade) {
        articlesOverview = new ArticlesOverview();
        salePane = new Sale();
        settingsPane = new Settings();
        logPane = new Log();

	    TabPane tabPane = new TabPane();
        Tab saleTab = new Tab("Kassa", salePane);
        Tab articlesOverviewTab = new Tab("Artikelen", articlesOverview);
        Tab settingsTab = new Tab("Instellingen", settingsPane);
        Tab logTab = new Tab("Log", logPane);
        List<Tab> tabs = Arrays.asList(saleTab, articlesOverviewTab, settingsTab, logTab);
        tabs.forEach(t -> t.setClosable(false));
        tabPane.getTabs().addAll(tabs);
	    this.setCenter(tabPane);
	}

    public ArticlesOverview getArticlesOverview() {
        return articlesOverview;
    }

    public Settings getSettingsPane() {
        return settingsPane;
    }

    public Sale getSalePane() {
        return salePane;
    }

    public Log getLogPane() {
        return logPane;
    }
}
