package lk.gamage.stockmgt.model;

public class StockTableDTO {
    private String stockID;
    private String itemCode;
    private String modelNo;
    private String itemName;
    private String brand;
    private double sellingPrice;
    private int qtyOnHand;

    public StockTableDTO() {
    }

    public StockTableDTO(String stockID, String itemCode, String modelNo, String itemName, String brand, double sellingPrice, int qtyOnHand) {
        this.stockID = stockID;
        this.itemCode = itemCode;
        this.modelNo = modelNo;
        this.itemName = itemName;
        this.brand = brand;
        this.sellingPrice = sellingPrice;
        this.qtyOnHand = qtyOnHand;
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

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
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

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "StockTableDTO{" +
                "stockID='" + stockID + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", modelNo='" + modelNo + '\'' +
                ", itemName='" + itemName + '\'' +
                ", brand='" + brand + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
