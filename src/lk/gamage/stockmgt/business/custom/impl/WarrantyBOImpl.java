package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.custom.WarrantyBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.QueryDAO;
import lk.gamage.stockmgt.dao.custom.WarrantyDAO;
import lk.gamage.stockmgt.dao.custom.impl.QueryDAOImpl;
import lk.gamage.stockmgt.dao.custom.impl.WarrantyDAOImpl;
import lk.gamage.stockmgt.entity.CustomEntity;
import lk.gamage.stockmgt.entity.Warranty;
import lk.gamage.stockmgt.model.ManageWarrantyTableDTO;
import lk.gamage.stockmgt.model.WarrantyDTO;

import java.util.ArrayList;

public class WarrantyBOImpl implements WarrantyBO {
    private QueryDAO queryDAO;
    private WarrantyDAO warrantyDAO;

    public WarrantyBOImpl() {
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
        warrantyDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.WARRANTY);
    }

    @Override
    public boolean addWarranty(WarrantyDTO warranty) throws Exception {
        return false;
    }

    @Override
    public boolean updateWarranty(WarrantyDTO warrantyDTO) throws Exception {
        return warrantyDAO.update(new Warranty(warrantyDTO.getWarrantyID(), warrantyDTO.getWarrantyPeriod(), warrantyDTO.getWarrantyFrom(), warrantyDTO.getWarrantyTo()));
    }

    @Override
    public boolean deleteWarranty(String warrantyID) throws Exception {
        return false;
    }

    @Override
    public WarrantyDTO searchWarranty(String warrantyID) throws Exception {
        return null;
    }

    @Override
    public String getWarrantyID() throws Exception {
        return warrantyDAO.getWarrantyID();
    }

    @Override
    public ArrayList<ManageWarrantyTableDTO> getAllWarrantys(String customerID, String orderDate) throws Exception {
        ArrayList<CustomEntity> allWarrantyQuery = queryDAO.getAllWarrantyQuery(customerID, orderDate);
        ArrayList<ManageWarrantyTableDTO> warranty = new ArrayList<>();
        for (CustomEntity c : allWarrantyQuery) {
            warranty.add(new ManageWarrantyTableDTO(c.getOrderID(), c.getItemCode(), c.getWarrantyID(), c.getItemName(), c.getBrand(), c.getModelNo(), c.getWarrantyPeriod(), c.getWarrantyFrom(), c.getWarrantyTo(), c.getOrderQty()));
        }
        return warranty;
    }

}
