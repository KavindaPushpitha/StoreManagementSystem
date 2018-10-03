package lk.gamage.stockmgt.dao.custom;

import lk.gamage.stockmgt.dao.CrudDAO;
import lk.gamage.stockmgt.entity.GRN;

public interface GRNDAO extends CrudDAO<GRN, String> {
    public GRN searchFormID(String grnID) throws Exception;
}
