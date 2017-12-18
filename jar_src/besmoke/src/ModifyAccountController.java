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

public class ModifyAccountController extends Controller {
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
    private TextField AccountName;
    @FXML
    private TextField AccountBalance;
    @FXML
    private TextField AccountPhone;
    @FXML
    private TextArea AccountDescription;
    @FXML
    private TextField AccountEmail;
    @FXML
    private Button finishModify;
    @FXML
    private Label errorMessage;
/***************************************************************************
 * public methods
***************************************************************************/
    public void initialize() {
        Account a = Account.getAccount(currentUser.getAccounts()[0]);
        AccountName.setText(a.getAccountName());
        AccountName.setEditable(false);
        Double b = (Double) a.getBalance();
        AccountBalance.setText(b.toString());
        AccountBalance.setEditable(false);
        AccountPhone.setText(a.getPhone());
        AccountEmail.setText(a.getEmail());
        AccountDescription.setText(a.getDescription());
    }

    public void modifyAcct(ActionEvent e) {
        if (checkFields()) {
            Account a = Account.getAccount(currentUser.getAccounts()[0]); 
            a.setPhone(AccountPhone.getText());
            a.setDescription(AccountDescription.getText());
            a.setEmail(AccountEmail.getText());
            Account.updateAcctList(a);
            window = getEventWindow();
            updateScene(VIEW_ACCT, 600, 600);
        }
    }

    private boolean checkFields() {
        String aPhone = AccountPhone.getText();
        String aDesc = AccountDescription.getText();
        String aEmail = AccountEmail.getText();

        if(aPhone.equals("") || aDesc.equals("") || aEmail.equals("")) {
            errorMessage.setText("You must populate all fields.");
            return false;
        }
        // TODO: implement more checks (is anyone good with Regular Expressions?)
        return true;
    }

}
