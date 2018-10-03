package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.model.ManageWarrantyTableDTO;
import lk.gamage.stockmgt.model.WarrantyDTO;

import java.util.ArrayList;

public interface WarrantyBO extends SuperBO {
    public boolean addWarranty(WarrantyDTO warranty) throws Exception;

    public boolean updateWarranty(WarrantyDTO warrantyDTO) throws Exception;

    public boolean deleteWarranty(String warrantyID) throws Exception;

    public WarrantyDTO searchWarranty(String warrantyID) throws Exception;

    public String getWarrantyID() throws Exception;

    public ArrayList<ManageWarrantyTableDTO> getAllWarrantys(String customerID, String orderDate) throws Exception;

}
