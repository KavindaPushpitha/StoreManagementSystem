package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.custom.DamageItemBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.DamageItemDAO;
import lk.gamage.stockmgt.dao.custom.QueryDAO;
import lk.gamage.stockmgt.entity.CustomEntity;
import lk.gamage.stockmgt.entity.DamageItem;
import lk.gamage.stockmgt.model.DamageItemDTO;
import lk.gamage.stockmgt.model.DamageItemTableDTO;

import java.util.ArrayList;

public class DamageItemBOImpl implements DamageItemBO {
    private DamageItemDAO damageItemDAO;
    private QueryDAO queryDAO;

    public DamageItemBOImpl() {
        damageItemDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DAMAGE_ITEM);
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    @Override
    public boolean addDamageItem(DamageItemDTO damageItemDTO) throws Exception {
        return damageItemDAO.save(new DamageItem(damageItemDTO.getDamageID(), damageItemDTO.getItemCode(), damageItemDTO.getFault(), damageItemDTO.getDamageType(), damageItemDTO.getDate(), damageItemDTO.getOrderID()));
    }

    @Override
    public boolean updateDamageItem(DamageItemDTO damageItemDTO) throws Exception {
        return damageItemDAO.update(new DamageItem(damageItemDTO.getDamageID(), damageItemDTO.getItemCode(), damageItemDTO.getFault(), damageItemDTO.getDamageType(), damageItemDTO.getDate(), damageItemDTO.getOrderID()));
    }

    @Override
    public boolean deleteDamageItem(String damageID) throws Exception {
        return damageItemDAO.delete(damageID);
    }

    @Override
    public DamageItemTableDTO searchDamageItem(String damageID) throws Exception {
        CustomEntity c = queryDAO.searchAllDamageItemTable(damageID);
        if (c != null) {
            return new DamageItemTableDTO(c.getDamageID(), c.getItemCode(), c.getOrderID(), c.getCustomerID(), c.getCustomerName(), c.getNic(), c.getItemName(), c.getBrand(), c.getModelNo(), c.getFault(), c.getDamageType(), c.getDamageDate());
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<DamageItemTableDTO> getAllDamageItem() throws Exception {
        ArrayList<CustomEntity> allDamageItemTable = queryDAO.getAllDamageItemTable();
        ArrayList<DamageItemTableDTO> dtos = new ArrayList<>();
        for (CustomEntity c : allDamageItemTable) {
            dtos.add(new DamageItemTableDTO(c.getDamageID(), c.getItemCode(), c.getOrderID(), c.getCustomerID(), c.getCustomerName(), c.getNic(), c.getItemName(), c.getBrand(), c.getModelNo(), c.getFault(), c.getDamageType(), c.getDamageDate()));
        }
        return dtos;
    }

    @Override
    public ArrayList<DamageItemTableDTO> searchDamageItems(String itemName, String date) throws Exception {
        CustomEntity c = queryDAO.searchAllDamageItemTable(itemName, date);
        ArrayList<DamageItemTableDTO> d = new ArrayList<>();
        if (c != null) {
            d.add(new DamageItemTableDTO(c.getDamageID(), c.getItemCode(), c.getOrderID(), c.getCustomerID(), c.getCustomerName(), c.getNic(), c.getItemName(), c.getBrand(), c.getModelNo(), c.getFault(), c.getDamageType(), c.getDamageDate()));
        }
        return d;
    }
}
