package controller.cashier;

import model.DomainFacade;

/**
 * @author Pieter Fiers
 * MVC for cashier
 * */

public class Main {
    public Main(DomainFacade model, view.jfx.cashier.Main view) {
        new Sale(model, view.getSalePane());
        new ArticlesOverview(model, view.getArticlesOverview());
        new Settings(model, view.getSettingsPane());
        new Log(model, view.getLogPane());
    }
}
