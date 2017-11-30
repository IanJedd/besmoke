package BeFinanced;

import com.jfoenix.controls.JFXButton;

public class DeleteController
{
    JFXButton deleteButton;
    JFXButton backButton;

    public void delete() throws Exception
    {
        View.update(Main.window, Model.previousFXML, Model.previousHeight, Model.previousWidth);
    }

    public void back() throws Exception
    {
        View.update(Main.window, Model.previousFXML, Model.previousHeight, Model.previousWidth);
    }

}
