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
    private TextArea createAccountDescription;
    @FXML
    private TextField createAccountEmail;
    @FXML
    private Button finishCreation;
    @FXML
    private Label errorMessage;
/***************************************************************************
 * public methods
***************************************************************************/
    public void newFinAcct(ActionEvent e) {
        if (checkFields()) {
            String name = createAccountName.getText();
            double balance = Double.parseDouble(createAccountBalance.getText());
            String phone = createAccountPhone.getText();
            String desc = createAccountDescription.getText();
            String email = createAccountEmail.getText();
            SubAccount a = new SubAccount(name, balance, desc, phone, email);
            BeFinanced.getMaster().processNewAccount(a);
            Account.addToAcctList(a);
            currentUser.addAccount(a);
            window = getEventWindow();
            updateScene(VIEW_ACCT, 600, 600);
        }
    }

    private boolean checkFields() {
        String aName = createAccountName.getText();
        String aBalance = createAccountBalance.getText();
        String aPhone = createAccountPhone.getText();
        String aDesc = createAccountDescription.getText();
        String aEmail = createAccountEmail.getText();

        if (Account.getAccount(aName) != null) {
            errorMessage.setText("An Account with this name already exists.\nYou must use a new name for account creation.");
            return false;
        }

        if(aName.equals("") || aBalance.equals("") || aPhone.equals("") || aDesc.equals("") || aEmail.equals("")) {
            errorMessage.setText("You must populate all fields.");
            return false;
        }
        try {
            Double.parseDouble(aBalance);
        }
        catch (Exception ex) {
            System.out.println(ex);
            errorMessage.setText("Balance must be numeric.");
            return false;
        }
        // TODO: implement more checks (is anyone good with Regular Expressions?)
        return true;
    }

}
