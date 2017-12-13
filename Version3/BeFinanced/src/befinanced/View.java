package befinanced;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;

//This class is DONE!
class View
{
    static void save(String fxmlFilename)
    {
        Model.savedFXMLFilename = fxmlFilename;
        Model.savedDimensions = (HashMap) Model.get(fxmlFilename);
        Model.savedHeight = Model.savedDimensions.get("Height");
        Model.savedWidth = Model.savedDimensions.get("Width");
    }
    static void update(String fxmlFilename) throws Exception
    {
        View view = new View();

        if (fxmlFilename.equals("Back"))
        {
            Model.fxmlFilename = Model.savedFXMLFilename;
            Model.dimensions = Model.savedDimensions;
            Model.height = Model.savedHeight;
            Model.width = Model.savedWidth;
        }
        else
        {
            Model.fxmlFilename = fxmlFilename;
            Model.dimensions = (HashMap) Model.get(fxmlFilename);
            Model.height = Model.dimensions.get("Height");
            Model.width = Model.dimensions.get("Width");
        }

        Class viewClass = view.getClass();
        String path = Model.fxmlDirectory + Model.fxmlFilename + Model.fxmlExtension;
        URL url = viewClass.getResource(path);
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, Model.width, Model.height);
        Stage window = Model.window;
        window.setScene(scene);
        window.setTitle(Model.title);
        String imageURL = Model.imageDirectory + Model.logo;
        Image image = new Image(imageURL);
        window.getIcons().add(image);
        window.show();
    }
}
