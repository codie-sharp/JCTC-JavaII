/*Purpose: Demonstrate the Formatter, FileInputStream, File, FileOutputStream, 
and StringBuffer classes.*/

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Ch11Lab1 {

	public static void main(String[] args) {
		//The File class links to the name of the file to be read
		File file = new File("Ch11Lab1File.txt");
		int ch;
		//The StringBuffer class allows you to append to a String of characters
		StringBuffer strContent = new StringBuffer("");
		//The FileInputStream class reads the input supplied by the File class
		FileInputStream fin = null;
		
		try
		{
		 //Construct a Formatter object that uses the FileOutputStream class to link to the text file to be downloaded to
			 Formatter output = new Formatter(new FileOutputStream("Ch11Lab1File.txt", true));
		 //Generate 100 random numbers
			 for (int i = 0; i < 100; i++)
			 output.format("%d ", (int)(Math.random() * 100000)); //The %d formats to an integer
		 //always close the stream
			 output.close();
		}
		
		catch(IOException e)
		
		{
			JOptionPane.showMessageDialog(null, "Error creating file");
		}
		
		//notify the user that the download is complete
		JOptionPane.showMessageDialog(null, "Output Complete");
		
		try
		{
			 fin = new FileInputStream(file);
	   	//while there is anything to read through the FileInputStream append to the StringBuffer
			 while( (ch = fin.read()) != -1)
			 {
				 strContent.append((char)ch);
			 }
		 //close the stream
			 fin.close();
		}
		
		catch(FileNotFoundException e)
		
		{
			JOptionPane.showMessageDialog(null, "File Not Found. Check the name of the file.");
		}
		
		catch(IOException ioe)
		
		{
			System.out.println("Error reading the file" + ioe);
		}
		//Display the contents
		System.out.println(strContent);

	}

}
