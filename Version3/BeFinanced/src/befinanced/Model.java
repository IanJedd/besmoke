package befinanced;

import javafx.stage.Stage;

import java.util.HashMap;

public class Model {
    static Stage window;

    //Three Tables for the Elven-kings under the sky,
    //Seven for the Dwarf-lords in their halls of stone,
    //Nine for Mortal Men doomed to die,
    //One for the Dark Lord on his dark throne
    //In the Land of Mordor where the Shadows lie.
    //One Table to rule them all, One Table to find them,
    //One Table to bring them all, and in the darkness bind them,
    //In the Land of Mordor where the Shadows lie.
    static HashMap<String, Object> table;
    //Users and user are loaded 
    static HashMap<String, Object> users;
    static HashMap<String, Object> user;
    static HashMap<String, Object> accounts;
    static HashMap<String, Object> account;
    static HashMap<String, Object> transactions;
    static HashMap<String, Object> transaction;

    static HashMap<String, Integer> dimensions;
    static HashMap<String, Integer> savedDimensions;

    static String title = "BeFinanced by BeSmoke";
    static String tableDirectory = "./befinanced/Data/";
    static String tableFilename = "BeTheTable.table";
    static String tablePath = tableDirectory + tableFilename;
    static String imageDirectory = "./befinanced/Images/";
    static String logo = "BeFinanced.png";
    static String fxmlDirectory = "./FXML/";
    static String fxmlExtension = ".fxml";
    static String fxmlFilename;
    // savedFXMLFilename lets the back function know what file to load
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
        //We need to make this method. Maybe serialize the object like Erik did.
        loadTable();

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
    public static void loadTable()
    {
        //A method for loading a table with the filename Model.tableFilename
        boolean success = false;
        if (!success) { table = new HashMap<>();}
    }

    public static void saveTable()
    {
        //A method for saving the table. Is serializing a Java object a thing? How'd you do that Erik?
    }

    public static void main(String[] args) {}
}
