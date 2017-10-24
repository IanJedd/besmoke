public class Transaction {
	public enum Type {DEPOSIT, WITHDRAWL}
	private Type type;
	private double amount;

	public Transaction(Type initType, double initAmount) {
		type = initType;
		amount = initAmount;
	}
	
	public void setAmount(double newAmount) {amount = newAmount;}

	public double getAmount() {return amount;}

	public void setType(Type newType) {type = newType;}

	public Type getType() {return type;}

}
