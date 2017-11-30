package BeFinanced;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

public class DeleteController
{
    public JFXButton deleteButton;
    public JFXButton backButton;
    public JFXPasswordField passwordField;

    public void delete() throws Exception
    {
        View.update(Main.window, Model.previousFXML, Model.previousHeight, Model.previousWidth);
    }

    public void back() throws Exception
    {
        View.update(Main.window, Model.previousFXML, Model.previousHeight, Model.previousWidth);
    }
}
