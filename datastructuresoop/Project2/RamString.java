/**
 * @author Jasmin Reynoso
 *
 */
public class RamString implements WackyStringInterface {
	
	/* public static void main(String args[]) {
		RamString obj = new RamString("V00908905");
		obj.convertDigitsToWordsInSubstring(8, 4);
		System.out.print(obj.getWackyString());
		
	}  */

	String str = "";
	
	public RamString(String string) {
		if(string == null) {
			throw new IllegalArgumentException();
		}
		str = string;
	}
	
	public RamString() {
		str = "Rodney the Ram";
	}
	
	/**
	 * PARAMETERS: a String
	 * RETURNS: none
	 * DOES: checks if passed string is null, if not, sets it to instance variable
	 */
	@Override
	public void setWackyString(String string) {
		if(string == null) {
			throw new IllegalArgumentException();
			}
		str = string;
	}
	/**
	 * PARAMETERS: none
	 * RETURNS: String 
	 * DOES: returns instance variable String
	 */ 
	@Override
	public String getWackyString() {
		return str;
	}

	@Override
	/**
	 * PARAMETERS: none
	 * RETURNS: a String 
	 * DOES: checks if instance variable is empty, if not, returns first
	 *  character, middle character, and last character as a concatenated string
	 */
	public String getFirstMiddleLast() {
		if(str != "") {
		int midIndex = (str.length() - 1) / 2;
		int lastIndex = str.length() - 1;
		String sub = "" + str.charAt(0) + str.charAt(midIndex) + str.charAt(lastIndex);
		return sub;
		}
		return "";
	}

	/**
	 * PARAMETERS: none
	 * RETURNS: a String
	 * DOES: returns a string of every third character in instance variable concatenated
	 */
	@Override
	public String getEveryThirdCharacter() {
		String sub = "";
		for(int x = 0; x < str.length(); x++) {
			if((x + 1) % 3 == 0) {
				sub += str.charAt(x);
			}
		}
		return sub;
	}

	/**
	 * PARAMETERS: none
	 * RETURNS: an int value of even digits
	 * DOES: uses for loop to traverse through instance variable and counts all even digits
	 */
	@Override
	public int countEvenDigits() {
		int counter = 0;
		for(int x = 0; x < str.length(); x++) {
			if(Character.isDigit(str.charAt(x)) && str.charAt(x) % 2 == 0)  {
				counter++;
			}
		}
		return counter;
	}
	/**
	 * PARAMETERS: none
	 * RETURNS: boolean value of true if valid EID, false if invalid
	 * DOES: checks if instance variable has a capital V at first index,
	 *  if following digits are OO, and rest are valid digits
	 */
	@Override
	public boolean isValidEID() {
		boolean isValid = false;
		if(str.length() == 9 && str.charAt(0) == 'V' && str.substring(1, 3).equals("00")) {
			for(int x = 3; x < str.length(); x++) {
				if(Character.isDigit(str.charAt(3))) {
					isValid = true;
				} else {
					isValid = false;
				}
			}
			} else {
			isValid = false;
		}
		return isValid;
	}
	/**
	 * PARAMETERS: int value of lower bound to check, int value of higher bound to check
	 * RETURNS: none
	 * DOES: checks if bounds arent valid range, if so throw MyIndexOutOfBoundException, 
	 * checks if lower bound is greater than upper Bound, if so, throw IllegalArgumentException
	 *  if valid, creates new string with digits within specified range with their corresponding word, except for zero
	 *  and copies it into instance variable
	 */
	@Override
	public void convertDigitsToWordsInSubstring(int startPosition, int endPosition)
			throws MyIndexOutOfBoundsException, IllegalArgumentException {
		if(startPosition < 1 || endPosition > str.length() || startPosition > str.length() || endPosition < 1) {
			throw new MyIndexOutOfBoundsException();
		} else if(startPosition > endPosition) {
			throw new IllegalArgumentException();
		}
		String []strSegments = new String[str.length()];
		String newStr = "";
		for(int x = 0; x < str.length(); x++) {
			char strCh = str.charAt(x);
			if(x < endPosition && x >= (startPosition - 1)) {
			switch(strCh) {
			case '1':
				strSegments[x] = "ONE";
				break;
			case '2':
				strSegments[x] = "TWO";
				break;
			case '3':
				strSegments[x] = "THREE";
				break;
			case '4':
				strSegments[x] = "FOUR";
				break;
			case '5':
				strSegments[x] = "FIVE";
				break;
			case '6':
				strSegments[x] = "SIX";
				break;
			case '7':
				strSegments[x] = "SEVEN";
				break;
			case '8':
				strSegments[x] = "EIGHT";
				break;
			case '9':
				strSegments[x] = "NINE";
				break;
			default:
				strSegments[x] = "" + str.charAt(x);
				break;
			}
			} else {
				strSegments[x] = "" + str.charAt(x);
			}
		  newStr = newStr.concat(strSegments[x]);
		}
		str = newStr;

	}

}
