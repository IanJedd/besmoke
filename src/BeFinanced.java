package besmoke.src;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.util.ArrayList;

public class BeFinanced extends Application {
    private Stage window;
    private Scene currentScene;
    private final String FXML_DIR = "./ViewFXML/";
    private final String INIT_SCENE_FXML = "logIn.fxml";
    private final String SOFTWARE_NAME = "BeFinanced";
    private final int INIT_WIDTH = 400;
    private final int INIT_HEIGHT = 300;
    private static User cUser;
    private static ArrayList<Transaction> tList;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle(SOFTWARE_NAME);
        window.getIcons().add(new Image("./besmoke/src/images/Logo.png"));
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
            System.out.println("Exception during Scene Update1: " + e);
        }
    }

    public static void setUser(User u) {
        cUser = u;
    }

    public static User getUser() {
        return cUser;
    }

    public static void setTList(ArrayList<Transaction> tL) {
        tList = tL;
    }

    public static ArrayList<Transaction> getTList() {
        return tList;
    }

    public static Transaction getTransaction(int id) {
        return tList.get(id);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
