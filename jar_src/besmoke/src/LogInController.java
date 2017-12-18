package besmoke.src;
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


public class LogInController extends Controller {
/*****************************************************************************
 * Inherited instance variables
     User currentUser
     Stage window
     Scene currentScene
     String FXML_DIR, LOG_IN, CREATE_ACCT, DEL_ACCT, VIEW_ACCT, CREATE_TRANSACTION, SWITCH_ACCOUNTS
 
 * Inherited @FXML variables
     MenuBar menuBar

 * Inherited Public Methods
     switchAccountsView(ActionEvent e)
     logOutAction(ActionEvent e)
     createAcctView(ActionEvent e)
     delAcctView(ActionEvent e)

 * Inherited Private Methods
     updateScene(String fxmlFile, int width, int height)
     getEventWindow()  :  returns Stage

*****************************************************************************/ 

/*****************************************************************************
 * instance variables whose objects are instantiated by the FXMLLoader
*****************************************************************************/ 
    // Login
    @FXML
    private Button loginBtn;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Label failedLogIn;
/***************************************************************************
 * public methods
***************************************************************************/
    public void loginBtnAction(ActionEvent e) {
        window = getEventWindow();
        CharSequence pw = passwordField.getCharacters();
        String username = usernameField.getText();
        currentUser = User.logIn(username, pw);
        if (currentUser != null) {
            BeFinanced.setUser(currentUser);
            BeFinanced.setTList(Transaction.getTransactionList());
            updateScene(VIEW_ACCT, 600, 600);
            failedLogIn.setText(""); // potentially unnecessary
            
        }
        else {
           failedLogIn.setText("invalid username or password");
        }
    }

/***************************************************************************
 * Empty Overrides
***************************************************************************/
    public void logOutAction(ActionEvent e) {}
    public void createAcctView(ActionEvent e) {}
    public void delAcctView(ActionEvent e) {}
    public void switchAccountsView(ActionEvent e) {}
}
