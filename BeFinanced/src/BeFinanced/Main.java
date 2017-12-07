package BeFinanced;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    static Stage window;
    public void start(Stage window) throws Exception
    {
        Model.window = window;
        View.update(Model.login, Model.littleHeight, Model.littleWidth);
    }

    public static void main(String[] args) {launch(args);}
}