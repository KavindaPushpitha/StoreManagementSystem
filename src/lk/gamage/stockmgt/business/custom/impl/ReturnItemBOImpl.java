package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.custom.ReturnItemBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.QueryDAO;
import lk.gamage.stockmgt.dao.custom.ReturnItemDAO;
import lk.gamage.stockmgt.entity.CustomEntity;
import lk.gamage.stockmgt.entity.ReturnItem;
import lk.gamage.stockmgt.model.ReturnItemDTO;
import lk.gamage.stockmgt.model.ReturnItemTableDTO;

import java.util.ArrayList;

public class ReturnItemBOImpl implements ReturnItemBO {
    private ReturnItemDAO returnItemDAO;
    private QueryDAO queryDAO;

    public ReturnItemBOImpl() {
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
        returnItemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETURN_ITEM);
    }

    @Override
    public boolean addReturnItem(ReturnItemDTO item) throws Exception {
        return returnItemDAO.save(new ReturnItem(item.getReturnID(), item.getDamageID(), item.getItemCode(), item.getReturnDate(), item.getStatus()));
    }

    @Override
    public boolean updateReturnItem(ReturnItemDTO item) throws Exception {
        return returnItemDAO.update(new ReturnItem(item.getReturnID(), item.getDamageID(), item.getItemCode(), item.getReturnDate(), item.getStatus()));
    }

    @Override
    public boolean deleteReturnItem(String returnID) throws Exception {
        return returnItemDAO.delete(returnID);
    }

    @Override
    public ReturnItemDTO searchReturnItem(String returnID) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ReturnItemTableDTO> getAllReturnItem() throws Exception {
        ArrayList<CustomEntity> c = queryDAO.getAllReturnItemTable();
        ArrayList<ReturnItemTableDTO> dtos = new ArrayList<>();
        for (CustomEntity entity : c) {
            dtos.add(new ReturnItemTableDTO(entity.getReturnID(), entity.getDamageID(), entity.getItemCode(), entity.getCustomerName(), entity.getContact(), entity.getItemName(), entity.getBrand(), entity.getModelNo(), entity.getDamageDate(), entity.getReturnDate(), entity.getStatus()));
        }
        return dtos;

    }
}
