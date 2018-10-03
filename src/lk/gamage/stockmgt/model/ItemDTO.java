package lk.gamage.stockmgt.model;

public class ItemDTO {
    String itemCode;
    String itemName;
    String brand;
    String modelNo;

    public ItemDTO() {
    }

    public ItemDTO(String itemCode, String itemName, String brand, String modelNo) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.brand = brand;
        this.modelNo = modelNo;
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

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", brand='" + brand + '\'' +
                ", modelNo='" + modelNo + '\'' +
                '}';
    }
}
