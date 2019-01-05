package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.ReturnItemDAO;
import lk.gamage.stockmgt.entity.ReturnItem;

import java.util.ArrayList;

public class ReturnItemDAOImpl implements ReturnItemDAO {
    @Override
    public boolean save(ReturnItem entity) throws Exception {
        return CrudUtil.executeUpdate("Insert Into ReturnItem values(?,?,?,?,?) ", entity.getReturnID(), entity.getDamageID(), entity.getItemCode(), entity.getReturnDate(), entity.getStatus()) > 0;
    }

    @Override
    public boolean update(ReturnItem entity) throws Exception {
        return CrudUtil.executeUpdate("Update ReturnItem set damageID=?,itemCode=?,returnDate=?,status=? where returnID=?  ", entity.getDamageID(), entity.getItemCode(), entity.getReturnDate(), entity.getStatus(), entity.getReturnID()) > 0;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.executeUpdate("Delete from ReturnItem where returnID=? ", s) > 0;
    }

    @Override
    public ReturnItem search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ReturnItem> getAll() throws Exception {
        return null;
    }
}
