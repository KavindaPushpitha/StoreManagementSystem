package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.QueryDAO;
import lk.gamage.stockmgt.entity.CustomEntity;
import lk.gamage.stockmgt.entity.GRNDetail;
import lk.gamage.stockmgt.model.GRNDetailDTO;

import java.sql.ResultSet;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomEntity> getAllStock() throws Exception {
        ArrayList<CustomEntity> allStock = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("select s.stockID,s.itemCode,s.qtyOnHand,s.sellingPrice,i.itemName,i.brand,i.modelNo from Stock s,Items i where s.itemCode=i.itemCode ");
        while (rst.next()) {
            allStock.add(new CustomEntity(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getDouble(4), rst.getString(5), rst.getString(6), rst.getString(7)));
        }
        return allStock;
    }

    @Override
    public ArrayList<CustomEntity> getAllGRNDetail(String ID) throws Exception {
        ResultSet rst = CrudUtil.executeQuery(" select g.stockId,g.itemCode,itemName,brand,modelNo,qty,buyingPrice from GRNDetail g,Stock s,Items i where g.stockId=s.stockId and s.itemCode=i.itemCode and g.grnId=? ", ID);
        ArrayList<CustomEntity> grnDetails = new ArrayList<>();
        while (rst.next()) {
            grnDetails.add(new CustomEntity(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getInt(6), rst.getDouble(7)));
        }
        return grnDetails;
    }

    @Override
    public boolean delete(String grnID, String stockId) throws Exception {
        return CrudUtil.executeUpdate("Delete from GRNDetail where grnid=? and stockID=? ", grnID, stockId) > 0;
    }

    @Override
    public ArrayList<CustomEntity> getAllSupplierDetails(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select i.itemCode,itemName,brand,modelNo,so.supplierOrderId,supplierOrderDate,qty from Items i, Supplier s,SupplierOrders so,SupplierOrderDetail sd where s.supplierId=so.supplierId and so.supplierOrderId=sd.supplierOrderId and s.supplierId=? and i.itemCode=sd.itemCode ", id);
        ArrayList<CustomEntity> supplier = new ArrayList<>();
        while (rst.next()) {
            supplier.add(new CustomEntity(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getInt(7)));
        }
        return supplier;
    }

    @Override
    public ArrayList<CustomEntity> getAllCustomerOrders(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select i.itemCode,d.orderId,itemName,brand,modelNo,orderDate,unitPrice,orderqty from Items i,CustomerOrders c,OrderDetail d where i.itemcode=d.itemcode and c.orderid=d.orderid and customerid=? ", id);
        ArrayList<CustomEntity> customEntities = new ArrayList<>();
        while (rst.next()) {
            customEntities.add(new CustomEntity(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getDouble(7), rst.getInt(8)));
        }
        return customEntities;
    }

    public ArrayList<CustomEntity> getAllGRNQueryDetails(String date, String name) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select distinct g.grnid,g.supplierorderid,suppliername,companyname,supplierOrderDate,grnDate,supplierpayment from GRN g,GRNDetail gd,Supplier s,SupplierOrders so where g.supplierorderid=so.supplierorderid and so.supplierid=s.supplierid and supplierName=? and grndate=? ", name, date);
        ArrayList<CustomEntity> customEntities = new ArrayList<>();
        while (rst.next()) {
            customEntities.add(new CustomEntity(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getDouble(7)));
        }
        return customEntities;
    }

    @Override
    public ArrayList<CustomEntity> getAllWarrantyQuery(String customerID, String orderDate) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select c.orderID,od.itemCode,od.WarrantyID,itemName,Brand,ModelNo,warrantyPeriod,warrantyFrom,warrantyTo,Orderqty from Warranty w,Items i,CustomerOrders c,OrderDetail od where w.warrantyId=od.warrantyID and c.orderID=od.orderID and i.itemCode=od.itemCode and customerID=? and orderDate=? ", customerID, orderDate);
        ArrayList<CustomEntity> c = new ArrayList<>();
        while (rst.next()) {
            c.add(new CustomEntity(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getInt(10)));
        }
        return c;
    }

    @Override
    public CustomEntity getAllCustomerDetail(String orderID) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select c.customerID,customerName,nic,orderDate from Customer c,CustomerOrders cs where c.customerId=cs.customerId and orderID=? ", orderID);
        ArrayList<CustomEntity> c = new ArrayList<>();
        if (rst.next()) {
            return new CustomEntity(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<CustomEntity> getAllDamageItemTable() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select damageID,i.ItemCode,d.orderID,cd.customerID,customerName,nic,itemName,brand,modelNo,fault,damageType,DamageDate from Items i,DamageItem d,Customer c,CustomerOrders cd where i.itemCode=d.itemCode and d.orderID=cd.orderID and c.customerID=cd.customerID ");
        ArrayList<CustomEntity> c = new ArrayList<>();
        while (rst.next()) {
            c.add(new CustomEntity(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12)));
        }
        return c;
    }

    @Override
    public CustomEntity searchAllDamageItemTable(String damageID) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select damageID,i.ItemCode,d.orderID,cd.customerID,customerName,nic,itemName,brand,modelNo,fault,damageType,DamageDate from Items i,DamageItem d,Customer c,CustomerOrders cd where i.itemCode=d.itemCode and d.orderID=cd.orderID and c.customerID=cd.customerID and damageID=? ", damageID);
        if (rst.next()) {
            return new CustomEntity(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12));
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<CustomEntity> getAllReturnItemTable() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select r.returnID,r.damageID,r.ItemCode,CustomerName,Contact,ItemName,Brand,ModelNo,DamageDate,ReturnDate,status from ReturnItem r,Customer c,DamageItem d,Items i,CustomerOrders o where  d.damageID=r.damageID and i.itemCode=r.itemCode and d.orderID=o.orderID and c.customerID=o.customerID");
        ArrayList<CustomEntity> c = new ArrayList<>();
        while (rst.next()) {
            c.add(new CustomEntity(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11)));
        }
        return c;
    }

    @Override
    public CustomEntity searchAllDamageItemTable(String itemName, String date) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select damageID,i.ItemCode,d.orderID,cd.customerID,customerName,nic,itemName,brand,modelNo,fault,damageType,DamageDate from Items i,DamageItem d,Customer c,CustomerOrders cd where i.itemCode=d.itemCode and d.orderID=cd.orderID and c.customerID=cd.customerID and itemName=? and damageDate=? ", itemName, date);
        if (rst.next()) {
            return new CustomEntity(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12));
        } else {
            return null;
        }
    }
}
