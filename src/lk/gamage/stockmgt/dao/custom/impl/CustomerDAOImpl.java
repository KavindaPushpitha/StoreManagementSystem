package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.CustomerDAO;
import lk.gamage.stockmgt.db.DBConnection;
import lk.gamage.stockmgt.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?)", customer.getCustomerID(), customer.getCustomerName(), customer.getDob(), customer.getAddress(), customer.getContact(), customer.getNic()) > 0;
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Customer SET customerName=?,dob=?,address=?,contact=?,nic=? WHERE customerID=? ", customer.getCustomerName(), customer.getDob(), customer.getAddress(), customer.getContact(), customer.getNic(), customer.getCustomerID()) > 0;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE customerID=? ", s) > 0;
    }

    @Override
    public Customer search(String s) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        final ResultSet rst = stm.executeQuery("SELECT * FROM customer WHERE CustomerName like'%" + s + "%'");
//       ResultSet rst = CrudUtil.executeQuery("SELECT * FROM customer WHERE fName like'?'", s);
        if (rst.next()) {
            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6)));
        }
        return customers;
    }

    @Override
    public Customer searchByNIC(String s) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        final ResultSet rst = stm.executeQuery("SELECT * FROM customer WHERE NIC like'%" + s + "%'");
//       ResultSet rst = CrudUtil.executeQuery("SELECT * FROM customer WHERE fName like'?'", s);
        if (rst.next()) {
            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
        } else {
            return null;
        }
    }
}
