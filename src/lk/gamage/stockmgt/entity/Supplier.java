package lk.gamage.stockmgt.entity;

public class Supplier {
    private String supplierID;
    private String supplierName;
    private String address;
    private String contact;
    private String companyName;
    private String companyAddress;
    private String companyContact;

    public Supplier() {
    }

    public Supplier(String supplierID, String supplierName, String address, String contact, String companyName, String companyAddress, String companyContact) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.address = address;
        this.contact = contact;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyContact = companyContact;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierID='" + supplierID + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyContact='" + companyContact + '\'' +
                '}';
    }
}
