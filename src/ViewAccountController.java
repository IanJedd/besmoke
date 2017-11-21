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
    // universal?
    @FXML
    private Label accountName;
    // Default tab
    @FXML
    private TableView<AccountW> tableAccData;
    @FXML
    private TableColumn<AccountW, StringProperty> nameCol, phoneCol, emailCol;
    @FXML
    private TableColumn<AccountW, DoubleProperty> balanceCol, feesCol;
    // Transaction tab
    @FXML
    private ToggleGroup transactionType;
    @FXML
    private TextField makeTransactionAmount, makeTransactionCode;
    @FXML
    private RadioButton makeTransactionCheck, makeTransactionWithdrawal, makeTransactionCredit;
    @FXML
    private TextArea makeTransactionDescription;
    @FXML
    private Button makeTransactionFinish;
    // Transactions Tab
    @FXML
    private TableView<TransactionW> transactionTable;
    @FXML
    private TableColumn<TransactionW, StringProperty> tTableTransCol, tTableDateCol, tTableTypeCol, tTableCodeCol, tTableDescriptionCol;
    @FXML
    private TableColumn<TransactionW, DoubleProperty> tTableAmtCol, tTableFeesCol;
    // Deposits Tab
    @FXML
    private TableView<TransactionW> depositsTable;
    @FXML
    private TableColumn<TransactionW, StringProperty> dTableTransCol, dTableDateCol, dTableIDCol, dTableDescriptionCol;
    @FXML
    private TableColumn<TransactionW, DoubleProperty> dTableAmtCol, dTableFeesCol;
    // Withdrawal tab
    @FXML
    private TableView<TransactionW> withdrawlsTable;
    @FXML
    private TableColumn<TransactionW, StringProperty> wTableTransCol, wTableDateCol, wTableIDCol, wTableCodeCol, wTableDescriptionCol;
    @FXML
    private TableColumn<TransactionW, DoubleProperty> wTableAmtCol;
    // Edit Transaction Tab
    @FXML
    private TextField editTransactionAmount, editTransactionCode, editTransactionID;
    @FXML
    private Button finishTransaction1, editTransactionPopulate;
    @FXML
    private RadioButton editTransactionCheck, editTransactionWithdrawal, editTransactionCredit;
    @FXML
    private ToggleGroup transactionType1;
    @FXML
    private TextArea editTransactionDescription;


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
        RadioButton r = (RadioButton) transactionType.getSelectedToggle();
        double amt = Double.parseDouble(makeTransactionAmount.getText());
        Account a = Account.getAccount(currentUser.getAccounts()[0]);
        if (r == makeTransactionCheck || r == makeTransactionCredit ) {
            a.processTransaction(new Transaction(TransType.DEPOSIT, amt));
        }
        else {
            a.processTransaction(new Transaction(TransType.WITHDRAWL, amt));
        }
        updateAccData();
        updateTData();
    }
/***************************************************************************
 * private methods
***************************************************************************/
    private void updateAccData() {
        String[] accNames = currentUser.getAccounts();
        ObservableList<AccountW> aList = FXCollections.observableArrayList();
        for(String name : accNames) {
            if (name != null) {
                aList.add(new AccountW(Account.getAccount(name)));}
        }
        tableAccData.setItems(aList);
    }

    private void updateTData() {
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
