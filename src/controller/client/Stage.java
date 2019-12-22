package controller.client;

import model.DomainFacade;

public class Stage {
    public Stage(DomainFacade model, view.jfx.client.Stage view) {
        new Main(model, view.getMain());
    }
}