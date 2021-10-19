/*
* This program demonstrates a solution to the isMember Method programming challenge.
*/

public class Ch15Lab1 {

	public static void main(String[] args) {
		final int ARRAY_SIZE = 10;
		int[] numbers = {2, 4, 6, 8, 10, 12, 14, 16 ,18, 20 };
		// Test all of the values from 0 through 20 and see if they are in the array.
		for (int x = 0; x <= 20; x++)
		{
		if (isMember(numbers, x, ARRAY_SIZE))
		System.out.println(x + " is found in the array.\n");
		else
		System.out.println(x + " is not found in the array.\n");
		}
	}
	
	/**
	 The isMember method searches all or part of an array for value.
	 @param array The array to be searched.
	 @param value The value to search for.
	 @param size The size of the part of the array being searched.
	 @return If the value is found, the method returns true. Otherwise, it returns false.
	 */
	
	public static boolean isMember(int[] array, int value, int size)
	{
		if (size == 0)
		return false;
		else if (array[size - 1] == value)
		return true;
		else
		return isMember(array, value, size -1);
	}
}
