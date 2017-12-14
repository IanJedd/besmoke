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
    protected User currentUser = BeFinanced.getUser();
    protected Stage window;
    protected Scene currentScene;
    protected final String FXML_DIR = "ViewFXML/";
    protected final String LOG_IN = "logIn.fxml";
    protected final String CREATE_ACCT = "createAccount.fxml";
    protected final String DEL_ACCT = "deleteAccount.fxml";
    protected final String VIEW_ACCT = "viewAccount.fxml";
    protected final String CREATE_TRANSACTION = "createTransaction.fxml";
    protected final String SWITCH_ACCOUNTS =  "switchAccounts.fxml";
    protected final String MODIFY_ACCOUNT = "modifyAccount.fxml";
/*****************************************************************************
 * instance variables whose objects are instantiated by the FXMLLoader
*****************************************************************************/
    // Universal @FXML tagged instance variables
    @FXML
    protected MenuBar menuBar;


/***************************************************************************
 * public methods
***************************************************************************/

    public void switchAccountsView(ActionEvent e) {
        window = getEventWindow();
       updateScene(SWITCH_ACCOUNTS,600,600);
    }

    public void logOutAction(ActionEvent e) {
        window = getEventWindow();
        currentUser.logOut();
        BeFinanced.setUser(null);
        updateScene(LOG_IN, 400, 300);
    }

    public void homeAction(ActionEvent e) {
       window = getEventWindow();
       updateScene(VIEW_ACCT, 600, 600);
   }

    public void createAcctView(ActionEvent e) {
        window = getEventWindow();
        updateScene(CREATE_ACCT, 600, 600);
    }
    public void delAcctView(ActionEvent e) {
        window = getEventWindow();
        updateScene(DEL_ACCT, 600, 600);
    }

    public void modifyAccountsView(ActionEvent e) {
        window = getEventWindow();
        updateScene(MODIFY_ACCOUNT, 600, 600);
    }
    
    public void launchGuide(ActionEvent e){
        Launch.url("guide.html");
    }

/***************************************************************************
 * private methods
****************************************************************************/
    protected void updateScene(String fxmlFile, int width, int height) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource(FXML_DIR + fxmlFile));
            currentScene = new Scene(root, width, height);
            window.setScene(currentScene);
            currentUser = BeFinanced.getUser();
        }
        catch (Exception e) {
            System.out.println("Exception during Scene Update: " + e);
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    protected Stage getEventWindow() {
        return (Stage) menuBar.getScene().getWindow();
    }
}
