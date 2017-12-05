package BeFinanced;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class LoginController
{
    public JFXTextField usernameField;
    public JFXPasswordField passwordField;
    public JFXButton loginButton;
    static String username = "";
    static String password = "";

    public void setPrevious()
    {
        Model.previousFXML = Model.login;
        Model.previousHeight = Model.bigHeight;
        Model.previousWidth = Model.bigWidth;
    }

    public void login() throws Exception
    {
        setPrevious();
        username = usernameField.getText();
        password = passwordField.getText();
        View.update(Main.window, Model.accounts, Model.bigHeight, Model.bigWidth);
    }
}
