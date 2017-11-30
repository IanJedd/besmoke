package besmoke.src;
import java.util.*;
import java.io.*;
public class Transaction implements Serializable {
	private TransType type;
	private double amount;
    private final int id;
    private String accountName;
    private Date date;
    private String code;
    private String description;
    private static final String DATA = "./besmoke/src/Data/transactions.ser";

	public Transaction(TransType initType, double initAmount, String accountName, String code, String description) {
		type = initType;
		amount = initAmount;
        this.accountName = accountName;
        this.code = code;
        this.date = new Date();
        this.description = description;
        this.id = BeFinanced.getTList().size();
        addToTransactionList(this);
	}
	
    public String getAccountName() { return accountName; }
    public String getCode() { return code; }
	public double getAmount() {return amount;}
	public void setAmount(double newAmount) {amount = newAmount;}
	public TransType getType() {return type;}
    public int getID() { return id; }

    public boolean isDeposit() {
        switch(this.type) {
            case WITHDRAWAL:
                return false;
            default:
                return true;
        }
    }

    public String getStringType() {
        switch(this.type) {
            case CC_DEPOSIT:
                return "Credit Card Deposit";
            case CHECK_DEPOSIT:
                return "Check Deposit";
            default:
               return "Withdrawal";
        }
    } 

    public static ArrayList<Transaction> getTransactionList() {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            FileInputStream fIn = new FileInputStream(DATA);
            ObjectInputStream oIn = new ObjectInputStream(fIn);
            transactions = (ArrayList<Transaction>) oIn.readObject();
            oIn.close();
            fIn.close();
        }
        catch (Exception e) {
            System.out.println("Transaction.getTransactionList() exception: " + e);
        }
        return transactions;
    }

    private void addToTransactionList(Transaction t) {
        ArrayList<Transaction> transactions = BeFinanced.getTList();
        System.out.println(transactions);
        transactions.add(t);
        try {
            FileOutputStream fOut = new FileOutputStream(DATA);
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(transactions);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("Transaction.addToTransactionList(Transaction t) exception: " + e);
        }

    }
    // create inital empty file
    // comment out once inital list is created so that it doesn't overwrite actual data
    
    public static void createInitialTransactionList() {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        try {
            FileOutputStream fOut = new FileOutputStream(DATA);
            ObjectOutputStream oOut = new ObjectOutputStream(fOut);
            oOut.writeObject(transactions);
            oOut.close();
            fOut.close();
        }
        catch (Exception e) {
            System.out.println("Transaction.createInitalTransactionList() exception: " + e);
        }
    }

    public static void main(String[] args) {
        createInitialTransactionList();
    }
}
