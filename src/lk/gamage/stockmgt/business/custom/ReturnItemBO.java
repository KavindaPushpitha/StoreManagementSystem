package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.model.ItemDTO;
import lk.gamage.stockmgt.model.ReturnItemDTO;
import lk.gamage.stockmgt.model.ReturnItemTableDTO;

import java.util.ArrayList;

public interface ReturnItemBO extends SuperBO {
    public boolean addReturnItem(ReturnItemDTO item) throws Exception;

    public boolean updateReturnItem(ReturnItemDTO item) throws Exception;

    public boolean deleteReturnItem(String returnID) throws Exception;

    public ReturnItemDTO searchReturnItem(String returnID) throws Exception;

    public ArrayList<ReturnItemTableDTO> getAllReturnItem() throws Exception;
}
