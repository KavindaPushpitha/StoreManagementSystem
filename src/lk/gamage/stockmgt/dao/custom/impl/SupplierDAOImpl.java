package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.SupplierDAO;
import lk.gamage.stockmgt.entity.Supplier;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier supplier) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO SUPPLIER VALUES(?,?,?,?,?,?,?) ", supplier.getSupplierID(), supplier.getSupplierName(), supplier.getAddress(), supplier.getContact(), supplier.getCompanyName(), supplier.getCompanyAddress(), supplier.getCompanyContact()) > 0;
    }

    @Override
    public boolean update(Supplier supplier) throws Exception {
        return CrudUtil.executeUpdate("UPDATE SUPPLIER SET supplierName=?,address=?,contact=?,companyName=?,companyAddress=?,companyContact=? WHERE supplierID=? ", supplier.getSupplierName(), supplier.getAddress(), supplier.getContact(), supplier.getCompanyName(), supplier.getCompanyAddress(), supplier.getCompanyContact(), supplier.getSupplierID()) > 0;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Supplier WHERE SupplierId=? ", s) > 0;
    }

    @Override
    public Supplier search(String s) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM SUPPLIER WHERE SupplierName=? ", s);
        if (rst.next()) {
            return new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Supplier> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM SUPPLIER");
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (rst.next()) {
            suppliers.add(new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7)));
        }
        return suppliers;
    }
}
