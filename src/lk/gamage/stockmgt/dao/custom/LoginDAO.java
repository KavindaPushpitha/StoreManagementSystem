package lk.gamage.stockmgt.dao.custom;

import lk.gamage.stockmgt.business.custom.LoginBO;
import lk.gamage.stockmgt.dao.CrudDAO;
import lk.gamage.stockmgt.entity.UserAccount;

public interface LoginDAO extends CrudDAO<UserAccount, String> {
    public boolean searchAccount(String uName, String password) throws Exception;

    public UserAccount getAccount() throws Exception;
}
