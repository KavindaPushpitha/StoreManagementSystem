package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.CustomerOrdersDAO;
import lk.gamage.stockmgt.entity.CustomerOrders;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerOrdersDAOImpl implements CustomerOrdersDAO {
    @Override
    public boolean save(CustomerOrders entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO CustomerOrders VALUES(?,?,?,?) ", entity.getOrderID(), entity.getCustomerID(), entity.getOrderDate(), entity.getCustomerPayment()) > 0;
    }

    @Override
    public boolean update(CustomerOrders entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public CustomerOrders search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<CustomerOrders> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from CustomerOrders");
        ArrayList<CustomerOrders> customerOrders = new ArrayList<>();
        while (rst.next()) {
            customerOrders.add(new CustomerOrders(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4)));
        }
        return customerOrders;
    }

}
