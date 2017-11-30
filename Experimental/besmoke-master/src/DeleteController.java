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
import javafx.event.EventHandler;
import java.awt.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class DeleteController extends Controller {
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
    // Delete accounts
    @FXML
    private ListView<String> accountList;
    @FXML
    private Button finishDelete;
    @FXML
    private Button cancelButton;
    @FXML
    private Label confirmationLabel;
/***************************************************************************
 * public methods
***************************************************************************/    
    public void attemptDeletion(ActionEvent e) {
        String candidate = accountList.getFocusModel().getFocusedItem();
        confirmationLabel.setText("Delete Account: " + candidate + "?");
        cancelButton.setVisible(true);
        cancelButton.setDisable(false);
        accountList.setDisable(true);
        // change Delete Button's Action
        finishDelete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                delete(event);
            }
        });
    }

    public void cancel(ActionEvent e) {
        confirmationLabel.setText("");
        cancelButton.setVisible(false);
        cancelButton.setDisable(true);
        accountList.setDisable(false);
        finishDelete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                attemptDeletion(event);
            }
        });
    }

    public void delete(ActionEvent e) {
        String account = accountList.getFocusModel().getFocusedItem();
        currentUser.deleteAccount(account);
        updateAccountList();
        cancel(new ActionEvent());
    }

    private void updateAccountList() {
        if (currentUser.hasAccount()) {
            ObservableList<String> accounts = FXCollections.observableArrayList();
            for (String s : currentUser.getAccounts()) {
                if (s != null) { accounts.add(s); }
            }
            accountList.setItems(accounts);
        }
    }

    public void initialize() {
        updateAccountList();
        cancelButton.setCancelButton(true);
        cancelButton.setVisible(false);
        cancelButton.setDisable(true);
    }
}
