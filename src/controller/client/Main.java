package controller.client;

import model.DomainFacade;

public class Main {
    public Main(DomainFacade model, view.jfx.client.Main view) {
        new Overview(model, view.getOverview());
    }
}
