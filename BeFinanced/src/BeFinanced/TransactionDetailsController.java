package BeFinanced;

import com.jfoenix.controls.*;

public class TransactionDetailsController
{
    public JFXButton doneButton;
    public JFXButton deleteCodeButton;
    public JFXButton backButton;
    public JFXButton defaultFeesButton;

    public JFXComboBox<String> codeDropdown;

    public JFXDatePicker datePicker;

    public JFXRadioButton checkRadio;
    public JFXRadioButton creditRadio;
    public JFXRadioButton withdrawalRadio;

    public JFXTextArea descriptionArea;

    public JFXTextField amountField;
    public JFXTextField newCodeField;
    public JFXTextField creditCardField;
    public JFXTextField universityField;
    public JFXTextField benefitsField;
    
    public JFXToggleButton creditCardToggle;
    public JFXToggleButton universityToggle;
    public JFXToggleButton benefitsToggle;

    public void addCode() throws Exception
    {

    }

    public void defaultFees() throws Exception
    {

    }

    public void done() throws Exception
    {
        View.update("Transactions");
    }

    public void deleteCode() throws Exception
    {
        View.save("TransactionDetails");
        View.update("Delete");
    }

    public void back() throws Exception
    {
        View.update("Transactions");
    }

}
