import java.util.*;
import java.io.*;
import java.io.Serializable;
public class Account implements Serializable {
    private final String accountName;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String initAccountName, double initBalance) {
        accountName = initAccountName;
        balance = initBalance;
    }

    public String getName() {return accountName;}

    // public void setName(String newName) {accountName = newName;}

    public double getBalance() {return balance;}

    public ArrayList<Transaction> getTransactions() {return transactions;}

    public void setBalance(double newBalance) {balance = newBalance;}

    public void changeBalance(double deltaB) {balance+= deltaB;}

        public static void addToAcctList(Account a) {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            FileInputStream fIn = new FileInputStream("./Data/accounts.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            accounts = (ArrayList<Account>) oIn.readObject();
            oIn.close();
            fIn.close();
            accounts.add(a);
            FileOutputStream fOut = new FileOutputStream("./Data/accounts.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(accounts);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("Account.addToAccountList(Account a) exception: " + e);
        }

    }

    public void processTransaction(Transaction t) {
        transactions.add(t);
        switch(t.getType()) {
            case DEPOSIT:
                balance += t.getAmount();
                break;
            case WITHDRAWL:
                balance -= t.getAmount();
                break;
        }
    }

    public static void deleteAccount(Account a) {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            FileInputStream fIn = new FileInputStream("./Data/accounts.ser");
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
            FileOutputStream fOut = new FileOutputStream("./Data/accounts.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(accounts);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("Account.deleteAccount(Account a) exception: " + e);
        }

    }

    public static void updateAcctList(Account a) {
        ArrayList<Account> accounts = new ArrayList<Account>();
        try {
            FileInputStream fIn = new FileInputStream("./Data/accounts.ser");
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
            FileOutputStream fOut = new FileOutputStream("./Data/accounts.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(accounts);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("Account.deleteAccount(Account a) exception: " + e);
        }

    }

    // create inital empty file
    // comment out once inital list is created so that it doesn't overwrite actual data
    
    public static void createInitialAccountList() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        Account a = new Account("test", 500.0);
        accounts.add(a);
        try {
            FileOutputStream fOut = new FileOutputStream("./Data/accounts.ser");
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
