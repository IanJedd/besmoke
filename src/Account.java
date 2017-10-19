public class Account{
	private String accountName;
	private double balance;

	public Account(String initAccountName, double initBalance) {
		accountName = initAccountName;
		balance = initBalance;
	}

	public String getName() {return accountName;}

	public void setName(String newName) {accountName = newName;}

	public double getBalance() {return balance;}

	public void setBalance(double newBalance) {balance = newBalance;}
}