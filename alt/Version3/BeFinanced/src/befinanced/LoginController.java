package befinanced;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.HashMap;

//Finished!
public class LoginController {
    public JFXTextField usernameField;

    public JFXPasswordField passwordField;

    public JFXButton loginButton;

    public Label tryAgainLabel;

    public void initialize() {
        Model.user = null;
        Model.accounts = null;
        Model.transactions = null;
        Model.username = null;
        Model.salt = null;
        Model.saltedHashedPassword = null;

        Model.goodPassword = false;
        Model.administrator = false;
    }

    public void login() throws Exception {
        checkCredentials();
        if (Model.goodPassword) {
            Model.user = (HashMap) Model.users.get(Model.username);
            Model.accounts = (HashMap) Model.user.get("Accounts");
            View.update("Accounts");
        } else {
            tryAgainLabel.opacityProperty().setValue(1);
        }
    }

    public void checkCredentials() {
        Model.username = usernameField.getText();
        String password = passwordField.getText();
        Model.passwardo = new Passwardo(password);
        Model.saltedHashedPassword = Model.passwardo.saltedHashedPassword;
        Model.salt = Model.passwardo.salt;

        if (Model.users.size() == 0) {
            Model.user = new HashMap<>();
            Model.administrator = true;
            Model.user.put("Administrator", Model.administrator);
            Model.user.put("Username", Model.username);
            Model.user.put("Salted Hashed Password", Model.saltedHashedPassword);
            Model.user.put("Salt", Model.salt);
            Model.accounts = new HashMap<>();
            Model.user.put("Accounts", Model.accounts);
            Model.users.put(Model.username, Model.user);
            Model.goodPassword = true;
            System.out.println("The Administrator account \"" + Model.username + "\" was created");
        } else if (Model.users.containsKey(Model.username)) {
            Model.user = (HashMap) Model.users.get(Model.username);
            Model.salt = (String) Model.user.get("Salt");
            Model.saltedHashedPassword = (String) Model.user.get("Salted Hashed Password");
            Model.user = null;
            Model.passwardo.salt = Model.salt;
            Model.passwardo.saltedHashedPassword = Model.saltedHashedPassword;
            Model.goodPassword = Model.passwardo.checkPassword(password);
        } else {
            Model.goodPassword = false;
        }
        password = Model.passwardo.initPassword;
    }
}
