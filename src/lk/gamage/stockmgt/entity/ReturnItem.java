package lk.gamage.stockmgt.entity;

public class ReturnItem {
    private String returnID;
    private String damageID;
    private String itemCode;
    private String returnDate;
    private String status;

    public ReturnItem() {
    }

    public ReturnItem(String returnID, String damageID, String itemCode, String returnDate, String status) {
        this.returnID = returnID;
        this.damageID = damageID;
        this.itemCode = itemCode;
        this.returnDate = returnDate;
        this.status = status;
    }

    public String getReturnID() {
        return returnID;
    }

    public void setReturnID(String returnID) {
        this.returnID = returnID;
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

    @Override
    public String toString() {
        return "ReturnItem{" +
                "returnID='" + returnID + '\'' +
                ", damageID='" + damageID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
