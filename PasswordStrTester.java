package PasswordStrengthTester;

import javax.swing.JOptionPane;

/**
 * @author Matthew Durbin
 *
 */
public class PasswordStrTester {
	
	int[] charValue;

	public void setValue(char val) {
		
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
		
		int stringLen = 0;
		
		String password = JOptionPane.showInputDialog(null, "Enter the password you would like to test:", JOptionPane.QUESTION_MESSAGE);
		
		stringLen = password.length();
		
		
		
	}

}
