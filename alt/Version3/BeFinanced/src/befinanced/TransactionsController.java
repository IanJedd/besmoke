package befinanced;

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

    //Done
    public void newTransaction() throws Exception
    {
        Model.transaction = new HashMap<>();
        View.update("TransactionDetails");
    }

    //Done
    public void viewTransaction() throws Exception
    {
        View.update("TransactionDetails");
    }

    //If Model.goodpassword is true, then delete the data. Needs work here.
    public void deleteTransaction() throws Exception
    {
        View.save("Transactions");
        View.update("Delete");
    }

    //Done
    public void back() throws Exception
    {
        View.update("Accounts");
    }

    //Needs work to make a selection on the table
    public void tableSelection() throws Exception
    {

    }
}
