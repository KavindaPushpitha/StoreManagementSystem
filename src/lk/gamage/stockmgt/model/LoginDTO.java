package lk.gamage.stockmgt.model;

public class LoginDTO {
    private String userID;
    private String userName;
    private String password;
    private String saltValue;

    public LoginDTO() {
    }

    public LoginDTO(String userID, String userName, String password, String saltValue) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.saltValue = saltValue;
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

    public String getSaltValue() {
        return saltValue;
    }

    public void setSaltValue(String saltValue) {
        this.saltValue = saltValue;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", saltValue='" + saltValue + '\'' +
                '}';
    }
}
