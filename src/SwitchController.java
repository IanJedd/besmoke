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

public class SwitchController extends Controller {
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
    // Switch accounts
    @FXML
    private ListView<String> accountList;
    @FXML
    private Button finishSwitch;
/***************************************************************************
 * public methods
***************************************************************************/
    public void initialize() {
        updateAccountList();
    }    
    public void finishSwitchAction(ActionEvent e) {
       String acc = accountList.getSelectionModel().getSelectedItem();
       

    }
/***************************************************************************
 * private methods
***************************************************************************/
    private void updateAccountList() {
        if (currentUser.hasAccount()) {
            ObservableList<String> accounts = FXCollections.observableArrayList();
            for (String s : currentUser.getAccounts()) {
                if (s != null) { accounts.add(s); }
            }
            accountList.setItems(accounts);
        }
    }
}
