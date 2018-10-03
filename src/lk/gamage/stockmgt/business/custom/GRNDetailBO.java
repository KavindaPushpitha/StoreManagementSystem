package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.entity.GRNDetail_PK;
import lk.gamage.stockmgt.model.AuthorizeGRNTableDTO;
import lk.gamage.stockmgt.model.GRNDetailDTO;

import java.util.ArrayList;

public interface GRNDetailBO extends SuperBO {
    public boolean addGRNDetail(GRNDetailDTO grndetaildto) throws Exception;

    public boolean updateGRNDetail(GRNDetailDTO grndetaildto) throws Exception;

    public boolean deleteGRNDetail(GRNDetailDTO grnDetailDTO) throws Exception;

    public GRNDetailDTO searchGRNDetail(String grnID, String itemCode, String stockID) throws Exception;

    public ArrayList<GRNDetailDTO> getAllGRNDetail() throws Exception;

    public ArrayList<AuthorizeGRNTableDTO> getAllGRNDetail(String ID) throws Exception;
}
