package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.GRNDetailDAO;
import lk.gamage.stockmgt.entity.GRNDetail;
import lk.gamage.stockmgt.entity.GRNDetail_PK;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GRNDetailDAOImpl implements GRNDetailDAO {
    @Override
    public boolean save(GRNDetail entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO grnDetail VALUES(?,?,?,?,?) ", entity.getGrnDetail_pk().getGrnID(), entity.getGrnDetail_pk().getItemCode(), entity.getGrnDetail_pk().getStockID(), entity.getBuyingPrice(), entity.getQty()) > 0;
    }

    @Override
    public boolean update(GRNDetail entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE grnDetail set itemCode=?,stockID=?,buyingPrice=?,qty=? where grnID=? ", entity.getGrnDetail_pk().getItemCode(), entity.getGrnDetail_pk().getStockID(), entity.getBuyingPrice(), entity.getQty(), entity.getGrnDetail_pk().getGrnID()) > 0;
    }

    @Override
    public boolean delete(GRNDetail_PK grnDetail_pk) throws Exception {
        return CrudUtil.executeUpdate("Delete from grnDetail where grnID=? and itemCode=? and stockID=? ", grnDetail_pk.getGrnID(), grnDetail_pk.getItemCode(), grnDetail_pk.getStockID()) > 0;
    }

    @Override
    public GRNDetail search(GRNDetail_PK grnDetail_pk) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from grnDetail where grnID=? and itemCode=? and stockID=? ", grnDetail_pk.getGrnID(), grnDetail_pk.getItemCode(), grnDetail_pk.getStockID());
        if (rst.next()) {
            return new GRNDetail(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getInt(5));
        } else {
            return null;
        }
    }


    @Override
    public ArrayList<GRNDetail> getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<GRNDetail> getAll(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from grnDetail where grnId=? ", id);
        ArrayList<GRNDetail> grnDetails = new ArrayList<>();
        while (rst.next()) {
            grnDetails.add(new GRNDetail(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getInt(5)));
        }
        return grnDetails;
    }

    @Override
    public GRNDetail search(String id, String itemcode, String stockID) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from grnDetail where grnID=? and itemCode=? and stockID=? ", id, itemcode, stockID);
        if (rst.next()) {
            return new GRNDetail(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getInt(5));
        } else {
            return null;
        }
    }


}
