package lk.gamage.stockmgt.model;

public class CustomerDTO {
    private String customerID;
    private String customerName;
    private String dob;
    private String address;
    private String contact;
    private String nic;


    public CustomerDTO() {
    }

    public CustomerDTO(String customerID, String customerName, String dob, String address, String contact, String nic) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.dob = dob;
        this.address = address;
        this.contact = contact;
        this.nic = nic;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
