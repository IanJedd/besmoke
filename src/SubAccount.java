package besmoke.src;
import java.util.*;
import java.io.*;
public class SubAccount extends Account {
    private ArrayList<Integer> transactions;

    public SubAccount(String accName, Double bal, String description, String phone, String email) {
        super(accName, bal, description, phone, email);
        transactions = new ArrayList<Integer>();
    }
    public ArrayList<Integer> getTransactions() {return transactions;}

    public void processTransaction(Transaction t) {
        super.processTransaction(t);
        transactions.add(t.getID());
        Account.updateAcctList(this);
    }
}
