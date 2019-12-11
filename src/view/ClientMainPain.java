package view;

import javafx.scene.layout.BorderPane;
import model.DomainFacade;
import view.panels.ClientOverview;

class ClientMainPain extends BorderPane {
    ClientMainPain(DomainFacade domainFacade) {
        ClientOverview clientOverview = new ClientOverview(domainFacade);
        this.setCenter(clientOverview);
    }
}
