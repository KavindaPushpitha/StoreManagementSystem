package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.model.ItemDTO;

import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public boolean addItem(ItemDTO item) throws Exception;

    public boolean updateItem(ItemDTO item) throws Exception;

    public boolean deleteItem(String itemCode) throws Exception;

    public ItemDTO searchItem(String itemCode) throws Exception;

    public ArrayList<ItemDTO> getAllItem() throws Exception;

    public ArrayList<String> getItemCodes() throws Exception;
}
