package BeFinanced;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    public void start(Stage window) throws Exception
    {
        Model.window = window;
        View.update("Login");
    }

    public static void main(String[] args) {launch(args);}
}