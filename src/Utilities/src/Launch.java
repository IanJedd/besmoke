import java.awt.*;
import java.io.File;

public class Launch {
    public static boolean open(File file)
    {
        try
        {
            if (PontificateOS.windows)
            {
                Runtime.getRuntime().exec(new String[]
                        {"rundll32", "url.dll,FileProtocolHandler",
                                file.getAbsolutePath()});
                return true;
            } else if (PontificateOS.linux || PontificateOS.mac)
            {
                Runtime.getRuntime().exec(new String[]{"/usr/bin/open",
                        file.getAbsolutePath()});
                return true;
            } else
            {
                // Unknown OS, try with desktop
                if (Desktop.isDesktopSupported())
                {
                    Desktop.getDesktop().open(file);
                    return true;
                }
                else
                {
                    return false;
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace(System.err);
            return false;
        }
    }
    public static void main(String[] string)
    {
        String path = System.getProperty("user.dir");
        path += "\\" + "test.txt";
        System.out.println(path);
        File file = new File(path);
        System.out.println(Launch.open(file));
    }
}
