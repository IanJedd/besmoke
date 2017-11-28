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
    private TableColumn<TransactionW, StringProperty> tTableTransCol, tTableDateCol, tTableTypeCol, tTableCodeCol, tTableDescriptionCol, tTableIDCol;
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
       // feesCol.setCellValueFactory(new PropertyValueFactory("fees"));


        wTableAmtCol.setCellValueFactory(new PropertyValueFactory("amount"));
        tTableAmtCol.setCellValueFactory(new PropertyValueFactory("amount"));
        dTableAmtCol.setCellValueFactory(new PropertyValueFactory("amount"));

        // Transaction Type Col
        
        /*
        wTableTransCol.setCellValueFactory(new PropertyValueFactory("sType"));
        tTableTransCol.setCellValueFactory(new PropertyValueFactory("sType"));
        dTableTransCol.setCellValueFactory(new PropertyValueFactory("sType"));
        

        // Transaction Date Columns
        wTableDateCol.setCellValueFactory(new PropertyValueFactory("amount"));
        tTableDateCol.setCellValueFactory(new PropertyValueFactory("amount"));
        dTableDateCol.setCellValueFactory(new PropertyValueFactory("amount"));
        
        // Transaction ID Columns
        wTableIDCol.setCellValueFactory(new PropertyValueFactory("amount"));
        tTableIDCol.setCellValueFactory(new PropertyValueFactory("amount"));
        dTableIDCol.setCellValueFactory(new PropertyValueFactory("amount"));
        
        */
        if (currentUser.hasAccount()) {
            updateAccData();
            System.out.println("here");
            updateTData();
        }
        
    }
    
    public void finishTransaction(ActionEvent e) {
        RadioButton r = (RadioButton) transactionType.getSelectedToggle();
        String accName = currentUser.getAccounts()[0];
        String code = "code place holder";
        String desc = "desc place holder";
        double amt = Double.parseDouble(makeTransactionAmount.getText());
        SubAccount a = (SubAccount) Account.getAccount(currentUser.getAccounts()[0]);
        if (r == makeTransactionCheck || r == makeTransactionCredit ) {
            a.processTransaction(new Transaction(TransType.CHECK_DEPOSIT, amt, accName, code, desc));
        }
        else {
            a.processTransaction(new Transaction(TransType.WITHDRAWAL, amt, accName, code, desc));
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
        SubAccount a = (SubAccount) Account.getAccount(currentUser.getAccounts()[0]);
        ObservableList<TransactionW> tList = FXCollections.observableArrayList();
        ObservableList<TransactionW> dList = FXCollections.observableArrayList();
        ObservableList<TransactionW> wList = FXCollections.observableArrayList();
        for(Integer id : a.getTransactions()) {
            Transaction t = BeFinanced.getTransaction(id);
            TransactionW tW = new TransactionW(t);
            tList.add(tW);
            if (t.isDeposit()) {
                dList.add(tW);
            }
            else {
                wList.add(tW);
            }
        }
        transactionTable.setItems(tList);
        withdrawlsTable.setItems(wList);
        depositsTable.setItems(dList);
    }

}
