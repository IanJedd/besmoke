package besmoke.src;
import java.util.*;
import java.io.*;

public class MasterAccount extends Account {

    public MasterAccount(String accName, Double bal, String description, String phone, String email) {
        super(accName, bal, description, phone, email);
    }

    public static MasterAccount getMasterAccount() {
        try {
            FileInputStream fIn = new FileInputStream("./besmoke/src/Data/maccount.ser");
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            MasterAccount ma = (MasterAccount) oIn.readObject();
            oIn.close();
            fIn.close();
            return ma;
        }
        catch (Exception e) {
            System.out.println("Account.deleteAccount(Account a) exception: " + e);
            return null;
        }
    }


    public void processNewAccount(Account a) {
        this.changeBalance(a.getBalance());
    }

    public void processTransaction(Transaction t) {
        super.processTransaction(t);
        update();
    }

    private void update() {

        try {
            FileOutputStream fOut = new FileOutputStream("./besmoke/src/Data/maccount.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(this);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("MasterAccount.update() exception: " + e);
        }
    }

    public static void createInitialAccount() {
        MasterAccount m = new MasterAccount("Robyn Berg", 0.0, "Master Account", "", "");
        try {
            FileOutputStream fOut = new FileOutputStream("./besmoke/src/Data/maccount.ser");
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(m);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("MasterAccount.createInitialAccount() exception: " + e);
        }
    }

    public static void main(String[] args) {
        createInitialAccount();
    }
}
