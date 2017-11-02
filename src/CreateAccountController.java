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

public class CreateAccountController extends Controller {
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
    @FXML
    private Label missingFields;
/***************************************************************************
 * public methods
***************************************************************************/
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
}
