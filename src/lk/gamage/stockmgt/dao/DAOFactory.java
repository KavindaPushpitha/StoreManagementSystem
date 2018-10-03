package lk.gamage.stockmgt.dao;

import lk.gamage.stockmgt.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory dAOFactory;

    public enum DAOTypes {
        CUSTOMER, ITEMS, CUSTOMER_ORDER, ORDER_DETAIL, DAMAGE_ITEM, GRN, GRN_DETAIL, QUERY, RETURN_ITEM, STOCK, SUPPLIER, SUPPLIER_ORDERS, SUPPLIER_ORDER_DETAIL, WARRANTY, LOGIN;
    }

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (dAOFactory == null) {
            dAOFactory = new DAOFactory();
        }
        return dAOFactory;
    }

    public <T> T getDAO(DAOTypes dAOTypes) {
        switch (dAOTypes) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEMS:
                return (T) new ItemsDAOImpl();
            case CUSTOMER_ORDER:
                return (T) new CustomerOrdersDAOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailDAOImpl();
            case GRN:
                return (T) new GRNDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case STOCK:
                return (T) new StockDAOImpl();
            case SUPPLIER:
                return (T) new SupplierDAOImpl();
            case WARRANTY:
                return (T) new WarrantyDAOImpl();
            case GRN_DETAIL:
                return (T) new GRNDetailDAOImpl();
            case DAMAGE_ITEM:
                return (T) new DamageItemDAOImpl();
            case RETURN_ITEM:
                return (T) new ReturnItemDAOImpl();
            case SUPPLIER_ORDERS:
                return (T) new SupplierOrdersDAOImpl();
            case SUPPLIER_ORDER_DETAIL:
                return (T) new SupplierOrderDetailDAOImpl();
            case LOGIN:
                return (T) new LoginDAOImpl();
            default:
                return null;
        }
    }
}
