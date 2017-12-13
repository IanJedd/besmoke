package befinanced;

import com.jfoenix.controls.*;

import java.util.HashMap;

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

    //Get a text code and put it in the dropdown. The codes are in a hashtable in Model.table under the key "Codes"
    public void addCode() throws Exception
    {

    }

    //Get default fees for the university, credit cards, and benefits and save in the Model.table
    public void defaultFees() throws Exception
    {

    }

    //Pull all fields and update them if input was given, then save to the Model.transaction table
    public void done() throws Exception
    {
        View.update("Transactions");
    }

    //Remove a code from the dropdown. The codes are a hash table in Model.table under the key Codes.
    public void deleteCode() throws Exception
    {
        View.save("TransactionDetails");
        View.update("Delete");
    }

    // Done
    public void back() throws Exception
    {
        View.update("Transactions");
    }

}
