import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;

public class Ch18Lab2 extends JFrame 
{
	//variables needing class scope
	private ArrayList <Course>courses;
	private JComboBox <String> courseList;
	private JTextField prefixField, courseNoField,sectionField,enrolledField;
	private JLabel logoLabel,prefixLabel,courseNoLabel,sectionLabel,enrolledLabel;
	private Course aCourse;
	private JButton loadButton,addButton,stopButton;
	private Font largeFont;
	private ImageIcon logo;
	private Border emptyBdr;
	private Border lineBorder;
	private Border compoundBorder;
	private JPanel mainPanel, top, top1, center, bottom;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem exitMenu;

	public static void main(String[] args) 
	{
		new Ch18Lab2("List and Add a Course");
	}	
		
	public Ch18Lab2(String titleText)
	{
		super(titleText);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createIcon();
		createLabels();
		createComboBox();
		createTextFields();
		createButtons();
		createBorders();
		createPanels();
		createMenu();
		add(mainPanel);
		setSize(750,600);
		setVisible(true);
	}
	
	public void createIcon()
	{
		logo = new ImageIcon("courses.jpg");
	}
	
	public void createLabels()
	{
		largeFont = new Font("Times New Roman", Font.ITALIC,24);
		logoLabel = new JLabel(" Courses", SwingConstants.CENTER);
		logoLabel.setFont(largeFont);
		logoLabel.setIcon(logo);
		prefixLabel = new JLabel("Course Prefix: ", SwingConstants.RIGHT);
		courseNoLabel = new JLabel("Course Number: ", SwingConstants.RIGHT);
		sectionLabel = new JLabel("Section Number: ", SwingConstants.RIGHT);
		enrolledLabel = new JLabel("Number Enrolled: ", SwingConstants.RIGHT);
	}
	
	public void createComboBox()
	{
		courseList = new JComboBox<String>();
		courseList.setPreferredSize(new Dimension(300, 50));
		courseList.setBorder(BorderFactory.createTitledBorder("Course List"));
	}
	
	public void createButtons()
	{
		Dimension size = new Dimension(195,60);
		Font buttonFont = new Font("Helvetica", Font.BOLD, 14);
		loadButton = new JButton("Load Current Courses");
		loadButton.setPreferredSize(size);
		loadButton.setFont(buttonFont);
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				loadCourse();
			}
		});
		
		addButton = new JButton("Add New Course");
		addButton.setPreferredSize(size);
		addButton.setFont(buttonFont);
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addCourse();
			}
		});
		
		stopButton = new JButton("Exit Program");
		stopButton.setPreferredSize(size);
		stopButton.setFont(buttonFont);
		stopButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}
	
	public void createTextFields()
	{
		prefixField = new JTextField();
		courseNoField = new JTextField();
		sectionField = new JTextField();
		enrolledField = new JTextField();
	}
	
	public void createBorders()
	{
		emptyBdr = BorderFactory.createEmptyBorder(20,0,20,150);
		lineBorder = LineBorder.createBlackLineBorder();
		compoundBorder = new CompoundBorder(lineBorder, emptyBdr);
	}
	
	public void createPanels()
	{
		top = new JPanel(new GridLayout(2,1,0, 20));
		top1 = new JPanel();
		top1.setLayout(new FlowLayout(FlowLayout.CENTER));
		top1.add(courseList);
		top.add(logoLabel);
		top.add(top1);
		center = new JPanel();
		center.setLayout(new GridLayout(4,2,10,10));
		center.setBorder(compoundBorder);
		center.add(prefixLabel);
		center.add(prefixField);
		center.add(courseNoLabel);
		center.add(courseNoField);
		center.add(sectionLabel);
		center.add(sectionField);
		center.add(enrolledLabel);
		center.add(enrolledField);
		bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottom.add(loadButton);
		bottom.add(addButton);
		bottom.add(stopButton);
		mainPanel = new JPanel(new BorderLayout(10,20));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
		mainPanel.add(top, BorderLayout.NORTH);
		mainPanel.add(center, BorderLayout.CENTER);
		mainPanel.add(bottom, BorderLayout.SOUTH);
	}
	
	public void createMenu()
	{
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		menuBar.add(fileMenu);
		exitMenu = new JMenuItem("Exit");
		exitMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		fileMenu.add(exitMenu);
	}
	
	private void addCourse()
	{
		courseList.removeAllItems();
		try
		{
			String prefix = prefixField.getText();
			String strCourseNo = courseNoField.getText();
			String section = sectionField.getText();
			String strEnrolled = enrolledField.getText();
			
			if(prefix.length() == 0 || strCourseNo.length() == 0 || section.length() == 0 ||
			strEnrolled.length() == 0)
				JOptionPane.showMessageDialog(this, "Please Enter All Data");
			else
			{
				int courseNo = Integer.parseInt(strCourseNo);
				int enrolled = Integer.parseInt(strEnrolled);
				createCourses(prefix, courseNo, section, enrolled);
			}
		}
		
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this,"courseNo and enrolled must be numeric");
		}
	}
	
	private void loadCourse()
	{
		courseList.removeAllItems();
		courses = new ArrayList<Course>();
		//The CourseData getAll() method is called and returns a value assigned to the courses ArrayList
		courses = CourseData.getAll();
		for(int i=0;i<courses.size();i++)
		{
			aCourse = (Course) courses.get(i);
			courseList.addItem(aCourse.getPrefix() + " "+ aCourse.getCourseNo() + " "+
			aCourse.getSection() + " has " + aCourse.getEnrolled()+" students enrolled");
		}
	}
	
	private void createCourses(String prefix, int courseNo, String section, int enrolled)
	{
		CourseData.addNew(prefix, courseNo, section, enrolled);
		clear();
	}
	
	public void clear()
	{
		prefixField.setText("");
		courseNoField.setText("");
		sectionField.setText("");
		enrolledField.setText("");
		prefixField.requestFocus();
	}
}

