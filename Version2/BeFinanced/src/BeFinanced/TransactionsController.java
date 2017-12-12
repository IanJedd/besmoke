package BeFinanced;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;

import java.util.HashMap;

public class TransactionsController
{
    public JFXButton newTransactionButton;
    public JFXButton viewTransactionButton;
    public JFXButton deleteTransactionButton;
    public JFXButton backButton;

    public JFXTreeTableView table;

    public void newTransaction() throws Exception
    {
        Model.transaction = new HashMap<>();
        View.update("TransactionDetails");
    }

    public void viewTransaction() throws Exception
    {
        View.update("TransactionDetails");
    }

    public void deleteTransaction() throws Exception
    {
        View.save("Transactions");
        View.update("Delete");
    }

    public void back() throws Exception
    {
        View.update("Accounts");
    }

    public void tableSelection() throws Exception
    {

    }
}
