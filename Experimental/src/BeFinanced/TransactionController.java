package BeFinanced;

import com.jfoenix.controls.*;

public class TransactionController
{
    public JFXButton doneButton;
    public JFXButton deleteCodeButton;
    public JFXButton backButton;
    public JFXComboBox<String> codeDropdown;
    public JFXDatePicker datePicker;
    public JFXRadioButton checkRadio;
    public JFXRadioButton creditRadio;
    public JFXRadioButton withdrawalRadio;
    public JFXTextArea descriptionArea;
    public JFXTextField amountField;
    public JFXTextField newCodeField;

    public void setPrevious()
    {
        Model.previousFXML = Model.transaction;
        Model.previousHeight = Model.bigHeight;
        Model.previousWidth = Model.bigWidth;
    }

    public void done() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.transactions, Model.bigHeight, Model.bigWidth);
    }

    public void deleteCode() throws Exception
    {
        setPrevious();
        View.update(Main.window, Model.delete, Model.littleHeight, Model.littleWidth);
    }

    public void back() throws Exception
    {
        View.update(Main.window, Model.transactions, Model.bigHeight, Model.bigWidth);
    }

}
