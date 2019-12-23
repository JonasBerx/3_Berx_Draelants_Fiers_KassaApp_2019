package model.receipt;

import model.DomainFacade;
import model.basket.Basket;
/**
 * @author the team
 */

//Blueprint for decorator related classes
public interface Receipt {
    default int getWidth() {
        return 36; // Default for 80mm roll
    };

    String getReceipt(Basket basket);
}
