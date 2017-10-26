import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.EventTarget;
import javafx.event.ActionEvent;
public class Controller {
    private User currentUser = null;
    private Stage window;
    private Scene currentScene;
    private final String FXML_DIR = "./ViewFXML/";
    private final String LOG_IN = "logIn.fxml";
    private final String CREATE_ACCT = "createAccount.fxml";
    private final String ACCT_VIEW = "accountView.fxml";
    private final String DEL_ACCT = "deleteAccount.fxml";

    // private variables with FXML access

    @FXML
    private Button loginBtn;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Label failedLogIn, missingFields;
    @FXML
    private MenuItem logOut;
    @FXML
    private MenuItem createAcct;
    @FXML
    private MenuItem delAcct;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TextField finAcctNameField;
    @FXML
    private TextField finAcctInitBalanceField;
    @FXML
    private Button createFinAcct;


    public void loginBtnAction(ActionEvent e) {
        window = getEventWindow(loginBtn);
        CharSequence pw = passwordField.getCharacters();
        String username = usernameField.getText();
        currentUser = User.logIn(username, pw);
        if (currentUser != null) {
            BeFinanced.setUser(currentUser);
            updateScene(ACCT_VIEW, 600, 600);
            failedLogIn.setText(""); // potentially unnecessary
            
        }
        else {
           failedLogIn.setText("invalid username or password");
        }
    }

    public void setUser(User u) {
        currentUser = u;
    }

    private Stage getEventWindow(Button b) {
        return (Stage) b.getScene().getWindow();
    }

    private Stage getEventWindow(MenuItem m) {
        return (Stage) menuBar.getScene().getWindow();
    }

    public void logOutAction(ActionEvent e) {
        window = getEventWindow(loginBtn);
        BeFinanced.getUser().logOut();
        BeFinanced.setUser(null);
        updateScene(LOG_IN, 300, 400);
    }

    public void updateScene(String fxmlFile, int width, int height) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource(FXML_DIR + fxmlFile));
            currentScene = new Scene(root, width, height);
            window.setScene(currentScene); 
            BeFinanced.setUser(
            System.out.println(currentUser);
        }
        catch (Exception e) {
            System.out.println("Exception during Scene Update: " + e);
        }
    }

    public void createAcctView(ActionEvent e) {
        window = getEventWindow(createAcct);
        updateScene(CREATE_ACCT, 600, 600);
    }
    public void delAcctView(ActionEvent e) {
        window = getEventWindow(delAcct);
        updateScene(DEL_ACCT, 600, 600);
    }

    public void newFinAcct(ActionEvent e) {
        if (finAcctNameField.getText().equals("") || finAcctInitBalanceField.getText().equals("")) {
            missingFields.setText("You must populate all fields.");
        }
        else {
            String name = finAcctNameField.getText();
            try {
                double balance = (double) Double.parseDouble(finAcctInitBalanceField.getText()); System.out.println("1");
                Account a = new Account(name, balance); System.out.println("2");
                Account.addToAcctList(a); System.out.println(currentUser);
                BeFinanced.getUser().addAccount(a); System.out.println("4");
                window = getEventWindow(createFinAcct); System.out.println(window);
                updateScene(ACCT_VIEW, 600, 600);
            }
            catch (Exception ex) {
                missingFields.setText("Balance must be numeric.");
                System.out.println("newFinAcct Exception: " + ex);
            }
        }
    }
}
