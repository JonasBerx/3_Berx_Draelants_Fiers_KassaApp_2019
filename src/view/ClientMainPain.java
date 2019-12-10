package view;

import javafx.scene.layout.BorderPane;
import model.DomainInterface;
import view.panels.ClientOverview;

import java.io.FileNotFoundException;

class ClientMainPain extends BorderPane {
    ClientMainPain(DomainInterface domainInterface) {
        ClientOverview clientOverview = new ClientOverview(domainInterface);
        this.setCenter(clientOverview);
    }
}
