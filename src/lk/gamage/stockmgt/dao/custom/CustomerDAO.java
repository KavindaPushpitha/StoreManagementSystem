package lk.gamage.stockmgt.dao.custom;

import lk.gamage.stockmgt.dao.CrudDAO;
import lk.gamage.stockmgt.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer, String> {
    public Customer searchByNIC(String s) throws Exception;

}
