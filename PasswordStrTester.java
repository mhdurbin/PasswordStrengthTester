package PasswordStrengthTester;

import java.util.HashMap;
import java.util.Set;
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
	static int negOccurNum[] = new int[2];
	static int negValOfRepeatChars = 0;
	static int negValOfConsUpperLetters = 0;
	static int stringLen = 0;
	
	// Calculates the number of Consecutive Uppercase Letters - There is a flaw in the logic, which is throwing off the count. Need to look into this more.
	public static void calcConsUpperLetters(String password) {
		int numOfRepeatUpperLetters = 0;
		for(int i = 0; i < password.length(); i++) {
			int nextLetter = i+1;
			if(nextLetter < password.length()) {
				if(Character.isUpperCase(password.charAt(i)) && Character.isUpperCase(password.charAt(nextLetter))) {
//					System.out.println("Character at i: " + password.charAt(i) + i);
//					System.out.println("Character at nextLetter: " + password.charAt(nextLetter) + nextLetter);
					numOfRepeatUpperLetters += 1;
				}
			}
		}
		negValOfConsUpperLetters = -numOfRepeatUpperLetters*2;
		System.out.println("Negative value of consecutive Uppercase: " + negValOfConsUpperLetters);
		System.out.println("Number of consecutive Uppercase: " + numOfRepeatUpperLetters);
	}
	
	// Calculates the number of repeat Characters
	public static void setRepeatCharactersValue(String password) {

		// Initializing number of repeat characters to be counted
		int numOfRepeatCharacters = 0;

		// Creating the HashMap that will keep track of the repeat characters
		// and how many there are
		HashMap<Character, Integer> repeatCharMap = new HashMap<Character, Integer>();

		// Converting the String to a Character Array
		char[] passwordCharArray = password.toCharArray();

		// Searching the Character Array to see if already in HashMap
		// If in HashMap, then incrementing count by 1
		// If not in HashMap, then adding to HashMap with value of 1
		for (char c : passwordCharArray) {
			if (repeatCharMap.containsKey(c)) {
				repeatCharMap.put(c, repeatCharMap.get(c) + 1);
			} else {
				repeatCharMap.put(c, 1);
			}
		}

		// Creating a Set that contains all keys of the HashMap
		Set<Character> charsInString = repeatCharMap.keySet();

		// Searching the Set to get total count of repeat characters
		// Only increases count of the total if character has more than 1
		// occurrence
		for (Character ch : charsInString) {
			if (repeatCharMap.get(ch) > 1) {
				numOfRepeatCharacters += repeatCharMap.get(ch);
				System.out.println(ch + " : " + repeatCharMap.get(ch));
			}
		}

		// Sets the Negative Value of the total number of repeat characters
		negValOfRepeatChars = (-numOfRepeatCharacters * 2) * numOfRepeatCharacters;

		System.out.println("Negative value of repeat characters: " + negValOfRepeatChars);
		System.out.println("Number of repeat Characters: " + numOfRepeatCharacters);
	}

	// Calculates the total value of both the Positive and Negative numbers
	public static void setTotalValue() {

		int posVal = calcPosVals();
		int negVal = calcNegVals();

		totalVal = posVal + negVal;
	}

	// Calculates the total Positive Values
	public static int calcPosVals() {

		int totalPosVal = 0;

		// Calculating the values associated with each individual character in
		// the charValue array
		for (int i = 0; i < charValue.length; i++) {
			totalPosVal += charValue[i];
		}

		System.out.println();
		System.out.println("Total Positive Value: " + totalPosVal);

		return totalPosVal;
	}

	// Calculates the total negative Values
	public static int calcNegVals() {

		int totalNegVal = 0;

		// Calculating the values associated with each individual character in
		// the negCharValue array
		for (int i = 0; i < charValue.length; i++) {
			totalNegVal += negCharValue[i];
		}

		// Adding the negative values from repeat characters and consecutive
		// Uppercase letters, occurence
		totalNegVal += negValOfRepeatChars;
		totalNegVal += negValOfConsUpperLetters;

		System.out.println();
		System.out.println("Total Negative Value: " + totalNegVal);

		return totalNegVal;
	}

	// Created to set the Negative value of each Character in the String
	public static void setNegValue(String password) {

		// Setting the base negative value of the Characters below:
		// Lowercase/Uppercase Letters = -negOccurNum[0]
		// Digits = -negOccurNum[1]

		for (int i = 0; i < charValue.length; i++) {
			if (Character.isLetter(password.charAt(i))) {
				negOccurNum[0] += 1;
			} else if (Character.isDigit(password.charAt(i))) {
				negOccurNum[1] += 1;
			}
		}

		for (int i = 0; i < negCharValue.length; i++) {
			if (Character.isLetter(password.charAt(i))) {

				negCharValue[i] = -negOccurNum[0];

				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the letters: " + negCharValue[i]);
			} else if (Character.isDigit(password.charAt(i))) {

				negCharValue[i] = -negOccurNum[1];

				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the digits: " + negCharValue[i]);
			}
		}

		// Calling on the method to calculate negative values from repeat
		// characters and consecutive Uppercase letters
		setRepeatCharactersValue(password);
		calcConsUpperLetters(password);

	}

	// Created to set the Positive value of each Character in the String
	public static void setPosValue(String password) {

		// Setting the base positive value of the Characters below:
		// Lowercase Letters = (length - occurNum[0])*2
		// Uppercase Letters = (length - occurNum[1])*2
		// Digits = (occurNum[2]*4)
		// Special Characters = (occurNum[3]*6)

		for (int i = 0; i < charValue.length; i++) {
			if (Character.isLetter(password.charAt(i)) && Character.isLowerCase(password.charAt(i))) {
				occurNum[0] += 1;
			} else if (Character.isLetter(password.charAt(i)) && Character.isUpperCase(password.charAt(i))) {
				occurNum[1] += 1;
			} else if (Character.isDigit(password.charAt(i))) {
				occurNum[2] += 1;
			} else {
				occurNum[3] += 1;
			}
		}

		for (int i = 0; i < charValue.length; i++) {
			if (Character.isLetter(password.charAt(i)) && Character.isLowerCase(password.charAt(i))) {

				charValue[i] = ((password.length() - occurNum[0]) * 2);
				charValue[i] += (occurNum[0] * 4);

				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the lowercase letters: " + charValue[i]);
			} else if (Character.isLetter(password.charAt(i)) && Character.isUpperCase(password.charAt(i))) {

				charValue[i] = ((password.length() - occurNum[1]) * 2);
				charValue[i] += (occurNum[1] * 4);

				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the uppercase letters: " + charValue[i]);
			} else if (Character.isDigit(password.charAt(i))) {

				charValue[i] = (occurNum[2] * 4);
				charValue[i] += (occurNum[2] * 4);

				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the digits: " + charValue[i]);
			} else {

				charValue[i] = (occurNum[3] * 6);
				charValue[i] += (occurNum[3] * 4);

				System.out.println("Character = " + password.charAt(i));
				System.out.println("Value of the special characters: " + charValue[i]);
			}
		}

	}

	public static void main(String[] args) {

		// Initializing the int to record the length of the Password String
		// Prompting the user for the Password they would like to test - would
		// like to make this a hidden text, further research needed

		String password = JOptionPane.showInputDialog(null, "Enter the password you would like to test:",
				JOptionPane.QUESTION_MESSAGE);

		// If cancel is selected when entering a password, this will provide
		// prompt stating the program was cancelled
		if (password == null) {
			JOptionPane.showMessageDialog(null, "The program has been cancelled.");
			System.exit(0);
		}

		System.out.println("Entered password: " + password);

		// Initializing the length of the password
		// Setting the length of the charValue and negCharValue arrays
		stringLen = password.length();
		charValue = new int[stringLen];
		negCharValue = new int[stringLen];

		// Calling on the setPosValue, setNegValue, and setTotalValue methods
		setPosValue(password);
		setNegValue(password);
		setTotalValue();

		System.out.println();
		System.out.println("Total Password Value: " + totalVal);

		if (totalVal > 100) {
			JOptionPane.showMessageDialog(null, "You have an excellent password.");
		} else if (totalVal < 100 && totalVal >= 80) {
			JOptionPane.showMessageDialog(null,
					"You have a strong password, but it could be better. Below are some helpful tips for creating a strong password:\n"
							+ "The longer the password, the stronger the password.\n"
							+ "Try to include Uppercase letters, Lowercase letters, Numbers, and Special Characters in your password.\n");
		} else {
			JOptionPane.showMessageDialog(null,
					"Your password needs some help. Below are some helpful tips for creating a strong password:\n"
							+ "The longer the password, the stronger the password.\n"
							+ "Try to include Uppercase letters, Lowercase letters, Numbers, and Special Characters in your password.\n");
		}
	}

}