//package cmsc256;
/**
 * This is an interface for a simple class that represents a string, defined
 * as a sequence of characters.
 */
public interface WackyStringInterface {

	/**
	 * Sets the value of the current string.
	 * @param string	The value to be set
	 */
	void setWackyString(String string);

	/**
	 * Returns the current string
	 * @return Current string
	 */
	String getWackyString();

	/**
	 * Returns a string that consists of only the characters
	 * in the first, middle and last positions in
	 * the current string, in the same order and with the same case as
	 * in the current string. The first character in the string is
	 * considered to be in Position 1.
	 *
	 * @return String 	made of characters in first, middle and last 
	 * 					positions in the current string
	 */
	String getFirstMiddleLast();

	/**
	 * Returns a string that consists of all and only the characters
	 * in the "every third" positions (i.e., third, sixth, ninth and so on)
	 * current string, in the same order and with the same case as in
	 *  in the current string. The first character in the string is
	 * considered to be in Position 1.
	 *
	 * @return String 	made of characters in every third position 
	 * 					in the current string
	 */
	String getEveryThirdCharacter();

	/**
	 * Returns the number of characters that are even digits
	 *  in the current string
	 * @return Number of even digits in the current string
	 */
	int countEvenDigits();
	
	/**
	 * Returns true if the current string has a capital V as it's first 
	 * character, followed by two zero characters (00), and then 
    * 6 additional digits. 
	 *	For example, V00123456 a valid VCU eID.
	 *
	 * @return true 	if current string is formated as valid VCU eID,
	 * 					false otherwise.
	 */
	boolean isValidEID();

	/**
	 * Replace the _individual_ digits in the current string, between
	 * startPosition and endPosition (included), with the corresponding
	 * word. The first character in the string is
	 * considered to be in Position 1. Digits are converted individually,
	 * even if contiguous, and digit "0" is not converted (e.g., 460 is
	 * converted to foursix0). This method changes the instance variable.
	 *
	 * @param startPosition		Position of the first character to consider
	 * @param endPosition		Position of the last character to consider
	 * @throws MyIndexOutOfBoundsException
	 *            	If either "startPosition" or "endPosition" are out of bounds
	 *             	(i.e., either less than 1 or greater than the length of the string)
	 * @throws IllegalArgumentException
	 *            If "startPosition" > "endPosition" (but both are within bounds)
	 */
	void convertDigitsToWordsInSubstring(int startPosition, int endPosition)
						throws MyIndexOutOfBoundsException, IllegalArgumentException;
}
