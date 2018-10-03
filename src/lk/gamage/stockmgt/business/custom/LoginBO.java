package lk.gamage.stockmgt.business.custom;

import lk.gamage.stockmgt.business.SuperBO;
import lk.gamage.stockmgt.model.LoginDTO;

public interface LoginBO extends SuperBO {
    public boolean addAccount(LoginDTO loginDTO) throws Exception;

    public boolean updateAccount(LoginDTO loginDTO) throws Exception;

    public boolean deleteAccount(String id) throws Exception;

    public boolean searchAccount(String uName, String password) throws Exception;

    public LoginDTO getAccount() throws Exception;
}
