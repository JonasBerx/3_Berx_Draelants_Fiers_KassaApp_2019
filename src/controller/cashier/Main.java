package controller.cashier;

import model.DomainFacade;

public class Main {
    public Main(DomainFacade model, view.jfx.cashier.Main view) {
        new Sales(model, view.getSalesPane());
        new ArticlesOverview(model, view.getArticlesOverview());
        new Settings(model, view.getSettingsPane());
    }
}
