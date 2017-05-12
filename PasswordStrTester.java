package PasswordStrengthTester;

import javax.swing.JOptionPane;

/**
 * @author Matthew Durbin
 *
 */
public class PasswordStrTester {
	
	// Creating a charValue array to set a value for each Character in the String
	int[] charValue;

	// Created to set the base value of each Character in the String
	public void setValue(char val) {
		
		// Setting the base value of the Characters below:
		// Lowercase Letters = 1
		// Uppercase Letters = 2
		// Digits = 1
		// Spaces Characters = 2
		// Special Characters = 2
		for(int i = 0; i > charValue.length; i++) {
			if(Character.isLetter(val) && Character.isLowerCase(val)) {
				charValue[i] = 1;
			}else if(Character.isLetter(val) && Character.isUpperCase(val)) {
				charValue[i] = 2;
			}else if(Character.isDigit(val)) {
				charValue[i] = 1;
			}else if(Character.isSpaceChar(val)) {
				charValue[i] = 2;
			}else{
				charValue[i] = 2;
			}
		}
		
	}
	
	/**
	 * 
	 */
	public static void main(String[] args) {
		
		// Initializing the int to record the length of the Password String
		// Prompting the user for the Password they would like to test - would like to make this a hidden text, further research needed
		int stringLen = 0;
		
		String password = JOptionPane.showInputDialog(null, "Enter the password you would like to test:", JOptionPane.QUESTION_MESSAGE);
		
		stringLen = password.length();
		
		
		
		
	}

}