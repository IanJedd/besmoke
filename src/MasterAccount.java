package besmoke.src;
import java.util.*;
import java.io.*;

public class MasterAccount extends Account {

    public MasterAccount(String accName, Double bal, String description, String phone, String email) {
        super(accName, bal, description, phone, email);
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
        MasterAccount m = new MasterAccount("Master Account", 0.0, "", "", "");
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
