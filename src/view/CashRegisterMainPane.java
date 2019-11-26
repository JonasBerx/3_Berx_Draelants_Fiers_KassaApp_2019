package view;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.CsvLoadSave;
import model.LoadSaveStrategy;
import view.panels.CashierProductOverviewPane;
import view.panels.CashierSettingsPane;

import java.io.File;
import java.io.FileNotFoundException;

class CashRegisterMainPane extends BorderPane {
    private CashierProductOverviewPane cashierProductOverviewPane = new CashierProductOverviewPane();
    private CashierSettingsPane settingsPane = new CashierSettingsPane();
    CashRegisterMainPane() throws FileNotFoundException {

	    TabPane tabPane = new TabPane();
        Tab kassaTab = new Tab("Kassa");

        Tab artikelTab = new Tab("Artikelen", cashierProductOverviewPane);
        Tab instellingTab = new Tab("Instellingen", settingsPane);
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
}
