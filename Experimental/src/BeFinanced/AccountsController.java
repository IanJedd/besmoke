package BeFinanced;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;

public class AccountsController
{
    public JFXButton backButton;
    public JFXButton newButton;
    public JFXButton detailsButton;
    public JFXButton selectButton;
    public JFXTreeTableView table;

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
    public void account() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.account, Model.bigHeight, Model.bigWidth);
    }
    public void select() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.transactions, Model.bigHeight, Model.bigWidth);
    }
    public void delete() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.delete, Model.littleHeight, Model.littleWidth);
    }

    public void tableSelection() throws Exception
    {

    }
}
