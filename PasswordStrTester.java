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
	
	public static void calcTotalBaseValue() {
		
		for(int i = 0; i < charValue.length; i++) {
			totalVal += charValue[i];
		}
	}
	
	// Created to set the Positive value of each Character in the String
	public static void setPosValue(String password) {
		
		// Setting the base value of the Characters below:
		// flatVal = 1
		// Lowercase Letters = (length - flatVal)*2
		// Uppercase Letters = (length - flatVal)*2
		// Digits = (flatVal*4)
		// Special Characters = (flatVal*6)
		int flatVal = 1;
		
		for(int i = 0; i < charValue.length; i++) {
			if(Character.isLetter(password.charAt(i)) && Character.isLowerCase(password.charAt(i))) {
				charValue[i] = ((password.length() - flatVal)*2);
				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the lowercase letters: " + charValue[i]);
			}else if(Character.isLetter(password.charAt(i)) && Character.isUpperCase(password.charAt(i))) {
				charValue[i] = ((password.length() - flatVal)*2);
				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the lowercase letters: " + charValue[i]);
			}else if(Character.isDigit(password.charAt(i))) {
				charValue[i] = (flatVal*4);
			}else{
				charValue[i] = (flatVal*6);
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