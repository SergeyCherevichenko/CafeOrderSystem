package uz.cherevichenko_sergey.cafe_order_system.view.dto_view;

public class ChangeInfoClientDTO {
    private String email;
    private String password;
    private String newName;
    private String newPhoneNumber;
    private String newEmail;
    private String newPassword;

    public ChangeInfoClientDTO(String email, String password, String newName, String newPhoneNumber, String newEmail, String newPassword) {
        this.email = email;
        this.password = password;
        this.newName = newName;
        this.newPhoneNumber = newPhoneNumber;
        this.newEmail = newEmail;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
