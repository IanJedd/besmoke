package befinanced;

import javafx.application.Application;
import javafx.stage.Stage;

//This class is Done!
public class Main extends Application
{
    public void start(Stage window) throws Exception
    {
        Model.window = window;
        View.update("Login");
    }

    public static void main(String[] args) {launch(args);}
}