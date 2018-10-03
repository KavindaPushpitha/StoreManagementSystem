package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.BOFactory;
import lk.gamage.stockmgt.business.custom.CustomerOrdersBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.*;
import lk.gamage.stockmgt.dao.custom.impl.*;
import lk.gamage.stockmgt.db.DBConnection;
import lk.gamage.stockmgt.entity.*;
import lk.gamage.stockmgt.model.*;

import java.sql.Connection;
import java.util.ArrayList;

public class CustomerOrdersBOImpl implements CustomerOrdersBO {
    private CustomerOrdersDAO customerOrdersDAO;
    private WarrantyDAO warrantyDAO;
    private OrderDetailDAO orderDetailDAO;
    private StockDAO stockDAO;
    private QueryDAO queryDAO;

    public CustomerOrdersBOImpl() {
        customerOrdersDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER_ORDER);
        warrantyDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.WARRANTY);
        orderDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);
        stockDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STOCK);
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    @Override
    public boolean addCustomerOrder(OrdersDTO order) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean result = customerOrdersDAO.save(new CustomerOrders(order.getOrderID(), order.getCustomerID(), order.getOrderDate(), order.getCustomerPayment()));
            if (!result) {
                return false;
                //connection.rollback();

            }
            for (WarrantyDTO warrantyDTO : order.getWarrantyDTOS()) {
                boolean save = warrantyDAO.save(new Warranty(warrantyDTO.getWarrantyID(), warrantyDTO.getWarrantyPeriod(), warrantyDTO.getWarrantyFrom(), warrantyDTO.getWarrantyTo()));
                if (!save) {
                    connection.rollback();
                    return false;
                }
            }
            for (OrderDetailDTO orderDetailDTO : order.getOrderDetailDTOS()) {
                boolean save1 = orderDetailDAO.save(new OrderDetail(orderDetailDTO.getOrderID(), orderDetailDTO.getItemCode(), orderDetailDTO.getWarrantyID(), orderDetailDTO.getUnitPrice(), orderDetailDTO.getOrderQty()));
                if (!save1) {
                    connection.rollback();
                    return false;
                }
                Stock search = stockDAO.search(orderDetailDTO.getItemCode());
                int currentQty = search.getQtyOnHand();
                currentQty -= orderDetailDTO.getOrderQty();
                search.setQtyOnHand(currentQty);
                boolean update = stockDAO.update(search);
                if (!update) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } finally {
            connection.setAutoCommit(true);
        }

    }

    @Override
    public boolean updateCustomerOrder(OrdersDTO Order) throws Exception {
        return false;
    }

    @Override
    public boolean deleteCustomerOrder(String OrderID) throws Exception {
        return false;
    }

    @Override
    public OrdersDTO searchCustomerOrder(String OrderID) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ViewCustomerOrderTableDTO> getAllCustomerOrder(String id) throws Exception {
        ArrayList<CustomEntity> allCustomerOrders = queryDAO.getAllCustomerOrders(id);
        ArrayList<ViewCustomerOrderTableDTO> customerOrderTableDTOS = new ArrayList<>();
        for (CustomEntity c : allCustomerOrders) {
            customerOrderTableDTOS.add(new ViewCustomerOrderTableDTO(c.getItemCode(), c.getOrderID(), c.getItemName(), c.getBrand(), c.getModelNo(), c.getDate(), c.getUnitPrice(), c.getQty()));
        }
        return customerOrderTableDTOS;
    }

    @Override
    public ArrayList<String> getCustomerOrderDates() throws Exception {
        ArrayList<CustomerOrders> all = customerOrdersDAO.getAll();
        ArrayList<String> orderDates = new ArrayList<>();
        for (CustomerOrders customerOrders : all) {
            orderDates.add(customerOrders.getOrderDate());
        }
        return orderDates;
    }

    @Override
    public ArrayList<String> getCustomerOrderIDs() throws Exception {
        ArrayList<CustomerOrders> all = customerOrdersDAO.getAll();
        ArrayList<String> orderIDs = new ArrayList<>();
        for (CustomerOrders customerOrders : all) {
            orderIDs.add(customerOrders.getOrderID());
        }
        return orderIDs;
    }

    @Override
    public DamageTextDTO getAllCustomerDetail(String orderID) throws Exception {
        CustomEntity allCustomerDetail = queryDAO.getAllCustomerDetail(orderID);
        if (allCustomerDetail != null) {
            return new DamageTextDTO(allCustomerDetail.getCustomerID(), allCustomerDetail.getCustomerName(), allCustomerDetail.getNic(), allCustomerDetail.getOrderDate());
        } else {
            return null;
        }
    }
}
