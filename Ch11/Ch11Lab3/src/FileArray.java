import java.io.*;

/**
The FileArray class works with files and arrays
for the FileArray Class programming challenge.
*/

public class FileArray {
	/**
	 The writeArray method writes an int array to
	 a file.
	 @param filename The name of the file.
	 @param array The array to write.
	 @exception IOException When an IO error occurs.
	*/
	
	public static void writeArray(String filename, int[] array) throws IOException
	{
		 // Open the file.
		 FileOutputStream fstream = new FileOutputStream(filename);
		 DataOutputStream outFile = new DataOutputStream(fstream);
		 
		 // Write the array.
		 for (int index = 0; index < array.length; index++)
		 outFile.writeInt(array[index]);
		
		 // Close the file.
		 outFile.close();
	}
	
	/**
	 The readArray method reads integers from a file
	 into an int array.
	 @param filename The name of the file.
	 @param array The array to hold the integers.
	 @exception IOException When an IO error occurs.
	*/
	
	public static void readArray(String filename, int[] array) throws IOException
	{
		 // Open the file.
		 FileInputStream fstream = new FileInputStream(filename);
		 DataInputStream outFile = new DataInputStream(fstream);
		
		 // Read values from the array.
		 for (int index = 0; index < array.length; index++)
		 array[index] = outFile.readInt();
		 
		 // Close the file.
		 outFile.close();
	}

}
