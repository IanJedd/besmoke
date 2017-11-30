package BeFinanced;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;

public class TransactionsController
{
    public JFXButton newTransactionButton;
    public JFXButton viewTransactionButton;
    public JFXButton deleteTransactionButton;
    public JFXButton backButton;
    public JFXTreeTableView table;

    public void setPrevious()
    {
        Model.previousFXML = Model.transactions;
        Model.previousHeight = Model.bigHeight;
        Model.previousWidth = Model.bigWidth;
    }

    public void newTransaction() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.transaction, Model.bigHeight, Model.bigWidth);
    }

    public void viewTransaction() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.transaction, Model.bigHeight, Model.bigWidth);
    }

    public void deleteTransaction() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.delete, Model.littleHeight, Model.littleWidth);
    }

    public void back() throws Exception
    {
        View.update(Main.window, Model.accounts, Model.bigHeight, Model.bigWidth);
    }

    public void tableSelection() throws Exception
    {

    }
}
