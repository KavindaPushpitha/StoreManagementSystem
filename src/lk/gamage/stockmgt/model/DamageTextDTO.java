package lk.gamage.stockmgt.model;

public class DamageTextDTO {
    private String customerID;
    private String customerName;
    private String nic;
    private String orderDate;

    public DamageTextDTO() {
    }

    public DamageTextDTO(String customerID, String customerName, String nic, String orderDate) {
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

    @Override
    public String toString() {
        return "WarrantyTextDTO{" +
                "customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", nic='" + nic + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
