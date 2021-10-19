
public class NegativePriceException extends Exception {
/**
No-arg constructor
*/
	
	public NegativePriceException()
	{
		super("Negative number given for item price.");
	}
	
	/**
	 This constructor reports the price that caused
	 the exception to be thrown.
	 @param price The invalid price.
	*/
	
	public NegativePriceException(double price)
	{
		super("Negative number given for price: " + price);
	}
}
