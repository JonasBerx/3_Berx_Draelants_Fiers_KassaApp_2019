package model.receipt;

import model.DomainFacade;

//Blueprint for decorator related classes
public interface Receipt {
    String getReceipt(DomainFacade d);
}
