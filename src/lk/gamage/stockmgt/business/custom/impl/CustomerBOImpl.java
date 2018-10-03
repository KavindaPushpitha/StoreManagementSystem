package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.custom.CustomerBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.CustomerDAO;
import lk.gamage.stockmgt.dao.custom.impl.CustomerDAOImpl;
import lk.gamage.stockmgt.entity.Customer;
import lk.gamage.stockmgt.model.CustomerDTO;

import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO;

    public CustomerBOImpl() {
        customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.save(new Customer(customer.getCustomerID(), customer.getCustomerName(), customer.getDob(), customer.getAddress(), customer.getContact(), customer.getNic()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.update((new Customer(customer.getCustomerID(), customer.getCustomerName(), customer.getDob(), customer.getAddress(), customer.getContact(), customer.getNic())));
    }

    @Override
    public boolean deleteCustomer(String customerID) throws Exception {
        return customerDAO.delete(customerID);
    }

    @Override
    public CustomerDTO searchCustomer(String customerID) throws Exception {
        Customer customer = customerDAO.search(customerID);
        return new CustomerDTO(customer.getCustomerID(), customer.getCustomerName(), customer.getDob(), customer.getAddress(), customer.getContact(), customer.getNic());
    }

    @Override
    public CustomerDTO searchByNic(String nic) throws Exception {
        Customer customer = customerDAO.searchByNIC(nic);
        return new CustomerDTO(customer.getCustomerID(), customer.getCustomerName(), customer.getDob(), customer.getAddress(), customer.getContact(), customer.getNic());
    }


    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws Exception {
        ArrayList<Customer> all = customerDAO.getAll();
        ArrayList<CustomerDTO> allDtos = new ArrayList<>();
        for (Customer customer : all) {
            allDtos.add(new CustomerDTO(customer.getCustomerID(), customer.getCustomerName(), customer.getDob(), customer.getAddress(), customer.getContact(), customer.getNic()));
        }
        return allDtos;
    }

    @Override
    public ArrayList<String> getAllCustomerID() throws Exception {
        return null;
    }
}
