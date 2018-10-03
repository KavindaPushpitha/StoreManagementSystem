package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.model.DamageTextDTO;
import lk.gamage.stockmgt.model.OrdersDTO;
import lk.gamage.stockmgt.model.ViewCustomerOrderTableDTO;

import java.util.ArrayList;

public interface CustomerOrdersBO extends SuperBO {
    public boolean addCustomerOrder(OrdersDTO Order) throws Exception;

    public boolean updateCustomerOrder(OrdersDTO Order) throws Exception;

    public boolean deleteCustomerOrder(String OrderID) throws Exception;

    public OrdersDTO searchCustomerOrder(String OrderID) throws Exception;

    public ArrayList<ViewCustomerOrderTableDTO> getAllCustomerOrder(String id) throws Exception;

    public ArrayList<String> getCustomerOrderDates() throws Exception;

    public ArrayList<String> getCustomerOrderIDs() throws Exception;

    public DamageTextDTO getAllCustomerDetail(String orderID) throws Exception;
}
