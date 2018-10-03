package lk.gamage.stockmgt.dao.custom.impl;

import lk.gamage.stockmgt.dao.CrudUtil;
import lk.gamage.stockmgt.dao.custom.LoginDAO;
import lk.gamage.stockmgt.entity.UserAccount;

import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean save(UserAccount entity) throws Exception {
        return CrudUtil.executeUpdate("Insert into userAccount values(?,?,?,?) ", entity.getUserID(), entity.getUserName(), entity.getPassword(), entity.getSaultValue()) > 0;
    }

    @Override
    public boolean update(UserAccount entity) throws Exception {
        return CrudUtil.executeUpdate("Update userAccount set userName=?,password=?,saltValue=? where userID=? ", entity.getUserName(), entity.getPassword(), entity.getSaultValue(), entity.getUserID()) > 0;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public UserAccount search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<UserAccount> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean searchAccount(String uName, String password) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select * from UserAccount where username=? and password=? ", uName, password);
        if (rst.next()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserAccount getAccount() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * from UserAccount ");
        if (rst.next()) {
            return new UserAccount(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
        } else {
            return null;
        }
    }
}
