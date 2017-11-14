package besmoke.src;
import java.io.Serializable;
public class Transaction implements Serializable {
	private TransType type;
	private double amount;

	public Transaction(TransType initType, double initAmount) {
		type = initType;
		amount = initAmount;
	}
	
	public void setAmount(double newAmount) {amount = newAmount;}

	public double getAmount() {return amount;}

	public void setType(TransType newType) {type = newType;}

	public TransType getType() {return type;}

    public boolean isDeposit() {
        switch(this.type) {
            case DEPOSIT:
                return true;
            default:
                return false;
        }
    }

    public String getStringType() {
        switch(this.type) {
            case DEPOSIT:
                return "Deposit";
            default:
               return "Withdrawl";
        }
    } 


}
