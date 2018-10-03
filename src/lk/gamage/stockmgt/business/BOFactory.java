package lk.gamage.stockmgt.business;

import lk.gamage.stockmgt.business.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    public enum BOTypes {
        CUSTOMER, CUSTOMER_ORDERS, DAMAGE_ITEM, GRN, GRN_DETAIL, ITEM, RETURN_ITEM, STOCK, SUPPLIER, SUPPLIER_ORDER, WARRANTY, LOGIN;
    }

    public BOFactory() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public <T> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case RETURN_ITEM:
                return (T) new ReturnItemBOImpl();
            case DAMAGE_ITEM:
                return (T) new DamageItemBOImpl();
            case GRN_DETAIL:
                return (T) new GRNDetailBOImpl();
            case WARRANTY:
                return (T) new WarrantyBOImpl();
            case SUPPLIER:
                return (T) new SupplierBOImpl();
            case STOCK:
                return (T) new StockBOImpl();
            case GRN:
                return (T) new GRNBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case SUPPLIER_ORDER:
                return (T) new SupplierOrderBOImpl();
            case CUSTOMER_ORDERS:
                return (T) new CustomerOrdersBOImpl();
            case LOGIN:
                return (T) new LoginBOImpl();
            default:
                return null;
        }
    }
}
