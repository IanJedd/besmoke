package besmoke.src;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.io.File;

public class BeFinanced extends Application {
    private Stage window;
    private Scene currentScene;
    private final String FXML_DIR = "ViewFXML/";
    private final String INIT_SCENE_FXML = "logIn.fxml";
    private final String SOFTWARE_NAME = "BeFinanced";
    private final int INIT_WIDTH = 400;
    private final int INIT_HEIGHT = 300;
    private static User cUser;
    private static ArrayList<Transaction> tList;
    private static MasterAccount ma = MasterAccount.getMasterAccount();

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle(SOFTWARE_NAME);
        window.getIcons().add(new Image(URIString("./besmoke/src/images/Logo.png")));
        updateScene(INIT_SCENE_FXML, INIT_WIDTH, INIT_HEIGHT);
        window.show();
    }

    @Override
    public void stop() {
        System.out.println("saving");
        cUser.logOut();
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

    public static MasterAccount getMaster() {
        return ma;
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
    private String URIString(String s) {
        return new File(s).toURI().toString();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
