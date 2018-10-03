package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.model.CustomerDTO;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public boolean addCustomer(CustomerDTO customer) throws Exception;

    public boolean updateCustomer(CustomerDTO customer) throws Exception;

    public boolean deleteCustomer(String customerID) throws Exception;

    public CustomerDTO searchCustomer(String customerID) throws Exception;

    public CustomerDTO searchByNic(String nic) throws Exception;

    public ArrayList<CustomerDTO> getAllCustomer() throws Exception;

    public ArrayList<String> getAllCustomerID() throws Exception;
}
