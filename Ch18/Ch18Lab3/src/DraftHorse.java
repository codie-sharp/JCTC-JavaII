/*
* Program Chapter 17 Lab 3. This class inherits attributes and methods from the Horse class
*/

public class DraftHorse extends Horse 
{
	// additional attributes beyond those inherited from Horse
	private String classification;
	private String draftBreed;

	public DraftHorse(int anIdNo, String aCoatColor,int anAge,double aHeight,String
	aClassification,String aDraftBreed)
	{
		// invoke super class constructor
		super(anIdNo,aCoatColor,anAge,aHeight);
		// set subclass attribute values
		setClassification(aClassification);
		setDraftBreed(aDraftBreed);
	}
	
	// set mutator methods
	public void setClassification(String aClassification)
	{
		classification=aClassification;
	}
	
	public void setDraftBreed(String aDraftBreed)
	{
		draftBreed = aDraftBreed;
	}
	
	// get accessor methods
	public String getClassification()
	{
		return classification;
	}
	
	public String getDraftBreed()
	{
		return draftBreed;
	}
}
