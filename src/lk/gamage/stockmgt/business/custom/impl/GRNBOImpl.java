package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.custom.GRNBO;
import lk.gamage.stockmgt.business.custom.GRNDetailBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.GRNDAO;
import lk.gamage.stockmgt.dao.custom.GRNDetailDAO;
import lk.gamage.stockmgt.dao.custom.QueryDAO;
import lk.gamage.stockmgt.dao.custom.StockDAO;
import lk.gamage.stockmgt.dao.custom.impl.GRNDAOImpl;
import lk.gamage.stockmgt.dao.custom.impl.GRNDetailDAOImpl;
import lk.gamage.stockmgt.dao.custom.impl.QueryDAOImpl;
import lk.gamage.stockmgt.dao.custom.impl.StockDAOImpl;
import lk.gamage.stockmgt.db.DBConnection;
import lk.gamage.stockmgt.entity.*;
import lk.gamage.stockmgt.model.GRNDTO;
import lk.gamage.stockmgt.model.GRNDetailDTO;
import lk.gamage.stockmgt.model.ViewGRNTableDTO;

import java.sql.Connection;
import java.util.ArrayList;

public class GRNBOImpl implements GRNBO {
    private GRNDAO grndao;
    private GRNDetailDAO grnDetailDao;
    private StockDAO stockDAO;
    private QueryDAO queryDAO;


    public GRNBOImpl() {
        grndao = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GRN);
        grnDetailDao = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GRN_DETAIL);
        stockDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STOCK);
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    @Override
    public boolean addGRN(GRNDTO grndto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {

            if (!grndao.save(new GRN(grndto.getGrnID(), grndto.getSupplierOrderID(), grndto.getGrnDate(), grndto.getSupplierPayment(), grndto.getStatus()))) {
                connection.rollback();
                return false;
            }
            for (GRNDetailDTO grnDetailDTO : grndto.getGrnDetailDTOS()) {
                if (!grnDetailDao.save(new GRNDetail(grnDetailDTO.getGrnID(), grnDetailDTO.getItemCode(), grnDetailDTO.getStockID(), grnDetailDTO.getBuyingPrice(), grnDetailDTO.getQty()))) {
                    connection.rollback();
                    return false;
                }
                Stock search = stockDAO.search(grnDetailDTO.getItemCode());
                if (search != null) {
                    int currentQty = search.getQtyOnHand();
                    currentQty = currentQty + grnDetailDTO.getQty();
                    double sellingPrice = search.getSellingPrice();
                    search.setQtyOnHand(currentQty);
                }
                if (!stockDAO.update(search)) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean updateGRN(GRNDTO grndto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean result = grndao.update(new GRN(grndto.getGrnID(), grndto.getSupplierOrderID(), grndto.getGrnDate(), grndto.getSupplierPayment(), grndto.getStatus()));
            if (!result) {
                return false;
            }
            for (GRNDetailDTO g : grndto.getGrnDetailDTOS()) {
                Stock search = stockDAO.search(g.getItemCode());
                GRNDetail grnDetail = grnDetailDao.search(g.getGrnID(), g.getItemCode(), g.getStockID());
                int detailQty = grnDetail.getQty();
                System.out.println("detai Qty" + detailQty);
                int gQty = g.getQty();
                System.out.println("new Qty" + gQty);
                if (detailQty > gQty) {
                    int onHand = search.getQtyOnHand();
                    System.out.println("On Hnad" + onHand);
                    onHand = onHand - (detailQty - g.getQty());
                    System.out.println("new On Hand" + onHand);
                    search.setQtyOnHand(onHand);
                    result = stockDAO.updateAll(search);
                    if (!result) {
                        connection.rollback();
                        return false;
                    }
                    result = grnDetailDao.update(new GRNDetail(g.getGrnID(), g.getItemCode(), g.getStockID(), g.getBuyingPrice(), g.getQty()));
                    if (!result) {
                        connection.rollback();
                        return false;
                    }
                } else if (detailQty < gQty) {
                    int onHand = search.getQtyOnHand();
                    System.out.println("onhand" + onHand);
                    onHand = onHand + (g.getQty() - detailQty);
                    System.out.println("now Onhand" + onHand);
                    search.setQtyOnHand(onHand);
                    result = stockDAO.updateAll(search);
                    result = grnDetailDao.update(new GRNDetail(g.getGrnID(), g.getItemCode(), g.getStockID(), g.getBuyingPrice(), g.getQty()));
                    if (!result) {
                        connection.rollback();
                        return false;
                    }
                }
                result = grnDetailDao.update(new GRNDetail(g.getGrnID(), g.getItemCode(), g.getStockID(), g.getBuyingPrice(), g.getQty()));
                if (!result) {
                    connection.rollback();
                    return false;
                }

            }
            connection.commit();
            return true;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean deleteGRN(String grndtoID) throws Exception {
        return false;
    }

    @Override
    public boolean deleteGRNDetails(GRNDetailDTO grnDetailDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean result = grnDetailDao.delete(new GRNDetail_PK(grnDetailDTO.getGrnID(), grnDetailDTO.getItemCode(), grnDetailDTO.getStockID()));
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

            result = grndao.delete(grnDetailDTO.getGrnID());
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
    public GRNDTO searchGRN(String grndtoID) throws Exception {
        GRN search = grndao.search(grndtoID);
        if (search != null) {
            return new GRNDTO(search.getGrnID(), search.getSupplierOrderID(), search.getGrnDate(), search.getSupplierPayment(), search.getStatus(), null);
        }
        return null;
    }

    @Override
    public ArrayList<GRNDTO> getAllGRN() throws Exception {
        ArrayList<GRN> all = grndao.getAll();
        ArrayList<GRNDTO> grndtos = new ArrayList<>();
        for (GRN grn : all) {
            grndtos.add(new GRNDTO(grn.getGrnID(), grn.getSupplierOrderID(), grn.getGrnDate(), grn.getSupplierPayment(), grn.getStatus(), null));
        }
        return grndtos;
    }

    @Override
    public ArrayList<ViewGRNTableDTO> getAllGRNQuery(String date, String name) throws Exception {
        ArrayList<CustomEntity> allGRNQueryDetails = queryDAO.getAllGRNQueryDetails(date, name);
        ArrayList<ViewGRNTableDTO> grnTableDTOS = new ArrayList<>();
        for (CustomEntity customEntity : allGRNQueryDetails) {
            grnTableDTOS.add(new ViewGRNTableDTO(customEntity.getGrnID(), customEntity.getSupplierOrderID(), customEntity.getSupplierName(), customEntity.getCompanyName(), customEntity.getSupplierOrderDate(), customEntity.getGrnDate(), customEntity.getSupplierPayment()));
        }
        return grnTableDTOS;
    }


}
