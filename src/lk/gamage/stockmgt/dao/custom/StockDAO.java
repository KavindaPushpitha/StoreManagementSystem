package lk.gamage.stockmgt.dao.custom;

import lk.gamage.stockmgt.dao.CrudDAO;
import lk.gamage.stockmgt.entity.Stock;

public interface StockDAO extends CrudDAO<Stock, String> {
    public boolean updatePrice(Stock stock) throws Exception;

    public boolean updateAll(Stock entity) throws Exception;

    public String getAllStockID() throws Exception;
}
