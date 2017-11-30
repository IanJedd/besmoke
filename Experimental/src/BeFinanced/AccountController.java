package BeFinanced;

import com.jfoenix.controls.JFXButton;

public class AccountController
{
    public JFXButton done;
    public JFXButton delete;
    public JFXButton back;

    public void setPrevious()
    {
        Model.previousFXML = Model.account;
        Model.previousHeight = Model.bigHeight;
        Model.previousWidth = Model.bigWidth;
    }

    public void done() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.accounts, Model.bigHeight, Model.bigWidth);
    }
    public void delete() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.delete, Model.littleHeight, Model.littleWidth);
    }
    public void back() throws Exception
    {
        View.update(Main.window, Model.accounts, Model.bigHeight, Model.bigWidth);
    }
}
