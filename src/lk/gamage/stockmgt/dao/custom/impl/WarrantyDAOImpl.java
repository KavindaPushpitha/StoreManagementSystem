package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.WarrantyDAO;
import lk.gamage.stockmgt.entity.Warranty;
import org.omg.IOP.ENCODING_CDR_ENCAPS;

import java.sql.ResultSet;
import java.util.ArrayList;

public class WarrantyDAOImpl implements WarrantyDAO {
    @Override
    public boolean save(Warranty entity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Warranty VALUES(?,?,?,?) ", entity.getWarrantyID(), entity.getWarrantyPeriod(), entity.getWarrantyFrom(), entity.getWarrantyTo()) > 0;
    }

    @Override
    public boolean update(Warranty entity) throws Exception {
        return CrudUtil.executeUpdate("Update Warranty Set warrantyPeriod=?,warrantyFrom=?,warrantyTo=? where warrantyID=? ", entity.getWarrantyPeriod(), entity.getWarrantyFrom(), entity.getWarrantyTo(), entity.getWarrantyID()) > 0;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public Warranty search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Warranty> getAll() throws Exception {
        return null;
    }

    @Override
    public String getWarrantyID() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select warrantyID from Warranty order by 1 desc limit 1");
        if (rst.next()) {
            return rst.getString(1);
        } else {
            return null;
        }
    }
}
