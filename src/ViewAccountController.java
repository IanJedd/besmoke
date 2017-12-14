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
import java.time.LocalDate;

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
    private TableColumn<AccountW, StringProperty> nameCol, phoneCol, emailCol, descriptionCol;
    @FXML
    private TableColumn<AccountW, DoubleProperty> balanceCol, uniFeesCol, creditFeesCol;
    // Transaction tab
    @FXML
    private ToggleGroup transactionType;
    @FXML
    private TextField makeTransactionAmount, makeTransactionCode, makeTransactionAmount1;
    @FXML
    private RadioButton makeTransactionCheck, makeTransactionWithdrawal, makeTransactionCredit, makeFeeWithdrawal;
    @FXML
    private TextArea makeTransactionDescription;
    @FXML
    private Button makeTransactionFinish;
    @FXML
    private DatePicker makeDate;
    // AllTransactions Tab
    @FXML
    private TableView<TransactionW> transactionTable1;
    @FXML
    private TableColumn<TransactionW, StringProperty> allTTableTransCol, allTTableDateCol, allTTableTypeCol, allTTableCodeCol, allTTableDescriptionCol, allTTableAccNameCol;
    @FXML
    private TableColumn<TransactionW, IntegerProperty> allTTableIDCol;
    @FXML
    private TableColumn<TransactionW, DoubleProperty> allTTableAmtCol, allTTableFeesCol;
    // Transactions Tab
    @FXML
    private TableView<TransactionW> transactionTable;
    @FXML
    private TableColumn<TransactionW, StringProperty> tTableTransCol, tTableDateCol, tTableTypeCol, tTableCodeCol, tTableDescriptionCol;
    @FXML
    private TableColumn<TransactionW, IntegerProperty> tTableIDCol;
    @FXML
    private TableColumn<TransactionW, DoubleProperty> tTableAmtCol, tTableFeesCol;
    // Deposits Tab
    @FXML
    private TableView<TransactionW> depositsTable;
    @FXML
    private TableColumn<TransactionW, StringProperty> dTableTransCol, dTableDateCol, dTableDescriptionCol, dTableCodeCol;
    @FXML
    private TableColumn<TransactionW, IntegerProperty> dTableIDCol;
    @FXML
    private TableColumn<TransactionW, DoubleProperty> dTableAmtCol, dTableFeesCol;
    // Withdrawal tab
    @FXML
    private TableView<TransactionW> withdrawalsTable;
    @FXML
    private TableColumn<TransactionW, StringProperty> wTableTransCol, wTableDateCol, wTableCodeCol, wTableDescriptionCol;
    @FXML
    private TableColumn<TransactionW, IntegerProperty> wTableIDCol;
    @FXML
    private TableColumn<TransactionW, DoubleProperty> wTableAmtCol, wTableFBen, wTableFNet, wTableSBen, wTableSNet;
    // Edit Transaction Tab
    @FXML
    private TextField editTransactionAmount, editTransactionCode, editTransactionID;
    @FXML
    private Button editTransactionPopulate, deleteCancel, deleteTransaction, confirmDelete;
    @FXML
    private TextArea editTransactionDescription;

/***************************************************************************
 * public methods
***************************************************************************/
    // editTrans
    //
    private void lockFields() {
        editTransactionID.setDisable(true);
    }
    private void unlockFields() {
        editTransactionID.setDisable(false);
    }
    private void clearFields() {
        editTransactionAmount.setText("");
        editTransactionCode.setText("");
        editTransactionID.setText("");
        editTransactionDescription.setText("");
    }

    public void populateEditTrans() {
        lockFields();
        String idStr = editTransactionID.getText();
        if (checkID(idStr)) {
            int id = Integer.parseInt(idStr);
            editTransactionID.setEditable(false);
            Transaction t = BeFinanced.getTransaction(id);
            editTransactionAmount.setText(((Double)t.getAmount()).toString());
            System.out.println(t);
            System.out.println(t.getCode());
            editTransactionCode.setText(t.getCode());
            editTransactionDescription.setText(t.getDescription());
            // handle new buttons
            editTransactionPopulate.setVisible(false);
            editTransactionPopulate.setDisable(true);
            deleteTransaction.setVisible(true);
            deleteTransaction.setDisable(false);
        }
    }

    public void cancelDelete() {
        unlockFields();
        confirmDelete.setVisible(false);
        confirmDelete.setDisable(true);
        deleteCancel.setVisible(false);
        deleteCancel.setDisable(true);
        editTransactionPopulate.setVisible(true);
        editTransactionPopulate.setDisable(false);
    }

    public void delete() {
        confirmDelete.setVisible(true);
        confirmDelete.setDisable(false);
        deleteCancel.setVisible(true);
        deleteCancel.setDisable(false);
        deleteTransaction.setVisible(false);
        deleteTransaction.setDisable(true);
    }

    public void deleteConfirmed() {
        int id = Integer.parseInt(editTransactionID.getText());
        Transaction initT = BeFinanced.getTransaction(id);
        if ( initT.isDeleted() ) { return; }
        initT.makeDeleted();
        String delDesc = "Delete Transaction for transaction id: " + id;
        Transaction deleter = new Transaction(initT.getType(), -initT.getAmount(), initT.getAccountName(), initT.getCode(), delDesc, LocalDate.now());
        deleter.makeDeleted();
        Account a = Account.getAccount(deleter.getAccountName());
        a.processTransaction(deleter);
        MasterAccount ma = BeFinanced.getMaster();
        ma.processTransaction(deleter);
        clearFields();
        unlockFields();
        confirmDelete.setVisible(false);
        confirmDelete.setDisable(true);
        deleteCancel.setVisible(false);
        deleteCancel.setDisable(true);
        editTransactionPopulate.setVisible(true);
        editTransactionPopulate.setDisable(false);
        updateAccData();
        updateTData();
    }

    public void initialize() {
        
        editTransactionAmount.setDisable(true);
        editTransactionCode.setDisable(true);
        editTransactionDescription.setDisable(true);

        // Accounts Tab
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        balanceCol.setCellValueFactory(new PropertyValueFactory("bal"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
        uniFeesCol.setCellValueFactory(new PropertyValueFactory("universityFees"));
        creditFeesCol.setCellValueFactory(new PropertyValueFactory("creditCardFees"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory("desc"));

        // (Transactions / Withdrawals / Deposits) Amount
        wTableAmtCol.setCellValueFactory(new PropertyValueFactory("amount"));
        tTableAmtCol.setCellValueFactory(new PropertyValueFactory("amount"));
        dTableAmtCol.setCellValueFactory(new PropertyValueFactory("amount"));
        allTTableAmtCol.setCellValueFactory(new PropertyValueFactory("amount"));
        
        // (Transactions / Withdrawals / Deposits) Description
        wTableDescriptionCol.setCellValueFactory(new PropertyValueFactory("desc"));
        tTableDescriptionCol.setCellValueFactory(new PropertyValueFactory("desc"));
        dTableDescriptionCol.setCellValueFactory(new PropertyValueFactory("desc"));
        allTTableDescriptionCol.setCellValueFactory(new PropertyValueFactory("desc"));

        // (Transactions / Deposits) Fees
        tTableFeesCol.setCellValueFactory(new PropertyValueFactory("fees"));
        dTableFeesCol.setCellValueFactory(new PropertyValueFactory("fees"));
        allTTableFeesCol.setCellValueFactory(new PropertyValueFactory("fees"));

        // (Transactions / Withdrawals / Deposits) Code
        tTableCodeCol.setCellValueFactory(new PropertyValueFactory("code"));
        wTableCodeCol.setCellValueFactory(new PropertyValueFactory("code"));
        allTTableCodeCol.setCellValueFactory(new PropertyValueFactory("code"));
        dTableCodeCol.setCellValueFactory(new PropertyValueFactory("code"));
        
        // (Transactions / Withdrawals / Deposits) Transaction Type
        wTableTransCol.setCellValueFactory(new PropertyValueFactory("sType"));
        tTableTransCol.setCellValueFactory(new PropertyValueFactory("sType"));
        dTableTransCol.setCellValueFactory(new PropertyValueFactory("sType"));
        allTTableTransCol.setCellValueFactory(new PropertyValueFactory("sType"));
        
        
        // (Transactions / Withdrawals / Deposits) Date
        wTableDateCol.setCellValueFactory(new PropertyValueFactory("date"));
        tTableDateCol.setCellValueFactory(new PropertyValueFactory("date"));
        dTableDateCol.setCellValueFactory(new PropertyValueFactory("date"));
        allTTableDateCol.setCellValueFactory(new PropertyValueFactory("date"));
 
        // (Transactions / Withdrawals / Deposits) ID
        wTableIDCol.setCellValueFactory(new PropertyValueFactory("id"));
        tTableIDCol.setCellValueFactory(new PropertyValueFactory("id"));
        dTableIDCol.setCellValueFactory(new PropertyValueFactory("id"));
        allTTableIDCol.setCellValueFactory(new PropertyValueFactory("id"));

        // AllTransactions AccountName
        allTTableAccNameCol.setCellValueFactory(new PropertyValueFactory("accountName"));

        // Withdrawals Benefits Calculator
        wTableFBen.setCellValueFactory(new PropertyValueFactory("facultyBen"));
        wTableSBen.setCellValueFactory(new PropertyValueFactory("studentBen"));
        wTableFNet.setCellValueFactory(new PropertyValueFactory("facultyNet"));
        wTableSNet.setCellValueFactory(new PropertyValueFactory("studentNet"));

        // set default date for datepickers
        makeDate.setValue(LocalDate.now());
        makeDate.setEditable(false);
        
        if (currentUser.hasAccount()) {
            accountName.setText(BeFinanced.getUser().getAccounts()[0]);
            updateAccData();
            updateTData();
        }
        
    }
    
    public void finishTransaction(ActionEvent e) {
        RadioButton r = (RadioButton) transactionType.getSelectedToggle();
        Transaction t;
        MasterAccount ma = BeFinanced.getMaster();
        String rId = r.getId();
        String accName = currentUser.getAccounts()[0];
        String code = getMakeTransCode();
        String desc = makeTransactionDescription.getText();
        LocalDate date = makeDate.getValue();
        double amt = Double.parseDouble(makeTransactionAmount.getText());
        SubAccount a = (SubAccount) Account.getAccount(currentUser.getAccounts()[0]);
        if (rId.equals("makeTransactionCheck")) {
            t = new Transaction(TransType.CHECK_DEPOSIT, amt, accName, code, desc, date);
            a.processTransaction(t);
            ma.processTransaction(t);
        }
        else if (rId.equals("makeTransactionCredit")) {
            t = new Transaction(TransType.CC_DEPOSIT, amt, accName, code, desc, date);
            a.processTransaction(t);
            ma.processTransaction(t);
        }

        else if (rId.equals("makeTransactionWithdrawal")) {
            t = new Transaction(TransType.WITHDRAWAL, amt, accName, code, desc, date);
            a.processTransaction(t);
            ma.processTransaction(t);
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
        aList.add(new AccountW(BeFinanced.getMaster()));
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
        ObservableList<TransactionW> allTList = FXCollections.observableArrayList();
        for(Transaction t : BeFinanced.getTList()) {
            allTList.add(new TransactionW(t));
        }
        for(Integer id : a.getTransactions()) {
            System.out.println(id);
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
        transactionTable1.setItems(allTList);
        transactionTable.setItems(tList);
        withdrawalsTable.setItems(wList);
        depositsTable.setItems(dList);
    }

    private String getMakeTransCode() {
        String retVal;
        // TODO: deal with combo box first if we can get that up and running
        retVal = makeTransactionAmount1.getText();
        return retVal;
    }

    private boolean checkID(String s) {
        try {
            int i = Integer.parseInt(s);
            if (BeFinanced.getTransaction(i) == null) {
                return false;
            }
        }
        catch (Exception e) {
            System.out.println("ID Lookup Exception: " + e);
            return false;
        }
        return true;
    }




}
