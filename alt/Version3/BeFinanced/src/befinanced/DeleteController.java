package befinanced;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

public class DeleteController
{
    public JFXButton deleteButton;
    public JFXButton backButton;

    public JFXPasswordField passwordField;

    public void delete() throws Exception
    {
        View.update("Back");
    }

    public void back() throws Exception
    {
        View.update("Back");
    }
}
