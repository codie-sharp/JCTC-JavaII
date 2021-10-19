import java.util.Scanner;

/**
This program demonstrates a solution to the
RetailItem Exceptions programming challenge.
*/

public class RetailItemDemo {

	public static void main(String[] args) {
		String description; // To hold the item description
		int units; // To hold the item units
		double price; // To hold the item price
		boolean goodInput; // Flag for validating input
		
		// Create a Scanner object for keyboard input.
		Scanner keyboard = new Scanner(System.in);
		
		// Create a RetailItem object.
		RetailItem item = new RetailItem();
		
		// Get the item description.
		System.out.print("Enter the item description: ");
		description = keyboard.nextLine();
		item.setDescription(description);
		
		// Get the item units.
		do
		{
			 System.out.print("Enter the units on hand: ");
			 units = keyboard.nextInt();
			 try
			 {
				 item.setUnitsOnHand(units);
				 // The next statement won't execute if units
				 // is negative because an exception will occur.
				 goodInput = true;
			 }
			 catch (NegativeUnitsException e)
			 {
				 System.out.println("Error: " + e.getMessage());
				 goodInput = false;
			 }
		} 
		while (!goodInput);

		// Get the item price.
		do
		{
			 System.out.print("Enter the item price: ");
			 price = keyboard.nextDouble();
			 try
			 {
				 item.setPrice(price);
				 // The next statement won't execute if price
				 // is negative because an exception will occur.
				 goodInput = true;
			 }
			 catch (NegativePriceException e)
			 {
				 System.out.println("Error: " + e.getMessage());
				 goodInput = false;
			 }
		} 
		while (!goodInput);
		
		// Display the info for item.
		System.out.println("You entered:");
		System.out.println("\tDescription: " + item.getDescription());
		System.out.println("\tUnits on hand: " + item.getUnitsOnHand());
		System.out.println("\tPrice: " + item.getPrice());
	}
}
