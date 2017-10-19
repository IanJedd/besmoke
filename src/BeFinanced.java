import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BeFinanced extends Application {
    private Stage window;
    private Scene currentScene;
    private final String FXML_DIR = "./ViewFXML/";
    private final String INIT_SCENE_FXML = "logIn.fxml";
    private final String SOFTWARE_NAME = "BeFinanced";
    private final int INIT_WIDTH = 400;
    private final int INIT_HEIGHT = 300;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle(SOFTWARE_NAME);
        updateScene(INIT_SCENE_FXML, INIT_WIDTH, INIT_HEIGHT);
        window.show();
    }

    public void updateScene(String fxmlFile, int width, int height) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource(FXML_DIR + fxmlFile));
            currentScene = new Scene(root, width, height);
            window.setScene(currentScene);
        }
        catch (Exception e) {
            System.out.println("Exception during Scene Update: " + e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
