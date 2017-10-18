public class User {
	
	private int accLimit = 5;
	
	private String		fName,
						lName,
						address;
	
	private Account[]	accounts;
	
	public User() {
		
		this.fName = "default";
		this.lName = "default";
		this.address = "default";
		
		this.accounts = new Account[accLimit];
		
	} public int[] hasInfo() {
		
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
		
	} public int hasAccount() {
		
		if(this.accounts[0] == null) {
			return 0; //no accounts under this user
		}
		
		return 1;
		
	} public void addAccount(Account newAcc) {
		
		int i = 0;
		boolean successful = false;
		
		while(i <= accLimit) {
			if(this.accounts[i] != null) {
				i += 1;
			} else {
				this.accounts[i] = newAcc;
				successful = true;
			}
		}
		
		if (!successful) {
			System.out.println("Error: maximum account limit exceeded.");
		}
		
	} public void setName(String fNameI, String lNameI) {
		
		this.fName = fNameI;
		this.lName = lNameI;
		
	} public void setAddress(String addressI) {
		
		this.address = addressI;
		
	} 

}

