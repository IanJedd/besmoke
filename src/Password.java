import java.security.*;

public class Password {
	public static final int SALTYSIZE = 32; //in bytes
	private String saltedHashedPassword;
	private String salt;
	private SecureRandom random;

	public static void main(String args[]) {
		Password passy = new Password("ayy lmao");
	}

	public Password(String initPassword) {
		random = new SecureRandom();
		byte[] saltyBytes = new byte[SALTYSIZE];
		random.nextBytes(saltyBytes);
		for (int i = 0; i < SALTYSIZE; i++) {salt += (char) saltyBytes[i];}
		System.out.println(salt);
	}

	public void updateHashedPassword(String newPassword) {

	}

	public boolean checkPassword(String questionablePassword) {
		//placeholder
		return false;
	}
}