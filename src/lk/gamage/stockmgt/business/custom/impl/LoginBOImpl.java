package lk.gamage.stockmgt.business.custom.impl;

import lk.gamage.stockmgt.business.custom.LoginBO;
import lk.gamage.stockmgt.dao.DAOFactory;
import lk.gamage.stockmgt.dao.custom.LoginDAO;
import lk.gamage.stockmgt.entity.UserAccount;
import lk.gamage.stockmgt.model.LoginDTO;

public class LoginBOImpl implements LoginBO {
    private LoginDAO loginDAO;

    public LoginBOImpl() {
        loginDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LOGIN);
    }

    @Override
    public boolean addAccount(LoginDTO loginDTO) throws Exception {
        return loginDAO.save(new UserAccount(loginDTO.getUserID(), loginDTO.getUserName(), loginDTO.getPassword(), loginDTO.getSaltValue()));
    }

    @Override
    public boolean updateAccount(LoginDTO loginDTO) throws Exception {
        return loginDAO.update(new UserAccount(loginDTO.getUserID(), loginDTO.getUserName(), loginDTO.getPassword(), loginDTO.getSaltValue()));
    }

    @Override
    public boolean deleteAccount(String id) throws Exception {
        return false;
    }

    @Override
    public boolean searchAccount(String uName, String password) throws Exception {
        boolean b = loginDAO.searchAccount(uName, password);
        if (b) {
            return true;
        } else {
            return false;
        }
    }

    public LoginDTO getAccount() throws Exception {
        UserAccount account = loginDAO.getAccount();
        if (account != null) {
            return new LoginDTO(account.getUserID(), account.getUserName(), account.getPassword(), account.getSaultValue());
        } else {
            return null;
        }
    }
}
