import java.io.*;
import java.util.*;

public class Write {

	public static void main(String[] args) {
		File numbers = new File("numbers.txt");
		Random rando = new Random();
		FileInputStream fin = null;
		int[] array = new int[100];
		
			try
			{
				Formatter output = new Formatter(new FileOutputStream("numbers.txt", true));
				for (int i = 0; i < array.length; i++)
				{
					array[i] = rando.nextInt();
					System.out.println("Writing #" +(i+1));
					output.close();
				}
			}
			catch(IOException e)
			{
				System.out.println("An error occurred.");
			}
		
	}
}
