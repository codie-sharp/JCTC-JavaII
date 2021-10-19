// Purpose: Set up a structure that will download information to a dat file
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class CourseData 
{
	private static ArrayList <Course> courses= new ArrayList<Course>();
	private static File courseFile = new File("Course.dat");
	
	public static void initialize()
	{
		try
		{
			FileReader fr = new FileReader(courseFile);
			BufferedReader in = new BufferedReader(fr);
			String prefix, section;
			int courseNo, enrolled;
			
			while(true)
			{
				prefix = in.readLine();
				if(prefix == null)
				 break;
				courseNo = Integer.parseInt(in.readLine());
				section = in.readLine();
				enrolled = Integer.parseInt(in.readLine());
				
				// create course instance and add reference to ArrayList courses
				if(prefix != null)
				{
					courses.add(new Course(prefix, courseNo, section, enrolled));
				}
			}//end while
		}//end try
		catch (IOException e)
		{
		JOptionPane.showMessageDialog(null, "Error reading file");
		}
	}

	public static void addNew(String aPrefix, int aCourseNo, String aSection, int registered)
	{
		Course aCourse = new Course(aPrefix, aCourseNo, aSection, registered);
		courses.add(aCourse);
		JOptionPane.showMessageDialog(null, "Course added");
		
		for(int i = 0; i< courses.size(); i++)
		{
			try
			{
				FileOutputStream CourseOut = new FileOutputStream(courseFile,true);
				PrintWriter out = new PrintWriter(CourseOut);
				aCourse =(Course) courses.get(i);
				out.println(aCourse.getPrefix());
				out.println(aCourse.getCourseNo());
				out.println(aCourse.getSection());
				out.println(aCourse.getEnrolled());
				out.close();
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(null,"Please check your input. "
				+ "The course number and enrolled must be integers. All fields must be entered");
			}
		}
	}

	public static ArrayList <Course> getAll()
	{
		return courses;
	}

}
