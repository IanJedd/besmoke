package BeFinanced;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

class View
{
    static void update(String filename, int height, int width) throws Exception
    {
        View view = new View();
        Class viewClass = view.getClass();
        String path = Model.fxmlDirectory + filename + Model.fxmlExtension;
        URL url = viewClass.getResource(path);
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, width, height);
        Stage window = Model.window;
        window.setScene(scene);
        window.setTitle(Model.title);
        String imageURL = Model.imageDirectory + Model.logo;
        Image image = new Image(imageURL);
        window.getIcons().add(image);
        window.show();
    }
}
