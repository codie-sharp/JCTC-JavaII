/*
* Superclass of different types of horses.
*/

public class Horse 
{
	// attributes
	private int idNo;
	private String coatColor;
	private int age;
	private double height;
	
	// constructor
	public Horse(int anIdNo, String aCoatColor, int anAge, double aHeight)
	{
		setIdNo(anIdNo);
		setCoatColor(aCoatColor);
		setAge(anAge);
		setHeight(aHeight);
	}
	
	// set mutator methods alternating the values of variables
	public void setIdNo(int anIdNo)
	{
		idNo = anIdNo;
	}
	
	public void setCoatColor(String aCoatColor)
	{
		coatColor=aCoatColor;
	}
	
	public void setAge(int anAge)
	{
			age = anAge;
	}
	
	public void setHeight(double aHeight)
	{
			height = aHeight;
	}
	
	// get accessor methods
	public int getIdNo()
	{
		return idNo;
	}
	
	public String getCoatColor()
	{
		return coatColor;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public double getHeight()
	{
		return height;
	}
}
