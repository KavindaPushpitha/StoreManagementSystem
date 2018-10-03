package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.StockDAO;
import lk.gamage.stockmgt.entity.Stock;

import java.sql.ResultSet;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {
    @Override
    public boolean save(Stock entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO STOCK VALUES(?,?,?,?) ", entity.getStockID(), entity.getItemCode(), entity.getQtyOnHand(), entity.getSellingPrice()) > 0;
    }

    @Override
    public boolean update(Stock entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE STOCK SET qtyOnHand=? WHERE itemCode=? ", entity.getQtyOnHand(), entity.getItemCode()) > 0;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.executeUpdate("Delete From stock where stockID=? ", s) > 0;
    }

    @Override
    public Stock search(String s) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM STOCK WHERE itemCode=? ", s);
        if (rst.next()) {
            return new Stock(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getDouble(4));
        } else {
            return null;
        }
    }


    @Override
    public ArrayList<Stock> getAll() throws Exception {
        return null;
    }


    @Override
    public boolean updatePrice(Stock stock) throws Exception {
        return CrudUtil.executeUpdate("UPDATE STOCK SET SellingPrice=? WHERE stockID= ? ", stock.getSellingPrice(), stock.getStockID()) > 0;
    }

    @Override
    public boolean updateAll(Stock entity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE STOCK SET qtyOnHand=? ,SellingPrice=? WHERE stockID= ? ", entity.getQtyOnHand(), entity.getSellingPrice(), entity.getStockID()) > 0;
    }

    @Override
    public String getAllStockID() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select StockID from  stock order by 1 desc limit 1");
        if (rst.next()) {
            return rst.getString(1);
        } else {
            return null;
        }
    }
}
