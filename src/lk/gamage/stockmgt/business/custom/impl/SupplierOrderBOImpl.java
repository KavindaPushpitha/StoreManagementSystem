package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.custom.SupplierOrderBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.QueryDAO;
import lk.gamage.stockmgt.dao.custom.SupplierOrderDetailDAO;
import lk.gamage.stockmgt.dao.custom.SupplierOrdersDAO;
import lk.gamage.stockmgt.dao.custom.impl.QueryDAOImpl;
import lk.gamage.stockmgt.dao.custom.impl.SupplierOrderDetailDAOImpl;
import lk.gamage.stockmgt.dao.custom.impl.SupplierOrdersDAOImpl;
import lk.gamage.stockmgt.db.DBConnection;
import lk.gamage.stockmgt.entity.CustomEntity;
import lk.gamage.stockmgt.entity.SupplierOrderDetail;
import lk.gamage.stockmgt.entity.SupplierOrders;
import lk.gamage.stockmgt.model.SupplierOrderDTO;
import lk.gamage.stockmgt.model.SupplierOrderDetailDTO;
import lk.gamage.stockmgt.model.ViewSupplierOrderTableDTO;

import java.sql.Connection;
import java.util.ArrayList;

public class SupplierOrderBOImpl implements SupplierOrderBO {
    private SupplierOrdersDAO supplierOrdersDAO;
    private SupplierOrderDetailDAO supplierOrderDetailDAO;
    private QueryDAO queryDAO;

    public SupplierOrderBOImpl() {
        supplierOrderDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER_DETAIL);
        supplierOrdersDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDERS);
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    @Override
    public boolean addSupplierOrder(SupplierOrderDTO supplierOrder) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean result = supplierOrdersDAO.save(new SupplierOrders(supplierOrder.getSupplierOrderID(), supplierOrder.getSupplierID(), supplierOrder.getSupplierOrderDate()));
            if (!result) {
                return false;
            }
            for (SupplierOrderDetailDTO orderDetailDTO : supplierOrder.getSupplierOrderDetailDTOS()) {
                result = supplierOrderDetailDAO.save(new SupplierOrderDetail(orderDetailDTO.getSupplierOrderID(), orderDetailDTO.getItemCode(), orderDetailDTO.getOrderQty()));
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
    public boolean updateSupplierOrder(SupplierOrderDTO supplierOrder) throws Exception {
        return false;
    }

    @Override
    public boolean deleteSupplierOrder(String supplierOrderID) throws Exception {
        return false;
    }

    @Override
    public SupplierOrderDTO searchSupplierOrder(String supplierOrderID) throws Exception {
        return null;
    }

    @Override
    public ArrayList<ViewSupplierOrderTableDTO> getAllSupplierOrder(String id) throws Exception {
        ArrayList<CustomEntity> allSupplierDetails = queryDAO.getAllSupplierDetails(id);
        ArrayList<ViewSupplierOrderTableDTO> supplierOrderDTOS = new ArrayList<>();
        for (CustomEntity c : allSupplierDetails) {
            supplierOrderDTOS.add(new ViewSupplierOrderTableDTO(c.getItemCode(), c.getItemName(), c.getBrand(), c.getModelNo(), c.getSupplierOrderID(), c.getDate(), c.getQty()));
        }
        return supplierOrderDTOS;
    }

    @Override
    public ArrayList<String> getSupplierOrderIDs() throws Exception {
        ArrayList<SupplierOrders> all = supplierOrdersDAO.getAll();
        ArrayList<String> arr = new ArrayList<>();
        for (SupplierOrders o : all) {
            arr.add(o.getSupplierOrderID());
        }
        return arr;
    }
}
