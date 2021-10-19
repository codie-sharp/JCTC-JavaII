public class Ch15PA1 
{
	public static void main(String[] args) 
	{
		String str = "Good Morning Java!";
		System.out.println(reverse(str));
	}
	
	public static String reverse(String str)
	{
		if (str.length() <= 0)
		{
			return str;
		}
		return reverse(str.substring(1)) + str.charAt(0);
	}
}
