package PasswordStrengthTester;

import javax.swing.JOptionPane;

/**
 * @author Matthew Durbin
 *
 */
public class PasswordStrTester {
	
	// Creating a charValue array to set a value for each Character in the String
	// Creating an int equal to the Total of the charValue values
	static int[] charValue;
	static int totalVal = 0;
	static int occurNum[] = new int[4];
	
	public static void calcTotalBaseValue() {
		
		for(int i = 0; i < charValue.length; i++) {
			totalVal += charValue[i];
		}
	}
	
	// Created to set the Positive value of each Character in the String
	public static void setPosValue(String password) {
		
		// Setting the base value of the Characters below:
		// Lowercase Letters = (length - occurNum[0])*2
		// Uppercase Letters = (length - occurNum[1])*2
		// Digits = (occurNum[2]*4)
		// Special Characters = (occurNum[3]*6)
		// int occurNum[] = new int[4];
		
		for(int i = 0; i < charValue.length; i++) {
			if(Character.isLetter(password.charAt(i)) && Character.isLowerCase(password.charAt(i))) {
				occurNum[0] += 1;
			}else if(Character.isLetter(password.charAt(i)) && Character.isUpperCase(password.charAt(i))) {
				occurNum[1] += 1;
			}else if(Character.isDigit(password.charAt(i))) {
				occurNum[2] += 1;
			}else{
				occurNum[3] += 1;
			}
		}
		
		
		for(int i = 0; i < charValue.length; i++) {
			if(Character.isLetter(password.charAt(i)) && Character.isLowerCase(password.charAt(i))) {
				charValue[i] = ((password.length() - occurNum[0])*2);
				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the lowercase letters: " + charValue[i]);
			}else if(Character.isLetter(password.charAt(i)) && Character.isUpperCase(password.charAt(i))) {
				charValue[i] = ((password.length() - occurNum[1])*2);
				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the uppercase letters: " + charValue[i]);
			}else if(Character.isDigit(password.charAt(i))) {
				charValue[i] = (occurNum[2]*4);
				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the digits: " + charValue[i]);
			}else{
				charValue[i] = (occurNum[3]*6);
				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the special characters: " + charValue[i]);
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		
		// Initializing the int to record the length of the Password String
		// Prompting the user for the Password they would like to test - would like to make this a hidden text, further research needed
		int stringLen = 0;
		
		String password = JOptionPane.showInputDialog(null, "Enter the password you would like to test:", JOptionPane.QUESTION_MESSAGE);
		
		stringLen = password.length();
		charValue = new int[stringLen];
		
		setPosValue(password);
		calcTotalBaseValue();
		System.out.println(totalVal);
	}

}