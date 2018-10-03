package lk.gamage.stockmgt.model;

public class AuthorizeGRNTableDTO {
    private String stockID;
    private String itemCode;
    private String itemName;
    private String brand;
    private String modelNo;
    private double buyingPrice;
    private int qty;

    public AuthorizeGRNTableDTO() {
    }

    public AuthorizeGRNTableDTO(String stockID, String itemCode, String itemName, String brand, String modelNo, double buyingPrice, int qty) {
        this.stockID = stockID;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
        this.buyingPrice = buyingPrice;
        this.qty = qty;
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

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "AuthorizeGRNTableDTO{" +
                "stockID='" + stockID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", brand='" + brand + '\'' +
                ", modelNo='" + modelNo + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", qty=" + qty +
                '}';
    }
}
