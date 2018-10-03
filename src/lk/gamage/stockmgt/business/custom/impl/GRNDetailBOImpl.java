package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.custom.GRNDetailBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.GRNDAO;
import lk.gamage.stockmgt.dao.custom.GRNDetailDAO;
import lk.gamage.stockmgt.dao.custom.QueryDAO;
import lk.gamage.stockmgt.dao.custom.StockDAO;
import lk.gamage.stockmgt.dao.custom.impl.GRNDetailDAOImpl;
import lk.gamage.stockmgt.dao.custom.impl.QueryDAOImpl;
import lk.gamage.stockmgt.db.DBConnection;
import lk.gamage.stockmgt.entity.*;
import lk.gamage.stockmgt.model.AuthorizeGRNTableDTO;
import lk.gamage.stockmgt.model.GRNDetailDTO;

import java.sql.Connection;
import java.util.ArrayList;

public class GRNDetailBOImpl implements GRNDetailBO {
    private QueryDAO queryDAO;
    private GRNDetailDAO grnDetailDAO;
    private StockDAO stockDAO;
    private GRNDAO grndao;

    public GRNDetailBOImpl() {
        grndao = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GRN);
        stockDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STOCK);
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
        grnDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GRN_DETAIL);
    }

    @Override
    public boolean addGRNDetail(GRNDetailDTO grndetaildto) throws Exception {
        return false;
    }

    @Override
    public boolean updateGRNDetail(GRNDetailDTO grndetaildto) throws Exception {
        return grnDetailDAO.update(new GRNDetail(grndetaildto.getGrnID(), grndetaildto.getItemCode(), grndetaildto.getStockID(), grndetaildto.getBuyingPrice(), grndetaildto.getQty()));
    }

    @Override
    public boolean deleteGRNDetail(GRNDetailDTO grnDetailDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean result = grnDetailDAO.delete(new GRNDetail_PK(grnDetailDTO.getGrnID(), grnDetailDTO.getItemCode(), grnDetailDTO.getStockID()));
            if (!result) {
                return false;
            }
            int qty = grnDetailDTO.getQty();
            Stock search = stockDAO.search(grnDetailDTO.getItemCode());

            int qtyOnHand = search.getQtyOnHand();
            qtyOnHand = qtyOnHand - qty;
            search.setQtyOnHand(qtyOnHand);
            result = stockDAO.update(search);
            if (!result) {
                connection.rollback();
                return false;
            }

            GRN grn = grndao.searchFormID(grnDetailDTO.getGrnID());
            double supplierPayment = grn.getSupplierPayment();
            double buyingPrice = grnDetailDTO.getBuyingPrice();
            int buyQty = grnDetailDTO.getQty();
            supplierPayment = supplierPayment - (buyingPrice * buyQty);
            grn.setSupplierPayment(supplierPayment);
            result = grndao.update(grn);
            if (!result) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;
        } finally {
            connection.setAutoCommit(true);
        }
    }


    @Override
    public GRNDetailDTO searchGRNDetail(String grnID, String itemCode, String stockID) throws Exception {
        return null;
    }


    @Override
    public ArrayList<GRNDetailDTO> getAllGRNDetail() throws Exception {
        return null;
    }

    @Override
    public ArrayList<AuthorizeGRNTableDTO> getAllGRNDetail(String ID) throws Exception {
        ArrayList<CustomEntity> all = queryDAO.getAllGRNDetail(ID);
        ArrayList<AuthorizeGRNTableDTO> grnDetailDTOS = new ArrayList<>();
        for (CustomEntity customEntity : all) {
            grnDetailDTOS.add(new AuthorizeGRNTableDTO(customEntity.getStockID(), customEntity.getItemCode(), customEntity.getItemName(), customEntity.getBrand(), customEntity.getModelNo(), customEntity.getBuyingPrice(), customEntity.getQty()));
        }
        return grnDetailDTOS;
    }


}
