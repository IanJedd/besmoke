package befinanced;

import com.jfoenix.controls.JFXButton;

import java.util.HashMap;

public class AccountsController {

    public JFXButton transactionsButton;
    public JFXButton deleteAccountButton;
    public JFXButton newButton;
    public JFXButton detailsButton;
    public JFXButton backButton;

    //Done
    public void back() throws Exception {
        View.update("Login");
    }

    //Done
    public void newAccount() throws Exception {
        View.update("AccountDetails");
        Model.account = new HashMap<>();
        Model.transactions = new HashMap<>();
    }
    //Done
    public void viewAccount() throws Exception {
        View.update("AccountDetails");
    }
    //If Model.goodpassword is true, then delete the account. Needs work here.
    public void deleteAccount() throws Exception {
        View.save("Accounts");
        View.update("Delete");
    }

    //Done
    public void viewTransactions() throws Exception {
        View.update("Transactions");
    }

}
