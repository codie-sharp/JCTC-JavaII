public class Course 
{
	//attributes
	private String prefix;
	private int courseNo;
	private String section;
	private int enrolled;
	
	// constructor
	public Course(String aPrefix, int aCourseNo, String aSection, int registered)
	{
		setPrefix(aPrefix);
		setCourseNo(aCourseNo);
		setSection(aSection);
		setEnrolled(registered);
	}
	
	// set mutator methods with simple validation
	public void setPrefix(String aPrefix)
	{
		prefix = aPrefix;
	}
	public void setCourseNo(int aCourseNo)
	{
		courseNo = aCourseNo;
	}
	public void setSection(String aSection)
	{
		section = aSection;
	}
	public void setEnrolled(int registered)
	{
		enrolled = registered;
	}

	// get accessor methods
	public String getPrefix()
	{
		return prefix;
	}
	public int getCourseNo()
	{
		return courseNo;
	}
	public String getSection()
	{
		return section;
	}
	public int getEnrolled()
	{
		return enrolled;
	}
}
