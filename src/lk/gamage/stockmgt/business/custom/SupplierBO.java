package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.model.SupplierDTO;

import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    public boolean addSupplier(SupplierDTO supplier) throws Exception;

    public boolean updateSupplier(SupplierDTO supplier) throws Exception;

    public boolean deleteSupplier(String supplierID) throws Exception;

    public SupplierDTO searchSupplier(String supplierID) throws Exception;

    public ArrayList<SupplierDTO> getAllSupplier() throws Exception;

    public ArrayList<String> getSupplierIDs() throws Exception;
}
