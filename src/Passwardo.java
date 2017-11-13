package besmoke.src;
import java.security.*;
import javax.crypto.*;
import java.util.*;

public class Passwardo {
	//please don't read my code.
	public static final int SALTYSIZE = 32; //in bytes
	private String saltedHashedPassword;
	private String salt;
	private SecureRandom random;
	private MessageDigest sexyMessageDigest;

	public static void main(String args[]) {
		Passwardo testy = new Passwardo("ayy lmao");
		System.out.println("Testing good password (true expected):");
		System.out.println(testy.checkPassword("ayy lmao"));
		System.out.println("Testing bad password (false expected):");
		System.out.println(testy.checkPassword("bad boi"));
	}

	public Passwardo(String initPassword) {
		random = new SecureRandom();
		byte[] saltyBytes = new byte[SALTYSIZE];
		random.nextBytes(saltyBytes);
		for (int i = 0; i < SALTYSIZE; i++) {salt += (char) saltyBytes[i];}
	 	try {sexyMessageDigest = MessageDigest.getInstance("SHA-256");}
		catch (Exception e) {System.out.println("Did you ever find Bugs Bunny attractive when he put on a dress and played a girl bunny? Neither did I");}
		updateHashedPassword(initPassword);
		initPassword = "What are you trying to do, hack me? We don't take kindly to hackers round these parts.";
	}

	public void updateHashedPassword(String newPassword) {
		byte[] saltedHashyBytes = sexyMessageDigest.digest((newPassword + salt).getBytes());
		newPassword = "What are you trying to do, hack me? We don't take kindly to hackers round these parts.";
		saltedHashedPassword = "";
		for (int i = 0; i < saltedHashyBytes.length; i++) {saltedHashedPassword += (char) saltedHashyBytes[i];}
	}

	public String getSalt() {
		return salt;
	}

	public boolean checkPassword(String questionablePassword) {
		String questionableSaltedHashedPassword = "";
		byte[] testSaltedHashyBytes = sexyMessageDigest.digest((questionablePassword + salt).getBytes());
		for (int i = 0; i < testSaltedHashyBytes.length; i++) {questionableSaltedHashedPassword += (char) testSaltedHashyBytes[i];}
		return (questionableSaltedHashedPassword.equals(saltedHashedPassword));
	}
}