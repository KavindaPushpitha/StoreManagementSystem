package lk.gamage.stockmgt.dao.custom;

import lk.gamage.stockmgt.dao.CrudDAO;
import lk.gamage.stockmgt.entity.GRNDetail;
import lk.gamage.stockmgt.entity.GRNDetail_PK;

import java.util.ArrayList;

public interface GRNDetailDAO extends CrudDAO<GRNDetail, GRNDetail_PK> {
    public ArrayList<GRNDetail> getAll(String id) throws Exception;

    public GRNDetail search(String id, String itemcode, String stockID) throws Exception;
}
