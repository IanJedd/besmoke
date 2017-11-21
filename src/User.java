package besmoke.src;
import java.util.*;
import java.io.*;
import java.io.Serializable;
public class User implements Serializable {
    
    private int accLimit = 5;
	private String fName, lName, address;
    private Passwardo passwardo;
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
        this.passwardo = new Passwardo("default");
    }
    // populating constructor
    public User(String fName, String lName, String address, String username, CharSequence password) {
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.username = username;
        this.passwardo = new Passwardo(password.toString());
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
            this.accounts[i] = newAcc.getAccountName();
        }
	}
    public void setDefaultAccount(String name) {
        int i = 0;
        while(i < accLimit && !this.accounts[i].equals(name)) {
            i++;
        }
        String temp = this.accounts[0];
        this.accounts[0] = this.accounts[i];
        this.accounts[i] = temp;
    }

    public void deleteAccount(String name) {
        int i = 0;
        while(i < accLimit && !this.accounts[i].equals(name)) {
            i++;
        }
        this.accounts[i] = null;
    }

    
    public void setName(String fNameI, String lNameI) {
        this.fName = fNameI;
        this.lName = lNameI;
	}
    public void setAddress(String addressI) {	
        this.address = addressI;
    }

    private Passwardo getPasswardo() {return passwardo;}

    //temporary static methods for logging in:

    public static User logIn(String username, CharSequence password) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileInputStream fIn = new FileInputStream("./besmoke/src/Data/users.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            users = (ArrayList<User>) oIn.readObject();
            oIn.close();
            fIn.close();
            User tempUser = null;
            for(User u : users) {
                if (u.username.equals(username)) {
                    tempUser = u;
                    break;
                }
            }
            if (tempUser != null && tempUser.getPasswardo().checkPassword(password.toString())) {
                return tempUser;
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            for (int i = 0; i < e.getStackTrace().length; i++) {System.out.println(".getStackTrace[" + i + "]: " + e.getStackTrace()[0]);}
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
            FileInputStream fIn = new FileInputStream("./besmoke/src/Data/users.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            users = (ArrayList<User>) oIn.readObject();
            oIn.close();
            fIn.close();
            users.add(u);
            FileOutputStream fOut = new FileOutputStream("./besmoke/src/Data/users.ser");
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
            FileInputStream fIn = new FileInputStream("./besmoke/src/Data/users.ser");
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
            FileOutputStream fOut = new FileOutputStream("./besmoke/src/Data/users.ser");
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
            FileInputStream fIn = new FileInputStream("./besmoke/src/Data/users.ser");
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
            FileOutputStream fOut = new FileOutputStream("./besmoke/src/Data/users.ser");
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
            FileOutputStream fOut = new FileOutputStream("./besmoke/src/Data/users.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(users);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("User.createUserList(User u) exception: " + e);
        }

    }
    //testing
    public static void main(String[] args) {
        createInitialUserList();  // USED ONCE TO INITALIZE users.ser
    }

}

