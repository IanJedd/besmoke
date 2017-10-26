import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.EventTarget;
import javafx.event.ActionEvent;

import java.awt.MenuItem;

public class Controller {
    private User currentUser = null;
    private Stage window;
    private Scene currentScene;
    private final String FXML_DIR = "./ViewFXML/";
    private final String LOG_IN = "logIn.fxml";
    private final String CREATE_ACCT = "createAccount.fxml";
    private final String ACCT_VIEW = "accountView.fxml";
    private final String DEL_ACCT = "deleteAccount.fxml";

    // private variables with FXML access
    @FXML
    private ListView accountList
    @FXML
    private Button finishSwitch
    @FXML
    private TextField newDepsoit
    @FXML
    private TextField newWithdrawal
    @FXML
    private TextField transactionDescription
    @FXML
    private Button finishTransaction
    @FXML
    private MenuItem switchAccounts
    @FXML
    private Button loginBtn;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Label failedLogIn, missingFields;
    @FXML
    private MenuItem logout;
    @FXML
    private MenuItem createAaccount;
    @FXML
    private MenuItem deleteAccount;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TextField newAccountName;
    @FXML
    private TextField newBalance;
    @FXML
    private Button finishCreate;

    public void finishCreateAction(ActionEvent e)
    // Finish create account button
    {

    }
    public void finishTransactionAction(ActionEvent e)
    // Finish transaction button
    {

    }
    public void switchAccountsAction(ActionEvent e)
    // Menu item
    {

    }
    public void finishAccountSwitch(ActionEvent e)
    // Finish switching button
    {

    }


    public void loginBtnAction(ActionEvent e) {
        window = getEventWindow(loginBtn);
        CharSequence pw = passwordField.getCharacters();
        String username = usernameField.getText();
        currentUser = User.logIn(username, pw);
        if (currentUser != null) {
            BeFinanced.setUser(currentUser);
            updateScene(ACCT_VIEW, 600, 600);
            failedLogIn.setText(""); // potentially unnecessary
            
        }
        else {
           failedLogIn.setText("invalid username or password");
        }
    }

    private Stage getEventWindow(Button b) {
        return (Stage) b.getScene().getWindow();
    }

    private Stage getEventWindow(MenuItem m) {
        return (Stage) menuBar.getScene().getWindow();
    }

    public void logOutAction(ActionEvent e) {
        window = getEventWindow(loginBtn);
        BeFinanced.getUser().logOut();
        BeFinanced.setUser(null);
        updateScene(LOG_IN, 300, 400);
    }

    public void updateScene(String fxmlFile, int width, int height) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource(FXML_DIR + fxmlFile));
            currentScene = new Scene(root, width, height);
            window.setScene(currentScene); System.out.println(currentUser);
        }
        catch (Exception e) {
            System.out.println("Exception during Scene Update: " + e);
        }
    }

    public void createAcctView(ActionEvent e) {
        window = getEventWindow(createAccount);
        updateScene(CREATE_ACCT, 600, 600);
    }
    public void delAcctView(ActionEvent e) {
        window = getEventWindow(deleteAccount);
        updateScene(DEL_ACCT, 600, 600);
    }

    public void newFinAcct(ActionEvent e) {
        if (newAccountName.getText().equals("") || newBalance.getText().equals("")) {
            missingFields.setText("You must populate all fields.");
        }
        else {
            String name = newAccountName.getText();
            try {
                double balance = (double) Double.parseDouble(newBalance.getText()); System.out.println("1");
                Account a = new Account(name, balance); System.out.println("2");
                Account.addToAcctList(a); System.out.println(currentUser);
                BeFinanced.getUser().addAccount(a); System.out.println("4");
                window = getEventWindow(createFinAcct); System.out.println(window);
                updateScene(ACCT_VIEW, 600, 600);
            }
            catch (Exception ex) {
                missingFields.setText("Balance must be numeric.");
                System.out.println("newFinAcct Exception: " + ex);
            }
        }
    }
}
