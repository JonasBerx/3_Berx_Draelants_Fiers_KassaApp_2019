package controller.cashier;

import controller.BasketStateObserver;
import controller.ControllerWarningException;
import controller.BasketArticlesObserver;
import controller.ShopObserver;
import model.DomainFacade;
import model.Util;
import model.article.Article;
import model.basket.Basket;
import model.basket.state.BasketState;
import model.basket.state.ClosedState;
import model.basket.state.OpenState;
import model.observer.EventData;
import model.receipt.ReceiptFactory;

import java.util.List;
import java.util.Map;

public class Sale implements BasketArticlesObserver, BasketStateObserver, ShopObserver {
    private DomainFacade model;
    private view.jfx.cashier.Sale view;


    public Sale(DomainFacade model, view.jfx.cashier.Sale view) {
        this.model = model;
        this.view = view;

        populateArticles();
        view.setHoldSaleBtnHeld(model.saleIsOnHold());

        view.setOnClearArticles(this::clearArticlesHandler);
        view.setOnSubmitArticleCode(this::submitArticleCodeHandler);
        view.setOnToggleHoldSale(this::toggleHoldSaleHandler);
        view.setOnPay(this::payHandler);
        view.setOnCancel(this::cancelHandler);
        view.setOnRemove(this::removeHandler);
        view.setOnClose(this::closeHandler);

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
        view.setPayBtnEnabled(false);
    }

    @Override
    public void removeArticles(Article article, int amountToRemove) {
        for (int i = 0; i < amountToRemove; i++) {
            view.removeArticle(article);
        }
    }

    @Override
    public void handleRemovedLastArticle() {
        view.setPayBtnEnabled(false);
    }

    @Override
    public void clearArticles() {
        view.clearArticles();
    }

    public void updatePriceLabels() {
        view.setTotalPriceLbl(model.getBasketTotalPrice());
        view.setDiscountPriceLbl(model.getBasketDiscountedPrice());
    }

    @Override
    public void update(Enum<?> event, EventData data) {
        BasketArticlesObserver.super.update(event, data);
        BasketStateObserver.super.update(event, data);
        ShopObserver.super.update(event, data);
    }

    public void handleBasketSwitchEvent(Basket oldBasket) {
        oldBasket.removeObserver(this);
        model.addBasketObserver(this);
        updatePriceLabels();
        clearArticles();
        populateArticles();
        handleStateChangeEvent(null, model.getBasket().getState());
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
            view.warn("Couldn't add the specified article.", e.getWarning());
            return;
        } finally {
            view.clearArticleCodeFld();
        }

        model.addBasketArticle(article);
    }

    private void toggleHoldSaleHandler() {
        if (model.saleIsOnHold()) {
            model.continueHeldSale();
        } else {
            model.putSaleOnHold();
        }
        view.setHoldSaleBtnHeld(model.saleIsOnHold());
    }

    private void removeHandler() {
        List<Article> selectedArticlesList = view.getSelectedArticles();
        Map<Article, Integer> selectedItems = Util.flatListToAmountMap(selectedArticlesList);
        if (selectedItems.size() == 0) return;
        StringBuilder itemsStr = new StringBuilder();
        selectedItems.forEach((article, amount) -> {
            itemsStr.append(String.format(
                    "%s (x %d)\n",
                    article.getName(), amount));
        });
        String warningStr = String.format("You will remove: \n%s", itemsStr);
        if (view.confirm(warningStr)) {
            model.removeBasketArticles(selectedItems);
        }
    }

    private void closeHandler() {
        try {
            model.closeBasket();
        } catch (IllegalStateException ex) {
            view.warn("Couldn't close the basket.", ex.getMessage());
        }
    }

    private void payHandler() {
        model.payBasket();
    }

    private void cancelHandler() {
        model.cancelBasket();
    }

    @Override
    public void handleStateChangeEvent(BasketState oldState, BasketState newState) {
        boolean isOpen = newState instanceof OpenState;
        view.setCloseBtnEnabled(isOpen);
        view.setHoldSaleBtnEnabled(isOpen);

        boolean isClosed = newState instanceof ClosedState;
        view.setPayBtnEnabled(isClosed);
        view.setCancelBtnEnabled(isClosed);
    }
}
