package lk.gamage.stockmgt.dao.custom;

import lk.gamage.stockmgt.dao.SuperDAO;
import lk.gamage.stockmgt.entity.CustomEntity;
import lk.gamage.stockmgt.model.GRNDetailDTO;

import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ArrayList<CustomEntity> getAllStock() throws Exception;

    public ArrayList<CustomEntity> getAllGRNDetail(String ID) throws Exception;

    public boolean delete(String grnID, String stockId) throws Exception;

    public ArrayList<CustomEntity> getAllSupplierDetails(String id) throws Exception;

    public ArrayList<CustomEntity> getAllCustomerOrders(String id) throws Exception;

    public ArrayList<CustomEntity> getAllGRNQueryDetails(String date, String name) throws Exception;

    public ArrayList<CustomEntity> getAllWarrantyQuery(String customerID, String orderDate) throws Exception;

    public CustomEntity getAllCustomerDetail(String orderID) throws Exception;

    public ArrayList<CustomEntity> getAllDamageItemTable() throws Exception;

    public CustomEntity searchAllDamageItemTable(String damageID) throws Exception;

    public ArrayList<CustomEntity> getAllReturnItemTable() throws Exception;

    public CustomEntity searchAllDamageItemTable(String itemName, String date) throws Exception;


}
