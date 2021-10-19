import java.io.*;
import java.util.*;

public class Read {

	public static void main(String[] args) {
		int ch;
		StringBuffer strContent = new StringBuffer("");
		FileInputStream fin = null;
		
		try
		{
			 fin = new FileInputStream("numbers.txt");
			 while( (ch = fin.read()) != -1)
			 {
				 strContent.append((char)ch);
			 }
			 fin.close();
		}
		catch(IOException e)
		{
			System.out.println("An error occurred.");
		}
		
		System.out.println(strContent);
	}
}
