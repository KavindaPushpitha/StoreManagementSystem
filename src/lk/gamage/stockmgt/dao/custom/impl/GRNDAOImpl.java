package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.GRNDAO;
import lk.gamage.stockmgt.entity.GRN;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GRNDAOImpl implements GRNDAO {
    @Override
    public boolean save(GRN entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO GRN VALUES(?,?,?,?,?)", entity.getGrnID(), entity.getSupplierOrderID(), entity.getGrnDate(), entity.getSupplierPayment(), entity.getStatus()) > 0;
    }

    @Override
    public boolean update(GRN entity) throws Exception {
        return CrudUtil.executeUpdate("Update grn set supplierOrderID=?, grnDate=?, SupplierPayment=?, status=? where grnID=? ", entity.getSupplierOrderID(), entity.getGrnDate(), entity.getSupplierPayment(), entity.getStatus(), entity.getGrnID()) > 0;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.executeUpdate("delete from grn where grnID=? ", s) > 0;
    }

    @Override
    public GRN search(String s) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM GRN WHERE STATUS=? ", s);
        if (rst.next()) {
            return new GRN(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5));
        } else {
            return null;
        }

    }

    @Override
    public ArrayList<GRN> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from grn");
        ArrayList<GRN> grns = new ArrayList<>();
        while (rst.next()) {
            grns.add(new GRN(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5)));
        }
        return grns;
    }

    @Override
    public GRN searchFormID(String grnID) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM GRN WHERE GrnID=? ", grnID);
        if (rst.next()) {
            return new GRN(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5));
        } else {
            return null;
        }
    }
}
