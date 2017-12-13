package BeFinanced;

import com.jfoenix.controls.*;

public class AccountDetailsController
{
    public JFXButton doneButton;
    public JFXButton deleteUserButton;
    public JFXButton backButton;

    public JFXPasswordField newPassword;
    public JFXPasswordField confirmPassword;

    public JFXTextField emailField;
    public JFXTextField phoneField;
    public JFXTextField streetField;
    public JFXTextField zipField;
    public JFXTextField newUsernameField;
    public JFXTextField accountField;

    public JFXTextArea descriptionArea;

    public JFXComboBox<String> allUsersDropdown;
    public JFXComboBox<String> authorizedUsersDropdown;

    public void done() throws Exception
    {
        View.update("Accounts");
    }
    public void deleteUser() throws Exception
    {
        View.save("AccountDetails");
        View.update("Delete");
    }
    public void back() throws Exception
    {
        View.update("Accounts");
    }
}
