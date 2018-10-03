package lk.gamage.stockmgt.entity;

public class DamageItem {
    private String damageID;
    private String itemCode;
    private String fault;
    private String damageType;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    private String orderID;

    private String damageDate;

    public DamageItem() {
    }

    public DamageItem(String damageID, String itemCode, String fault, String damageType, String damageDate, String orderID) {
        this.damageID = damageID;
        this.itemCode = itemCode;
        this.fault = fault;
        this.damageType = damageType;
        this.damageDate = damageDate;
        this.orderID = orderID;
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
        return "DamageItem{" +
                "damageID='" + damageID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", fault='" + fault + '\'' +
                ", damageType='" + damageType + '\'' +
                ", damageDate='" + damageDate + '\'' +
                '}';
    }
}
