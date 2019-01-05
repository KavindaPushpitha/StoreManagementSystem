package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudDAO;
import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.ItemsDAO;
import lk.gamage.stockmgt.entity.Items;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemsDAOImpl implements ItemsDAO {
    @Override
    public boolean save(Items item) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Items VALUES(?,?,?,?) ", item.getItemCode(), item.getItemName(), item.getBrand(), item.getModelNo()) > 0;
    }

    @Override
    public boolean update(Items item) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Items SET itemName=?, brand=?, modelNo=? WHERE itemCode=? ", item.getItemName(), item.getBrand(), item.getModelNo(), item.getItemCode()) > 0;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Items WHERE itemCode=? ", s) > 0;
    }

    @Override
    public Items search(String s) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Items WHERE itemName=? ", s);
        if (rst.next()) {
            return new Items(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
        } else {
            return null;
        }

    }

    @Override
    public ArrayList<Items> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Items");
        ArrayList<Items> items = new ArrayList<>();
        while (rst.next()) {
            items.add(new Items(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
        }
        return items;
    }
}
