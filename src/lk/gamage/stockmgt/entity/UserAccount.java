package lk.gamage.stockmgt.entity;

public class UserAccount {
    private String userID;
    private String userName;
    private String password;
    private String saultValue;

    public UserAccount() {
    }

    public UserAccount(String userID, String userName, String password, String saultValue) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.saultValue = saultValue;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSaultValue() {
        return saultValue;
    }

    public void setSaultValue(String saultValue) {
        this.saultValue = saultValue;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", saultValue='" + saultValue + '\'' +
                '}';
    }
}
