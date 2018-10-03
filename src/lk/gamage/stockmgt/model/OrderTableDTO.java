package lk.gamage.stockmgt.model;

public class OrderTableDTO {
    private String itemCode;
    private String itemDescriptiom;
    private String warrantyID;
    private String warrantyPeriod;
    private String from;
    private String to;
    private double unitPrice;
    private int orderQty;
    private double total;

    public OrderTableDTO() {
    }

    public OrderTableDTO(String itemCode, String itemDescriptiom, String warrantyID, String warrantyPeriod, String from, String to, double unitPrice, int orderQty, double total) {
        this.itemCode = itemCode;
        this.itemDescriptiom = itemDescriptiom;
        this.warrantyID = warrantyID;
        this.warrantyPeriod = warrantyPeriod;
        this.from = from;
        this.to = to;
        this.unitPrice = unitPrice;
        this.orderQty = orderQty;
        this.total = total;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescriptiom() {
        return itemDescriptiom;
    }

    public void setItemDescriptiom(String itemDescriptiom) {
        this.itemDescriptiom = itemDescriptiom;
    }

    public String getWarrantyID() {
        return warrantyID;
    }

    public void setWarrantyID(String warrantyID) {
        this.warrantyID = warrantyID;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderTableDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", itemDescriptiom='" + itemDescriptiom + '\'' +
                ", warrantyID='" + warrantyID + '\'' +
                ", warrantyPeriod='" + warrantyPeriod + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", unitPrice=" + unitPrice +
                ", orderQty=" + orderQty +
                ", total=" + total +
                '}';
    }
}
