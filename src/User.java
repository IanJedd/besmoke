import java.util.*;
import java.io.*;
import java.io.Serializable;
public class User implements Serializable {
    
    private int accLimit = 5;
	private String fName, lName, address;
    private CharSequence password;
    private final String username;
    // changed to string array of account names should be better for data storage/retrieval
	private String[] accounts;

    // default constructor
	public User() {
		
		this.fName = "default";
		this.lName = "default";
		this.address = "default";
		this.accounts = new String[accLimit];
        this.username = "default";
        this.password = (CharSequence) "default";
    }
    // populating constructor
    public User(String fName, String lName, String address, String username, CharSequence password) {
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.username = username;
        this.password = password;
        this.accounts = new String[accLimit];
    }

    public int[] hasInfo() {

        int[] output = new int[3];
        if (this.fName == "default") {
            output[0] = 0;
        } else {
            output[0] = 1;
        }
        if (this.lName == "default") {
            output[1] = 0;
        } else {
            output[1] = 1;
        }
        if (this.address == "default") {
            output[2] = 0;
        } else {
            output[2] = 1;
        }
        return output;		//for index 0,1,2 (fName, lName, address) default (0) or populated (1)

    }
    public boolean hasAccount() {
        if(this.accounts[0] == null) {
            return false; //no accounts under this user
        }
        return true;
	}

    public String[] getAccounts() {
        return accounts;
    }
    
    public void addAccount(Account newAcc) {
        int i = 0;
        while(i < accLimit && this.accounts[i] != null) {
            i++;
        }
        if(i == accLimit) {
            System.out.println("Error: maximum account limit exceeded.");
        } 
        else {
            this.accounts[i] = newAcc.getName();
        }
	}
    
    public void setName(String fNameI, String lNameI) {
        this.fName = fNameI;
        this.lName = lNameI;
	}
    public void setAddress(String addressI) {	
        this.address = addressI;
    }

    //temporary static methods for logging in:

    public static User logIn(String username, CharSequence password) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileInputStream fIn = new FileInputStream("./Data/users.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            users = (ArrayList<User>) oIn.readObject();
            oIn.close();
            fIn.close();
            User tempUser = null;
            for(User u : users) {
                System.out.println(u.username);
                System.out.println(username);
                if (u.username.equals(username)) {
                    tempUser = u;
                    break;
                }
            }

            if (tempUser != null && passwordsMatch(password, tempUser.password)) {
                return tempUser;
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            System.out.println("User.existsUser() exception: " + e);
            return null;
        }
    }

    public void logOut() {
        // TODO: Handle FinancialAccountSaving
        updateUserList(this);
    }



    public static void addToUserList(User u) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileInputStream fIn = new FileInputStream("./Data/users.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            users = (ArrayList<User>) oIn.readObject();
            oIn.close();
            fIn.close();
            users.add(u);
            FileOutputStream fOut = new FileOutputStream("./Data/users.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(users);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("User.addToUserList(User u) exception: " + e);
        }

    }

    public static void deleteUser(User u) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileInputStream fIn = new FileInputStream("./Data/users.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            users = (ArrayList<User>) oIn.readObject();
            oIn.close();
            fIn.close();
            int sz = users.size();
            for (int i=0; i<sz; i++) {
                if (users.get(i).username.equals(u.username)) {
                    users.remove(i);
                    break;
                }
            }
            FileOutputStream fOut = new FileOutputStream("./Data/users.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(users);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("User.deleteUser(User u) exception: " + e);
        }

    }

    public static void updateUserList(User u) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileInputStream fIn = new FileInputStream("./Data/users.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            users = (ArrayList<User>) oIn.readObject();
            oIn.close();
            fIn.close();
            int sz = users.size();
            for (int i=0; i<sz; i++) {
                if (users.get(i).username.equals(u.username)) {
                    users.remove(i);
                    users.add(u);
                    break;
                }
            }
            FileOutputStream fOut = new FileOutputStream("./Data/users.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(users);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("User.deleteUser(User u) exception: " + e);
        }

    }

    // create inital empty file
    // comment out once inital list is created so that it doesn't overwrite actual data
    
    public static void createInitialUserList() {
        ArrayList<User> users = new ArrayList<User>();
        User u = new User("Temp", "User", "Missoula, MT", "csadmin", "csci323");
        users.add(u);
        try {
            FileOutputStream fOut = new FileOutputStream("./Data/users.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(users);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("User.createUserList(User u) exception: " + e);
        }

    }
    private static boolean passwordsMatch(CharSequence p1, CharSequence p2) {
        if (p1.length() != p2.length()) { return false; }
        for (int i = 0; i < p1.length(); i++) {
            System.out.println("p1 char: " + p1.charAt(i) +" p2 char: " + p2.charAt(i));
            if (p1.charAt(i) != p2.charAt(i)) { return false; }
        }
        return true;
    }
    //testing
    public static void main(String[] args) {
        createInitialUserList();  // USED ONCE TO INITALIZE users.ser
    }

}

