package BeFinanced;

import com.jfoenix.controls.*;

public class AccountManagementController
{
    public JFXButton doneButton;
    public JFXButton deleteUserButton;
    public JFXButton backButton;
    public JFXButton addUserButton;
    public JFXButton authorizeUserButton;
    public JFXButton deauthorizeUserButton;
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

    public void setPrevious()
    {
        Model.previousFXML = Model.account;
        Model.previousHeight = Model.bigHeight;
        Model.previousWidth = Model.bigWidth;
    }

    public void done() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.accounts, Model.bigHeight, Model.bigWidth);
    }
    public void deleteUser() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.delete, Model.littleHeight, Model.littleWidth);
    }
    public void back() throws Exception
    {
        View.update(Main.window, Model.accounts, Model.bigHeight, Model.bigWidth);
    }
}
