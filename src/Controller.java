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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

/*****************************************************************************
 * public methods quickReference:
 * finishCreateAction TODO: implement
 * finishTransactionAction TODO: implement
 * finishSwitchAction TODO: implement
 * switchAccountsView
 * loginBtnAction
 * logOutAction
 * createAcctView
 * delAcctView
 * newFinAcct
 *
*****************************************************************************/ 

public class Controller {
/*****************************************************************************
 * instance variables
*****************************************************************************/ 
    private User currentUser = BeFinanced.getUser();
    private Stage window;
    private Scene currentScene;
    private final String FXML_DIR = "./ViewFXML/";
    private final String LOG_IN = "logIn.fxml";
    private final String CREATE_ACCT = "createAccount.fxml";
   // private final String ACCT_VIEW = "accountView.fxml"; supplanted by viewAccount????
    private final String DEL_ACCT = "deleteAccount.fxml";
    private final String VIEW_ACCT = "viewAccount.fxml";
    private final String CREATE_TRANSACTION = "createTransaction.fxml";
    private final String SWITCH_ACCOUNTS =  "switchAccounts.fxml";
/*****************************************************************************
 * instance variables whose objects are instantiated by the FXMLLoader
*****************************************************************************/ 
    // Switch accounts
    @FXML
    private ListView<String> accountList;
    @FXML
    private Button finishSwitch;
    // Transaction tab
    @FXML
    private TextField transactionAmount;
    @FXML
    private RadioButton makeDeposit;
    @FXML
    private RadioButton makeWithdrawal;
    @FXML
    private TextField transactionDescription;
    @FXML
    private Button finishTransaction;
    // Create account
    @FXML
    private TextField createAccountName;
    @FXML
    private TextField createAccountBalance;
    @FXML
    private TextField createAccountPhone;
    @FXML
    private TextField createAccountDescription;
    @FXML
    private TextField createAccountEmail;
    @FXML
    private Button finishCreation;
    // Login
    @FXML
    private Button loginBtn;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Label failedLogIn, missingFields;
    // Universal MenuBar
    @FXML
    private MenuBar menuBar;


/***************************************************************************
 * public methods
***************************************************************************/
   
    public void finishCreateAction(ActionEvent e) {

    }
    public void finishTransactionAction(ActionEvent e) {

    }

    public void switchAccountsView(ActionEvent e) {
        window = getEventWindow();
       if ( updateScene(SWITCH_ACCOUNTS,600,600)){
       }
    }

    public void loginBtnAction(ActionEvent e) {
        window = getEventWindow();
        CharSequence pw = passwordField.getCharacters();
        String username = usernameField.getText();
        currentUser = User.logIn(username, pw);
        if (currentUser != null) {
            BeFinanced.setUser(currentUser);
            updateScene(VIEW_ACCT, 600, 600);
            failedLogIn.setText(""); // potentially unnecessary
            
        }
        else {
           failedLogIn.setText("invalid username or password");
        }
    }


    public void logOutAction(ActionEvent e) {
        window = getEventWindow();
        currentUser.logOut();
        BeFinanced.setUser(null);
        updateScene(LOG_IN, 300, 400);
    }

    public void createAcctView(ActionEvent e) {
        window = getEventWindow();
        updateScene(CREATE_ACCT, 600, 600);
    }
    public void delAcctView(ActionEvent e) {
        window = getEventWindow();
        updateScene(DEL_ACCT, 600, 600);
    }

    public void newFinAcct(ActionEvent e) {
        if (createAccountName.getText().equals("") || createAccountBalance.getText().equals("")) {
            missingFields.setText("You must populate all fields.");
        }
        else {
            String name = createAccountName.getText();
            try {
                System.out.println(currentUser);
                double balance = Double.parseDouble(createAccountBalance.getText()); System.out.println("1");
                Account a = new Account(name, balance); System.out.println("2");
                Account.addToAcctList(a); System.out.println(a.getName());
                currentUser.addAccount(a);
                for(String s : currentUser.getAccounts()) { System.out.println(s); }
                window = getEventWindow();
                updateScene(VIEW_ACCT, 600, 600);
            }
            catch (Exception ex) {
                missingFields.setText("Balance must be numeric.");
                System.out.println("newFinAcct Exception: " + ex);
            }
        }
    }


/***************************************************************************
 * private methods
****************************************************************************/
    private boolean updateScene(String fxmlFile, int width, int height) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource(FXML_DIR + fxmlFile));
            currentScene = new Scene(root, width, height);
            window.setScene(currentScene);
            currentUser = BeFinanced.getUser();
        }
        catch (Exception e) {
            System.out.println("Exception during Scene Update: " + e);
        }
        finally {
            return true;
        }
    }
    
    private Stage getEventWindow() {
        return (Stage) menuBar.getScene().getWindow();
    }
}
