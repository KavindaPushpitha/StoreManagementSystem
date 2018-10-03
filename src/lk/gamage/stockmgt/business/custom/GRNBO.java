package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.model.GRNDTO;
import lk.gamage.stockmgt.model.GRNDetailDTO;
import lk.gamage.stockmgt.model.ViewGRNTableDTO;

import java.util.ArrayList;

public interface GRNBO extends SuperBO {
    public boolean addGRN(GRNDTO grndto) throws Exception;

    public boolean updateGRN(GRNDTO grndto) throws Exception;

    public boolean deleteGRN(String grndtoID) throws Exception;

    public boolean deleteGRNDetails(GRNDetailDTO grnDetailDTO) throws Exception;

    public GRNDTO searchGRN(String grndtoID) throws Exception;

    public ArrayList<GRNDTO> getAllGRN() throws Exception;

    public ArrayList<ViewGRNTableDTO> getAllGRNQuery(String date, String name) throws Exception;
}
