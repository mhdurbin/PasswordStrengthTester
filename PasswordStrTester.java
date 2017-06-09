package PasswordStrengthTester;

import javax.swing.JOptionPane;

/**
 * @author Matthew Durbin
 *
 */
public class PasswordStrTester {
	
	// Creating a charValue array to set a value for each Character in the String
	// Creating an int equal to the Total of the charValue values
	// Creating an occurrence array (occurNum) to determine how many of one type of character is being used
	// Creating a negative occurrence array (negOccurNum) to determine the number of occurrences for the Negative values
	// because the Uppercase and Lowercase values are not calculated separately for the Negative values
	static int[] charValue;
	static int[] negCharValue;
	static int totalVal = 0;
	static int occurNum[] = new int[4];
	static int negOccurNum[] = new int[3];
	static int numOfRepeatCharacters = 0;
	static int negValOfRepeatChars = 0;
	
	public static void findRepeatCharacters(String password) {
		for(int i = 0; i < password.length(); i++) {
			for(int j = i+1; j < password.length(); j++) {
				if(password.charAt(i) == password.charAt(j)) {
					numOfRepeatCharacters += 1;
				}
			}
		}
		negValOfRepeatChars = -numOfRepeatCharacters*2;
		System.out.println("Negative value of repeat characters: " + negValOfRepeatChars);
		System.out.println("Number of repeat Characters: " + numOfRepeatCharacters);
	}
	
	// Calculates the total value of both the Positive and Negative numbers
	public static void calcTotalValue() {
		
		int posVal = calcPosVals();
		int negVal = calcNegVals();
		
		totalVal = posVal + negVal;
	}
	
	// Calculates the total Positive Values
	public static int calcPosVals() {
		
		int totalPosVal = 0;
		
		for(int i = 0; i < charValue.length; i++) {
			totalPosVal += charValue[i];
		}
		System.out.println();
		System.out.println("Total Positive Value: " + totalPosVal);
		
		return totalPosVal;
	}
	
	//Calculates the total negative Values
	public static int calcNegVals() {
		
		int totalNegVal = 0;
		
		for(int i = 0; i < charValue.length; i++) {
			totalNegVal += negCharValue[i];
		}
		
		totalNegVal += negValOfRepeatChars;
		System.out.println();
		System.out.println("Total Negative Value: " + totalNegVal);
		
		return totalNegVal;
	}
	
	// Created to set the Negative value of each Character in the String
	public static void setNegValue(String password) {
		
		for(int i = 0; i < charValue.length; i++) {
			if(Character.isLetter(password.charAt(i))) {
				negOccurNum[0] += 1;
			}else if(Character.isDigit(password.charAt(i))) {
				negOccurNum[1] += 1;
			}else{
				negOccurNum[2] += 1;
			}
		}
		
		for(int i = 0; i < negCharValue.length; i++) {
			//Create a method for repeat characters
			findRepeatCharacters(password);
			
			if(Character.isLetter(password.charAt(i))) {
				negCharValue[i] = -negOccurNum[0];
				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the letters: " + negCharValue[i]);
			}else if(Character.isDigit(password.charAt(i))) {
				negCharValue[i] = -negOccurNum[1];
				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the digits: " + negCharValue[i]);
			}else{
				System.out.println("This was a special character or more complex situation than just Letters or Number");
				//Create a method for Consecutive Uppercase Letters
				//Create a method for Consecutive Lowercase Letters
				//Create a method for Consecutive Numbers
				//Create a method for Sequential Letters
				//Create a method for Sequential Numbers
				//Create a method for Sequential Special Characters
			}
		}
	}
	
	// Created to set the Positive value of each Character in the String
	public static void setPosValue(String password) {
		
		// Setting the base positive value of the Characters below:
		// Lowercase Letters = (length - occurNum[0])*2
		// Uppercase Letters = (length - occurNum[1])*2
		// Digits = (occurNum[2]*4)
		// Special Characters = (occurNum[3]*6)
		
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
		
		if(password == null) {
			System.out.println("The program has been cancelled.");
			System.exit(0);
		}
		
		stringLen = password.length();
		charValue = new int[stringLen];
		negCharValue = new int[stringLen];
		
		setPosValue(password);
		setNegValue(password);
		calcTotalValue();
		System.out.println();
		System.out.println(totalVal);
		
		if(totalVal > 100) {
			JOptionPane.showMessageDialog(null,"You have a strong password.");
		}else {
			JOptionPane.showMessageDialog(null,"Your password needs some help. Below are some helpful tips for creating a strong password:\n" 
					+ "The longer the password, the stronger the password.\n"
					+ "Try to include Uppercase letters, Lowercase letters, Numbers, and Special Characters in your password.\n");
		}
	}

}