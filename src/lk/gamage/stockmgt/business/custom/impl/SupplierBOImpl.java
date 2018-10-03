package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.custom.SupplierBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.SupplierDAO;
import lk.gamage.stockmgt.dao.custom.impl.SupplierDAOImpl;
import lk.gamage.stockmgt.entity.Supplier;
import lk.gamage.stockmgt.model.SupplierDTO;

import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    private SupplierDAO supplierDAO;

    public SupplierBOImpl() {
        supplierDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    }

    @Override
    public boolean addSupplier(SupplierDTO supplier) throws Exception {
        return supplierDAO.save(new Supplier(supplier.getSupplierID(), supplier.getSupplierName(), supplier.getAddress(), supplier.getContact(), supplier.getCompanyName(), supplier.getCompanyAddress(), supplier.getCompanyContact()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws Exception {
        return supplierDAO.update(new Supplier(supplier.getSupplierID(), supplier.getSupplierName(), supplier.getAddress(), supplier.getContact(), supplier.getCompanyName(), supplier.getCompanyAddress(), supplier.getCompanyContact()));
    }

    @Override
    public boolean deleteSupplier(String supplierID) throws Exception {
        return supplierDAO.delete(supplierID);
    }

    @Override
    public SupplierDTO searchSupplier(String supplierID) throws Exception {
        Supplier search = supplierDAO.search(supplierID);
        return new SupplierDTO(search.getSupplierID(), search.getSupplierName(), search.getAddress(), search.getContact(), search.getCompanyName(), search.getCompanyAddress(), search.getCompanyContact());
    }

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws Exception {
        ArrayList<Supplier> all = supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier supplier : all) {
            supplierDTOS.add(new SupplierDTO(supplier.getSupplierID(), supplier.getSupplierName(), supplier.getAddress(), supplier.getContact(), supplier.getCompanyName(), supplier.getCompanyAddress(), supplier.getCompanyContact()));
        }
        return supplierDTOS;
    }

    @Override
    public ArrayList<String> getSupplierIDs() throws Exception {
        return null;
    }


}
