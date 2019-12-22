package controller.cashier;

import controller.ControllerWarningException;
import controller.IBasketArticlesController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.DomainFacade;
import model.Util;
import model.article.Article;
import model.basket.Basket;
import model.receipt.ReceiptFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Sales implements IBasketArticlesController {
    private DomainFacade model;
    private view.jfx.cashier.Sales view;


    public Sales(DomainFacade model, view.jfx.cashier.Sales view) {
        this.model = model;
        this.view = view;

        populateArticles();
        view.setHoldSaleBtn(model.saleIsOnHold());

        view.setOnClearArticles(this::clearArticlesHandler);
        view.setOnSubmitArticleCode(this::submitArticleCodeHandler);
        view.setOnToggleHoldSale(this::toggleHoldSaleHandler);
        view.setOnPay(this::payHandler);

        model.addShopObserver(this);
        model.addBasketObserver(this);
    }

    private void populateArticles() {
        view.clearArticles();
        addArticles(model.getBasketArticleStacks());
    }

    public void addArticles(Map<Article, Integer> basketArticleStacks) {
        basketArticleStacks.forEach(this::addArticle);
    }

    public void addArticle(Article article, int amountToAdd) {
        for (int i = 0; i < amountToAdd; i++) {
            addArticle(article);
        }
    }

    @Override
    public void addArticle(Article article) {
        view.addArticle(article);
        view.setEnablePayBtn(false);
    }

    @Override
    public void removeArticle(Article article) {
        view.removeArticle(article);
    }

    @Override
    public void removeArticles(Article article, int amountToRemove) {
        for (int i = 0; i < amountToRemove; i++) {
            removeArticle(article);
        }
    }

    @Override
    public void handleRemovedLastArticle() {
        view.setEnablePayBtn(false);
    }

    @Override
    public void clearArticles() {
        view.clearArticles();
    }

    public void updatePriceLabels() {
        view.setTotalPriceLbl(model.getBasketTotalPrice());
        view.setDiscountPriceLbl(model.getBasketDiscountedPrice());
    }

    public void handleBasketSwitchEvent(Basket oldBasket) {
        oldBasket.removeObserver(this);
        updatePriceLabels();
    }


    // Handlers

    private void clearArticlesHandler() {
        if (view.confirm("This will remove all scanned products.")) {
            model.clearBasketArticles();
            view.clearArticleCodeFld();
        }
    }

    private Article articleFromCodeStr(String articleCodeStr) throws ControllerWarningException {
        if (articleCodeStr == null || articleCodeStr.trim().isEmpty())
            throw new ControllerWarningException( "No article code provided.");

        int code;
        try {
            code = Integer.parseInt(articleCodeStr);
        } catch (NumberFormatException ex) {
            throw new ControllerWarningException("Invalid article code.");
        }

        Article article = model.getArticleDb().get(code);
        if (article == null)
            throw new ControllerWarningException("Article code not found.");

        return article;
    }

    private void submitArticleCodeHandler() {
        String articleCodeStr = view.getArticleCodeFld();

        Article article;
        try {
            article = articleFromCodeStr(articleCodeStr);
        } catch (ControllerWarningException e) {
            view.warn(e.getWarning());
            return;
        }

        model.addBasketArticle(article);
        view.clearArticleCodeFld();
    }

    private void toggleHoldSaleHandler() {
        if (model.saleIsOnHold()) {
            model.continueHeldSale();
        } else {
            model.putSaleOnHold();
        }
        view.setHoldSaleBtn(model.saleIsOnHold());
    }

    private void payHandler() {
        if (model.getAllUniqueBasketArticles().size() > 0) {
            ReceiptFactory generateReceipt = new ReceiptFactory();
            try {
                System.out.println(generateReceipt.MakeReceiptFactory().getReceipt(model));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteHandler() {
        List<Article> selectedArticlesList = view.getSelectedArticles();
        Map<Article, Integer> selectedItems = Util.flatListToAmountMap(selectedArticlesList);
        if (selectedItems.size() == 0) return;
        StringBuilder itemsStr = new StringBuilder();
        selectedItems.forEach((article, amount) -> {
            itemsStr.append(String.format(
                    "%s (x %d)\n"
                    , article.getName(), amount));
        });
        String warningStr = String.format("You will remove: \n%s", itemsStr);
        if (view.confirm(warningStr)) {
            model.removeBasketArticles(selectedItems);
        }
    }
}
