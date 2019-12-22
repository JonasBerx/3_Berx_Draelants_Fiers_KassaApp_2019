package controller.cashier;

import model.DomainFacade;

public class ArticlesOverview {
    public ArticlesOverview(DomainFacade model, view.jfx.cashier.ArticlesOverview view) {
        view.populateArticles(model.getArticleDb().getAll());
    }
}
