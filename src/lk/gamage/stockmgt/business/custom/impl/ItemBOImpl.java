package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.custom.ItemBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.ItemsDAO;
import lk.gamage.stockmgt.entity.Items;
import lk.gamage.stockmgt.model.ItemDTO;

import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    private ItemsDAO itemsDAO;

    public ItemBOImpl() {
        itemsDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEMS);
    }

    @Override
    public boolean addItem(ItemDTO item) throws Exception {
        return itemsDAO.save(new Items(item.getItemCode(), item.getItemName(), item.getBrand(), item.getModelNo()));

    }

    @Override
    public boolean updateItem(ItemDTO item) throws Exception {
        return itemsDAO.update((new Items(item.getItemCode(), item.getItemName(), item.getBrand(), item.getModelNo())));
    }

    @Override
    public boolean deleteItem(String itemCode) throws Exception {
        return itemsDAO.delete(itemCode);
    }

    @Override
    public ItemDTO searchItem(String itemCode) throws Exception {
        Items search = itemsDAO.search(itemCode);
        return new ItemDTO(search.getItemCode(), search.getItemName(), search.getBrand(), search.getModelNo());
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws Exception {
        ArrayList<Items> all = itemsDAO.getAll();
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        for (Items items : all) {
            allItems.add(new ItemDTO(items.getItemCode(), items.getItemName(), items.getBrand(), items.getModelNo()));
        }
        return allItems;
    }

    @Override
    public ArrayList<String> getItemCodes() throws Exception {
        return null;
    }
}
