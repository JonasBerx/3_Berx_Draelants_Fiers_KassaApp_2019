package view.jfx.cashier;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.DomainFacade;

public class Main extends BorderPane {
    private ProductOverview productOverview;
    private Settings settingsPane;
    private Sales salesPane;
    public Main(DomainFacade domainFacade) {
        productOverview = new ProductOverview(domainFacade);
        salesPane = new Sales(domainFacade);
        settingsPane = new Settings(domainFacade);

	    TabPane tabPane = new TabPane();
        Tab kassaTab = new Tab("Kassa", salesPane);
        Tab artikelTab = new Tab("Artikelen", productOverview);
        Tab instellingTab = new Tab("Instellingen", settingsPane);
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
}
