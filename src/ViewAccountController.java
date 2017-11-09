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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.*;

public class ViewAccountController extends Controller {
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
    // Default tab
    @FXML
    private TableView<AccountW> tableAccData;
    @FXML
    private TableColumn<AccountW, StringProperty> nameCol, phoneCol, emailCol;
    @FXML
    private TableColumn<AccountW, DoubleProperty> balanceCol;
    // Transaction tab
    @FXML
    private TextField transactionAmount;
    @FXML
    private RadioButton makeDeposit;
    @FXML
    private RadioButton makeWithdrawal;
    @FXML
    private TextArea transactionDescription;
    @FXML
    private Button finishTransaction;

/***************************************************************************
 * public methods
***************************************************************************/
    public void initialize() {
        System.out.println("initialize");
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        balanceCol.setCellValueFactory(new PropertyValueFactory("bal"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        
        if (currentUser.hasAccount()) {
            System.out.println("updataAccData");
            updateAccData();
        }
        
    }
    

    
    public void finishTransaction(ActionEvent e) {

    }
/***************************************************************************
 * private methods
***************************************************************************/
    public void updateAccData() {
        String[] accNames = currentUser.getAccounts();
        ObservableList<AccountW> aList = FXCollections.observableArrayList();
        for(String name : accNames) {
            if (name != null) {
                System.out.println("if");
                aList.add(new AccountW(Account.getAccount(name)));}
        }
        System.out.println(tableAccData);
        tableAccData.setItems(aList);
    }
}
