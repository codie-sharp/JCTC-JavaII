import java.io.*;
import java.util.Formatter;

import javax.swing.JOptionPane;

public class Ch11PA1 {

	public static void main(String[] args) {
			File file = new File("MyLetters.txt");
			int ch;
			StringBuffer strContent = new StringBuffer("");
			FileInputStream fin = null;
		
			try
			{
				fin = new FileInputStream(file);
				while( (ch = fin.read()) != -1)
				 {
					 strContent.append((char)ch);
				 }
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
			System.out.println("Encrypting the contents of the file " +file
			+".\nThe encrypted file will be stored as Encrypted.txt.");
	}
}
