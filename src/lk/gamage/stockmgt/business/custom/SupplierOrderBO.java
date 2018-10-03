package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.model.SupplierOrderDTO;
import lk.gamage.stockmgt.model.ViewSupplierOrderTableDTO;

import java.util.ArrayList;

public interface SupplierOrderBO extends SuperBO {
    public boolean addSupplierOrder(SupplierOrderDTO supplierOrder) throws Exception;

    public boolean updateSupplierOrder(SupplierOrderDTO supplierOrder) throws Exception;

    public boolean deleteSupplierOrder(String supplierOrderID) throws Exception;

    public SupplierOrderDTO searchSupplierOrder(String supplierOrderID) throws Exception;

    public ArrayList<ViewSupplierOrderTableDTO> getAllSupplierOrder(String id) throws Exception;

    public ArrayList<String> getSupplierOrderIDs() throws Exception;
}
