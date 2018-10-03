package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.OrderDetailDAO;
import lk.gamage.stockmgt.entity.OrderDetail;
import lk.gamage.stockmgt.entity.OrderDetail_PK;

import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(OrderDetail entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO ORDERDETAIL VALUES(?,?,?,?,?) ", entity.getOrderDetail_pk().getOrderID(), entity.getOrderDetail_pk().getItemCode(), entity.getWarrantyID(), entity.getUnitPrice(), entity.getOrderQty()) > 0;
    }

    @Override
    public boolean update(OrderDetail entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(OrderDetail_PK orderDetail_pk) throws Exception {
        return false;
    }

    @Override
    public OrderDetail search(OrderDetail_PK orderDetail_pk) throws Exception {
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws Exception {
        return null;
    }
}
