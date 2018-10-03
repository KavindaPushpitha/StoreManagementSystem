package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.SupplierOrderDetailDAO;
import lk.gamage.stockmgt.entity.SupplierOrderDetail;
import lk.gamage.stockmgt.entity.SupplierOrderDetail_PK;

import java.util.ArrayList;

public class SupplierOrderDetailDAOImpl implements SupplierOrderDetailDAO {


    @Override
    public boolean save(SupplierOrderDetail supplierOrderDetail) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO SUPPLIERORDERDETAIL VALUES(?,?,?) ", supplierOrderDetail.getSupplierOrderDetail_pk().getSupplierOrderID(), supplierOrderDetail.getSupplierOrderDetail_pk().getItemCode(), supplierOrderDetail.getQty()) > 0;
    }

    @Override
    public boolean update(SupplierOrderDetail entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(SupplierOrderDetail_PK supplierOrderDetail_pk) throws Exception {
        return false;
    }

    @Override
    public SupplierOrderDetail search(SupplierOrderDetail_PK supplierOrderDetail_pk) throws Exception {
        return null;
    }

    @Override
    public ArrayList<SupplierOrderDetail> getAll() throws Exception {
        return null;
    }
}
