package BeFinanced;

import javafx.stage.Stage;

import java.util.HashMap;

public class Model {
    static Stage window;

    static HashMap<String, Object> table = new HashMap<>();
    static HashMap<String, Object> users;
    static HashMap<String, Object> user;
    static HashMap<String, Object> accounts;
    static HashMap<String, Object> account;
    static HashMap<String, Object> transactions;
    static HashMap<String, Object> transaction;

    static HashMap<String, Integer> dimensions;
    static HashMap<String, Integer> savedDimensions;

    static String title = "BeFinanced by BeSmoke";
    static String imageDirectory = "/Images/";
    static String logo = "BeFinanced.png";
    static String fxmlDirectory = "/FXML/";
    static String fxmlExtension = ".fxml";
    static String fxmlFilename;
    static String savedFXMLFilename;
    static String username;
    static String saltedHashedPassword;
    static String salt;

    static Passwardo passwardo;

    static boolean administrator = false;
    static boolean goodPassword = false;

    static int height;
    static int width;
    static int savedHeight;
    static int savedWidth;

    static {
        int littleHeight = 200;
        int littleWidth = 300;
        int bigHeight = 400;
        int bigWidth = 1000;

        dimensions = new HashMap<>();
        dimensions.put("Height", littleHeight);
        dimensions.put("Width", littleWidth);
        table.put("Login", dimensions);
        table.put("Delete", dimensions);

        dimensions = new HashMap<>();
        dimensions.put("Height", bigHeight);
        dimensions.put("Width", bigWidth);
        table.put("Accounts", dimensions);
        table.put("AccountDetails", dimensions);
        table.put("Transactions", dimensions);
        table.put("TransactionDetails", dimensions);

        users = new HashMap<>();
        table.put("Users", users);
    }

    public static Object value(String key) {
        return table.get(key);
    }

    public static void main(String[] args) {}
}
