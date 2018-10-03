package lk.gamage.stockmgt.model;

public class DamageItemDTO {
    private String damageID;
    private String itemCode;
    private String fault;
    private String damageType;
    private String date;
    private String orderID;

    public DamageItemDTO() {
    }

    public DamageItemDTO(String damageID, String itemCode, String fault, String damageType, String date, String orderID) {
        this.damageID = damageID;
        this.itemCode = itemCode;
        this.fault = fault;
        this.damageType = damageType;
        this.date = date;
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

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DamageItemDTO{" +
                "damageID='" + damageID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", fault='" + fault + '\'' +
                ", damageType='" + damageType + '\'' +
                ", orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
