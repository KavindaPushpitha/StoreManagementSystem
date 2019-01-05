package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.SupplierOrdersDAO;
import lk.gamage.stockmgt.entity.SupplierOrders;
import lk.gamage.stockmgt.model.SupplierOrderDTO;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SupplierOrdersDAOImpl implements SupplierOrdersDAO {

    @Override
    public boolean save(SupplierOrders supplierOrder) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO SupplierOrders VALUES(?,?,?) ", supplierOrder.getSupplierOrderID(), supplierOrder.getSupplierID(), supplierOrder.getOrderDate()) > 0;

    }

    @Override
    public boolean update(SupplierOrders supplierOrder) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public SupplierOrders search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<SupplierOrders> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from SupplierOrders");
        ArrayList<SupplierOrders> supplierOrders = new ArrayList<>();
        while (rst.next()) {
            supplierOrders.add(new SupplierOrders(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return supplierOrders;
    }
}
