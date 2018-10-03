package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.dao.custom.StockDAO;
import lk.gamage.stockmgt.model.StockDTO;
import lk.gamage.stockmgt.model.StockTableDTO;

import java.util.ArrayList;

public interface StockBO extends SuperBO {
    public boolean addStock(ArrayList<StockDTO> stockDTOS) throws Exception;

    public boolean addStock(StockDTO stockDTO) throws Exception;

    public boolean updateStock(StockDTO stock) throws Exception;

    public boolean deleteStock(String stockID) throws Exception;

    public StockDTO searchStock(String stockID) throws Exception;

    public ArrayList<StockTableDTO> getAllStock() throws Exception;

    public String getAllStockID() throws Exception;

    public boolean updateSellingPrice(StockDTO stock) throws Exception;

}
