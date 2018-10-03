package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.model.DamageItemDTO;
import lk.gamage.stockmgt.model.DamageItemTableDTO;

import java.util.ArrayList;

public interface DamageItemBO extends SuperBO {
    public boolean addDamageItem(DamageItemDTO damageItemDTO) throws Exception;

    public boolean updateDamageItem(DamageItemDTO damageItemDTO) throws Exception;

    public boolean deleteDamageItem(String damageID) throws Exception;

    public DamageItemTableDTO searchDamageItem(String damageID) throws Exception;

    public ArrayList<DamageItemTableDTO> getAllDamageItem() throws Exception;

    public ArrayList<DamageItemTableDTO> searchDamageItems(String itemName, String date) throws Exception;

}
