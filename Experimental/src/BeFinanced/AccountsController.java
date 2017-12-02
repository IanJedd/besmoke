package BeFinanced;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.scene.control.TreeTableColumn;

public class AccountsController
{
    public JFXButton backButton;
    public JFXButton newButton;
    public JFXButton detailsButton;
    public JFXButton transactionsButton;
    public JFXButton deleteAccountButton;
    public JFXTreeTableView table;
    public TreeTableColumn id;
    public TreeTableColumn username;
    public TreeTableColumn grossBalance;
    public TreeTableColumn netBalance;

    public void setPrevious()
    {
        Model.previousFXML = Model.accounts;
        Model.previousHeight = Model.bigHeight;
        Model.previousWidth = Model.bigWidth;
    }

    public void back() throws Exception
    {
        View.update(Main.window, Model.login, Model.littleHeight, Model.littleWidth);
    }
    public void newAccount() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.account, Model.bigHeight, Model.bigWidth);
    }
    public void viewAccount() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.account, Model.bigHeight, Model.bigWidth);
    }
    public void deleteAccount() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.delete, Model.littleHeight, Model.littleWidth);
    }
    public void viewTransactions() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.transactions, Model.bigHeight, Model.bigWidth);
    }
}
