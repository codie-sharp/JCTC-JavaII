
public class Ch15Lab2 {

	public static void main(String[] args) {
		// Create an array of strings to test.
		String[] testStrings = { "Able was I ere I saw Elba","Four score and seven years ago",
		"Now is the time for all good men","Desserts I stressed","Ask not what your country can do for you","Kayak" };
		// Test the strings.
		for (int i = 0; i < testStrings.length; i++)
		{
			System.out.print("\"" + testStrings[i] + "\"");
			if (isPalindrome(testStrings[i].toUpperCase()))
			System.out.println(" is a palindrome.\n");
			else
			System.out.println(" is not a palindrome.\n");
		}
	}
	
	/*
	* The isPalindrome method determines whether a string is a palindrome.
	* @param str The string to test.
	* @return This method returns true if str contains a palindrome. It returns false otherwise.
	*/	
	public static boolean isPalindrome(String str)
	{
		boolean status = false;
		if (str.length() <= 1)
		status = true;
		else if (str.charAt(0) == str.charAt(str.length()-1))
		status = isPalindrome (str.substring (1, str.length()-1));
		return status;
	}

}
