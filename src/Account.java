package besmoke.src;
import java.util.*;
import java.io.*;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
public class Account implements Serializable {
    private final String accountName;
    private double balance;
    private static final double UNI_FEE = .08;
    private static final double CREDIT_FEE = .04;
    private double creditCardFees;
    private double universityFees;
    private String description;
    private String phone;
    private String email;

    public Account(String initAccountName, double initBalance, String d, String p, String e) {
        accountName = initAccountName;
        balance = initBalance;
        description = d;
        phone = p;
        email = e;
    }

    // Getters and Setters
    public String getAccountName() {return accountName;}
    public StringProperty accountNameProperty() { return new SimpleStringProperty(accountName); }

    public double getBalance() {return balance;}
    public DoubleProperty balanceProperty() { return new SimpleDoubleProperty(balance); }

    public String getDescription() {return description;}
    public StringProperty descriptionProperty() { return new SimpleStringProperty(description); }

    public String getPhone() {return phone;}
    public StringProperty phoneProperty() { return new SimpleStringProperty(phone); }

    public String getEmail() {return email;}
    public StringProperty emailProperty() { return new SimpleStringProperty(email); }

    
    // Balance Editors
    public void changeBalance(double deltaB){balance += deltaB;}

    // Transaction Processing
    public void processTransaction(Transaction t) {
        double amt = t.getAmount();
        double uniFee = amt * UNI_FEE;
        double ccFee = amt * CREDIT_FEE;
        switch(t.getType()) {
            case CC_DEPOSIT:
                amt -= (ccFee + uniFee);
                changeBalance(amt);
                creditCardFees += ccFee;
                universityFees += uniFee;
                break;
            case CHECK_DEPOSIT:
                System.out.println(amt);
                amt -= uniFee;
                changeBalance(amt);
                universityFees += uniFee;
                break;
            case WITHDRAWAL:
                System.out.println("Withdrawal");
                changeBalance(-1*amt);
                break;
        }
    }

    // Static Methods 
    public static void addToAcctList(Account a) {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            FileInputStream fIn = new FileInputStream("./besmoke/src/Data/accounts.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            accounts = (ArrayList<Account>) oIn.readObject();
            oIn.close();
            fIn.close();
            accounts.add(a);
            FileOutputStream fOut = new FileOutputStream("./besmoke/src/Data/accounts.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(accounts);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("Account.addToAccountList(Account a) exception: " + e);
        }

    }


    public static void deleteAccount(Account a) {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            FileInputStream fIn = new FileInputStream("./besmoke/src/Data/accounts.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            accounts = (ArrayList<Account>) oIn.readObject();
            oIn.close();
            fIn.close();
            int sz = accounts.size();
            for (int i=0; i<sz; i++) {
                if (accounts.get(i).accountName.equals(a.accountName)) {
                    accounts.remove(i);
                    break;
                }
            }
            FileOutputStream fOut = new FileOutputStream("./besmoke/src/Data/accounts.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(accounts);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("Account.deleteAccount(Account a) exception: " + e);
        }
    }
    public static Account getAccount(String aName) {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            FileInputStream fIn = new FileInputStream("./besmoke/src/Data/accounts.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            accounts = (ArrayList<Account>) oIn.readObject();
            oIn.close();
            fIn.close();
            int sz = accounts.size();
            for (int i=0; i<sz; i++) {
                if (accounts.get(i).accountName.equals(aName)) {
                    return accounts.get(i);
                }
            }
            return null;
        }
        catch (Exception e) {
            System.out.println("Account.deleteAccount(Account a) exception: " + e);
            return null;
        }

    }

    public static void updateAcctList(Account a) {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            FileInputStream fIn = new FileInputStream("./besmoke/src/Data/accounts.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            accounts = (ArrayList<Account>) oIn.readObject();
            oIn.close();
            fIn.close();
            int sz = accounts.size();
            for (int i=0; i<sz; i++) {
                if (accounts.get(i).accountName.equals(a.accountName)) {
                    accounts.remove(i);
                    accounts.add(a);
                    break;
                }
            }
            FileOutputStream fOut = new FileOutputStream("./besmoke/src/Data/accounts.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(accounts);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("Account.updateAcctList(Account a) exception: " + e);
        }

    }

    // create inital empty file
    // comment out once inital list is created so that it doesn't overwrite actual data
    
    public static void createInitialAccountList() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        Account a = new Account("test", 500.0, "", "", "");
        accounts.add(a);
        try {
            FileOutputStream fOut = new FileOutputStream("./besmoke/src/Data/accounts.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(accounts);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("Account.createUserList() exception: " + e);
        }
    }

    public static void main(String[] args) {
        createInitialAccountList();
    }
}
