package lk.gamage.stockmgt.dao.custom;

import lk.gamage.stockmgt.dao.CrudDAO;
import lk.gamage.stockmgt.entity.Warranty;

import java.util.ArrayList;

public interface WarrantyDAO extends CrudDAO<Warranty, String> {
    public String getWarrantyID() throws Exception;

}
