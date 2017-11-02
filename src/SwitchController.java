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

public class SwitchController {
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
    // Universal MenuBar
    @FXML
    private MenuBar menuBar;


    public void finishSwitchAction(ActionEvent e) {
        System.out.println("finishSwithAction");
        System.out.println(finishSwitch);
        System.out.println(accountList);
        updateAccountList();

    }

    public void switchAccountsView(ActionEvent e) {
        window = getEventWindow();
       if ( updateScene(SWITCH_ACCOUNTS,600,600)){
           updateAccountList();
       }
    }

    private void updateAccountList() {
        if (currentUser.hasAccount()) {
            System.out.println("hasAccount");
            ObservableList<String> accounts = FXCollections.observableArrayList();
            for (String s : currentUser.getAccounts()) {
                System.out.println(s);
                accounts.add(s);
            }
            accountList.setItems(accounts);
        }
    }

    public void initialize() {
        updateAccountList();

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
