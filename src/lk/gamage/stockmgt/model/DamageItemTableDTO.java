package lk.gamage.stockmgt.model;

public class DamageItemTableDTO {
    private String damageID;
    private String itemCode;
    private String orderID;
    private String customerID;
    private String customerName;
    private String nic;
    private String itemName;
    private String brand;
    private String modelNo;
    private String fault;
    private String damageType;
    private String damageDate;

    public DamageItemTableDTO() {
    }

    public DamageItemTableDTO(String damageID, String itemCode, String orderID, String customerID, String customerName, String nic, String itemName, String brand, String modelNo, String fault, String damageType, String damageDate) {
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

    public String getDamageID() {
        return damageID;
    }

    public void setDamageID(String damageID) {
        this.damageID = damageID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

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

    @Override
    public String toString() {
        return "DamageItemTableDTO{" +
                "damageID='" + damageID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", nic='" + nic + '\'' +
                ", itemName='" + itemName + '\'' +
                ", brand='" + brand + '\'' +
                ", modelNo='" + modelNo + '\'' +
                ", fault='" + fault + '\'' +
                ", damageType='" + damageType + '\'' +
                ", damageDate='" + damageDate + '\'' +
                '}';
    }
}
