import java.awt.*;
import java.io.File;

public class Launch {
    public static void url(String filename)
    {
        String path = System.getProperty("user.dir") + "/src/" + filename;
        path = path.replace("\\", "/");
        System.out.println(path);
        try {
            File htmlFile = new File(path);
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
        catch (Exception e) {System.out.println("Bad url");}
    }
    public static void main(String[] string)
    {
        Launch.url("guide.html");
    }
}
