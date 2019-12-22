package controller.cashier;

import model.DomainFacade;

public class Stage {
    public Stage(DomainFacade model, view.jfx.cashier.Stage view) {
        new Main(model, view.getMain());
    }
}