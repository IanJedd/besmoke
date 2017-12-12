package BeFinanced;

import com.jfoenix.controls.JFXButton;

import java.util.HashMap;

public class AccountsController {

    public JFXButton transactionsButton;
    public JFXButton deleteAccountButton;
    public JFXButton newButton;
    public JFXButton detailsButton;
    public JFXButton backButton;

    public void back() throws Exception {
        View.update("Login");
    }

    public void newAccount() throws Exception {
        View.update("AccountDetails");
        Model.account = new HashMap<>();
        Model.transactions = new HashMap<>();
    }

    public void viewAccount() throws Exception {
        View.update("AccountDetails");
    }

    public void deleteAccount() throws Exception {
        View.save("Accounts");
        View.update("Delete");
    }

    public void viewTransactions() throws Exception {
        View.update("Transactions");
    }

}
