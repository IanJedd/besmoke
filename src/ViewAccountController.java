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
    // Transactions Tab
    @FXML
    private TableView<TransactionW> transactionTable;
    @FXML
    private TableColumn<TransactionW, StringProperty> tTableTransCol;
    @FXML
    private TableColumn<TransactionW, DoubleProperty> tTableAmtCol;
    // Deposits Tab
    @FXML
    private TableView<TransactionW> depositsTable;
    @FXML
    private TableColumn<TransactionW, StringProperty> dTableTransCol;
    @FXML
    private TableColumn<TransactionW, DoubleProperty> dTableAmtCol;
    // Withdrawl tab
    @FXML
    private TableView<TransactionW> withdrawlsTable;
    @FXML
    private TableColumn<TransactionW, StringProperty> wTableTransCol;
    @FXML
    private TableColumn<TransactionW, DoubleProperty> wTableAmtCol;


/***************************************************************************
 * public methods
***************************************************************************/
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        balanceCol.setCellValueFactory(new PropertyValueFactory("bal"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));

        wTableAmtCol.setCellValueFactory(new PropertyValueFactory("amount"));
        tTableAmtCol.setCellValueFactory(new PropertyValueFactory("amount"));
        dTableAmtCol.setCellValueFactory(new PropertyValueFactory("amount"));

        wTableTransCol.setCellValueFactory(new PropertyValueFactory("sType"));
        tTableTransCol.setCellValueFactory(new PropertyValueFactory("sType"));
        dTableTransCol.setCellValueFactory(new PropertyValueFactory("sType"));
        
        if (currentUser.hasAccount()) {
            updateAccData();
            updateTData();
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
                aList.add(new AccountW(Account.getAccount(name)));}
        }
        tableAccData.setItems(aList);
    }

    public void updateTData() {
        Account a = Account.getAccount(currentUser.getAccounts()[0]);
        ObservableList<TransactionW> tList = FXCollections.observableArrayList();
        ObservableList<TransactionW> dList = FXCollections.observableArrayList();
        ObservableList<TransactionW> wList = FXCollections.observableArrayList();
        for(Transaction t : a.getTransactions()) {
            tList.add(new TransactionW(t));
            if (t.isDeposit()) {
                dList.add(new TransactionW(t));
            }
            else {
                wList.add(new TransactionW(t));
            }
        }
        transactionTable.setItems(tList);
        withdrawlsTable.setItems(wList);
        depositsTable.setItems(dList);
    }
}
