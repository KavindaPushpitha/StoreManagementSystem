package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudDAO;
import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.DamageItemDAO;
import lk.gamage.stockmgt.entity.DamageItem;

import java.util.ArrayList;

public class DamageItemDAOImpl implements DamageItemDAO {
    @Override
    public boolean save(DamageItem entity) throws Exception {
        return CrudUtil.executeUpdate("Insert into DamageItem values(?,?,?,?,?,?) ", entity.getDamageID(), entity.getItemCode(), entity.getFault(), entity.getDamageType(), entity.getDamageDate(), entity.getOrderID()) > 0;
    }

    @Override
    public boolean update(DamageItem entity) throws Exception {
        return CrudUtil.executeUpdate("Update DamageItem set itemCode=?,fault=?,damageType=?,damageDate=?,orderID=? where damageID=? ", entity.getItemCode(), entity.getFault(), entity.getDamageType(), entity.getDamageDate(), entity.getOrderID(), entity.getDamageID()) > 0;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.executeUpdate("Delete from DamageItem where damageID=? ", s) > 0;
    }

    @Override
    public DamageItem search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<DamageItem> getAll() throws Exception {
        return null;
    }
}
