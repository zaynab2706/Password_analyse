package cybersecurity;
import java.util.Scanner;
import java.util.Random;
public class PasswordAnalyzer {
	
	public static void PasswordAnalyser ( String password ) {
		int score = 0;
		boolean hasDigit = false;
		boolean hasUppercase = false;
		boolean hasLowercase = false;
		boolean hasSpecial = false;
		
		for (char c : password.toCharArray()) {
			if(Character.isDigit(c)) {
				hasDigit = true;
			}
			if(Character.isUpperCase(c)) {
				hasUppercase=true;
			}
			if(Character.isLowerCase(c)) {
				hasLowercase = true;
			}
			if(!Character.isLetterOrDigit(c)) {
				hasSpecial = true;
			}
		}
		
		if (password.length() < 8) {
			System.out.println("password too short");
			}
		else {
			System.out.println("Password lengh is good");
			 score += 20;
			}
		if(!hasDigit) {
			System.out.println("Password should contain a number");
		} 
		else {
			score += 20;
		}
		if(!hasUppercase) {
			System.out.println("Password should contain an uppercase letter.");
		}
		else {
			score += 20;
		}
		if(!hasLowercase) {
		    System.out.println("Password should contain a lowercase letter.");
		}
		else {
			score += 20;
		}
		if(!hasSpecial) {
		    System.out.println("Password should contain a special character.");
		}
		else {
			score += 20;
		}
		System.out.println("Password score: " + score + "/100");
		if (score < 40 ) {
			System.out.println("Strengh: Weak");
		}
		else if (score < 80) {
				System.out.println("Strengh: Medium");
				}
		else { 
			System.out.println("Strengh : Strong");
		}
    }
	
	public static void Passwordgenerator () {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
    	Random random = new Random();
    	String password = "";
    	for (int i=0 ; i<12 ; i++) {
    		int index = random.nextInt(characters.length());
    		password += characters.charAt(index);
    	}
    	System.out.println("Generated password: " + password);
    }
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your password:");
		String password = scanner.nextLine();

		PasswordAnalyser(password);
		PasswordSuggester();
		}

}
