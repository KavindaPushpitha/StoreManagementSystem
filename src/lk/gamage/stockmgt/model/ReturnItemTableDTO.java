package lk.gamage.stockmgt.model;

public class ReturnItemTableDTO {
    private String returnID;
    private String damageID;
    private String itemCode;
    private String customerName;
    private String contact;
    private String itemName;
    private String brand;
    private String modelNo;
    private String damageDate;
    private String returnDate;
    private String status;

    public ReturnItemTableDTO() {
    }

    public ReturnItemTableDTO(String returnID, String damageID, String itemCode, String customerName, String contact, String itemName, String brand, String modelNo, String damageDate, String returnDate, String status) {
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getDamageDate() {
        return damageDate;
    }

    public void setDamageDate(String damageDate) {
        this.damageDate = damageDate;
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
        return "ReturnItemTableDTO{" +
                "returnID='" + returnID + '\'' +
                ", damageID='" + damageID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", customerName='" + customerName + '\'' +
                ", contact='" + contact + '\'' +
                ", itemName='" + itemName + '\'' +
                ", brand='" + brand + '\'' +
                ", modelNo='" + modelNo + '\'' +
                ", damageDate='" + damageDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
