package lk.gamage.stockmgt.entity;

import javax.naming.Name;

public class CustomEntity {
    private String stockID;
    private String itemCode;
    private int qtyOnHand;
    private double sellingPrice;
    private String itemName;
    private String brand;
    private String modelNo;
    private String supplierOrderID;
    private String date;
    private int qty;
    private double buyingPrice;
    private String orderID;
    private double unitPrice;

    private String damageID;
    private String customerID;
    private String customerName;
    private String nic;
    private String fault;
    private String damageType;
    private String damageDate;

    private String returnID;
    private String contact;
    private String returnDate;
    private String status;

    public String getReturnID() {
        return returnID;
    }

    public void setReturnID(String returnID) {
        this.returnID = returnID;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomEntity(String returnID, String damageID, String itemCode, String customerName, String contact, String itemName, String brand, String modelNo, String damageDate, String returnDate, String status) {
        this.returnID = returnID;
        this.damageID = damageID;
        this.itemCode = itemCode;
        this.customerName = customerName;
        this.contact = contact;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
        this.damageDate = damageDate;
        this.returnDate = returnDate;
        this.status = status;

    }

    public String getDamageID() {
        return damageID;
    }

    public void setDamageID(String damageID) {
        this.damageID = damageID;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getDamageDate() {
        return damageDate;
    }

    public void setDamageDate(String damageDate) {
        this.damageDate = damageDate;
    }

    public CustomEntity(String damageID, String itemCode, String orderID, String customerID, String customerName, String nic, String itemName, String brand, String modelNo, String fault, String damageType, String damageDate) {
        this.damageID = damageID;
        this.itemCode = itemCode;
        this.orderID = orderID;
        this.customerID = customerID;
        this.customerName = customerName;
        this.nic = nic;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
        this.fault = fault;
        this.damageType = damageType;
        this.damageDate = damageDate;
    }

    private String grnID;
    private String supplierName;
    private String companyName;
    private String orderDate;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public CustomEntity(String customerID, String customerName, String nic, String orderDate) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.nic = nic;
        this.orderDate = orderDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getWarrantyID() {
        return warrantyID;
    }

    public void setWarrantyID(String warrantyID) {
        this.warrantyID = warrantyID;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getWarrantyFrom() {
        return warrantyFrom;
    }

    public void setWarrantyFrom(String warrantyFrom) {
        this.warrantyFrom = warrantyFrom;
    }

    public String getWarrantyTo() {
        return warrantyTo;
    }

    public void setWarrantyTo(String warrantyTo) {
        this.warrantyTo = warrantyTo;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    private String warrantyID;
    private String warrantyPeriod;
    private String warrantyFrom;
    private String warrantyTo;
    private int orderQty;

    public CustomEntity(String orderID, String itemCode, String warrantyID, String itemName, String brand, String modelNo, String warrantyPeriod, String warrantyFrom, String warrantyTo, int orderQty) {
        this.orderID = orderID;
        this.itemCode = itemCode;
        this.warrantyID = warrantyID;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
        this.warrantyPeriod = warrantyPeriod;
        this.warrantyFrom = warrantyFrom;
        this.warrantyTo = warrantyTo;
        this.orderQty = orderQty;
    }

    public String getSupplierOrderDate() {
        return supplierOrderDate;
    }

    public void setSupplierOrderDate(String supplierOrderDate) {
        this.supplierOrderDate = supplierOrderDate;
    }

    public String getGrnDate() {
        return grnDate;
    }

    public void setGrnDate(String grnDate) {
        this.grnDate = grnDate;
    }

    private String supplierOrderDate;
    private String grnDate;
    private double supplierPayment;

    public CustomEntity(String grnID, String supplierOrderID, String supplierName, String companyName, String supplierOrderDate, String grnDate, double supplierPayment) {
        this.grnID = grnID;
        this.supplierOrderID = supplierOrderID;
        this.supplierName = supplierName;
        this.companyName = companyName;
        this.supplierOrderDate = supplierOrderDate;
        this.grnDate = grnDate;
        this.supplierPayment = supplierPayment;
    }

    public String getGrnID() {
        return grnID;
    }

    public void setGrnID(String grnID) {
        this.grnID = grnID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getSupplierPayment() {
        return supplierPayment;
    }

    public void setSupplierPayment(double supplierPayment) {
        this.supplierPayment = supplierPayment;
    }

    public CustomEntity(String itemCode, String orderID, String itemName, String brand, String modelNo, String date, double unitPrice, int qty) {
        this.itemCode = itemCode;
        this.orderID = orderID;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
        this.date = date;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSupplierOrderID() {
        return supplierOrderID;
    }

    public void setSupplierOrderID(String supplierOrderID) {
        this.supplierOrderID = supplierOrderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public CustomEntity(String itemCode, String itemName, String brand, String modelNo, String supplierOrderID, String date, int qty) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
        this.supplierOrderID = supplierOrderID;
        this.date = date;
        this.qty = qty;

    }

    public CustomEntity(String stockID, String itemCode, String itemName, String brand, String modelNo, int qty, double buyingPrice) {
        this.stockID = stockID;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
        this.qty = qty;
        this.buyingPrice = buyingPrice;

    }

    public CustomEntity() {
    }

    public CustomEntity(String stockID, String itemCode, int qtyOnHand, double sellingPrice, String itemName, String brand, String modelNo) {
        this.stockID = stockID;
        this.itemCode = itemCode;
        this.qtyOnHand = qtyOnHand;
        this.sellingPrice = sellingPrice;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }
}
