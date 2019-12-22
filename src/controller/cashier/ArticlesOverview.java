package controller.cashier;

import model.DomainFacade;

public class ArticlesOverview {
    private DomainFacade model;
    private view.jfx.cashier.ArticlesOverview view;

    public ArticlesOverview(DomainFacade model, view.jfx.cashier.ArticlesOverview view) {
        this.model = model;
        this.view = view;

        view.populateArticles(model.getArticleDb().getAll());
    }
}
