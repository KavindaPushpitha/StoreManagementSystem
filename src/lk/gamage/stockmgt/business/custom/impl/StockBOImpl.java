package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.custom.StockBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.QueryDAO;
import lk.gamage.stockmgt.dao.custom.StockDAO;
import lk.gamage.stockmgt.dao.custom.impl.QueryDAOImpl;
import lk.gamage.stockmgt.dao.custom.impl.StockDAOImpl;
import lk.gamage.stockmgt.entity.CustomEntity;
import lk.gamage.stockmgt.entity.Stock;
import lk.gamage.stockmgt.model.StockDTO;
import lk.gamage.stockmgt.model.StockTableDTO;

import java.util.ArrayList;

public class StockBOImpl implements StockBO {
    private StockDAO stockDAO;
    private QueryDAO queryDAO;

    public StockBOImpl() {
        stockDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STOCK);
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    @Override
    public boolean addStock(ArrayList<StockDTO> stockDTOS) throws Exception {
        boolean result;
        for (StockDTO stockDTO : stockDTOS) {
            result = stockDAO.save(new Stock(stockDTO.getStockID(), stockDTO.getItemCode(), stockDTO.getQtyOnHand(), stockDTO.getSellingPrice()));
            if (!result) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addStock(StockDTO stockDTO) throws Exception {
        return stockDAO.save(new Stock(stockDTO.getStockID(), stockDTO.getItemCode(), stockDTO.getQtyOnHand(), stockDTO.getSellingPrice()));
    }

    @Override
    public boolean updateStock(StockDTO stock) throws Exception {
        return stockDAO.update(new Stock(stock.getStockID(), stock.getItemCode(), stock.getQtyOnHand(), stock.getSellingPrice()));
    }

    @Override
    public boolean deleteStock(String stockID) throws Exception {
        return stockDAO.delete(stockID);
    }

    @Override
    public StockDTO searchStock(String stockID) throws Exception {
        Stock search = stockDAO.search(stockID);
        if (search != null) {
            return new StockDTO(search.getStockID(), search.getItemCode(), search.getQtyOnHand(), search.getSellingPrice());
        } else {
            return null;
        }

    }

    @Override
    public ArrayList<StockTableDTO> getAllStock() throws Exception {
        ArrayList<CustomEntity> allStock = queryDAO.getAllStock();
        ArrayList<StockTableDTO> stockTableDTOS = new ArrayList<>();
        for (CustomEntity customEntity : allStock) {
            stockTableDTOS.add(new StockTableDTO(customEntity.getStockID(), customEntity.getItemCode(), customEntity.getModelNo(), customEntity.getItemName(), customEntity.getBrand(), customEntity.getSellingPrice(), customEntity.getQtyOnHand()));
        }
        return stockTableDTOS;
    }


    @Override
    public String getAllStockID() throws Exception {
        return stockDAO.getAllStockID();
    }

    @Override
    public boolean updateSellingPrice(StockDTO stock) throws Exception {
        return stockDAO.updatePrice(new Stock(stock.getStockID(), stock.getItemCode(), stock.getQtyOnHand(), stock.getSellingPrice()));
    }


}
