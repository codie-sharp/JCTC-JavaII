

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
* Purpose: create list of courses, and add them to the combo box, displaying the selected course's
* description to a JTextArea. When the downloadMenu is pressed the course description will be
* downloaded to a text file.
*/

public class Ch18Lab7 extends JFrame 
{
	//variables needing class scope
	private String[] courseNames = {"CIT105", "CIT111", "CIT120", "CIT125", "CIT130",
	"CIT140", "CIT141", "CIT142", "CIT143", "CIT144", "CIT145", "CIT147", "CIT148", "CIT149",
	"CIT150", "CIT151", "CIT152", "CIT155", "CIT157", "CIT160", "CIT161", "CIT167", "CIT170",
	"CIT171", "CIT180", "CIT182", "CIT184"};
	private ArrayList<String> courses = new ArrayList<String>();
	private ImageIcon courseIcon;
	private JLabel courseLabel;
	private JButton addButton, removeButton, sortButton, shuffleButton;
	private JTextArea displayArea;
	private JComboBox<String> coursesCombo;
	private DefaultListModel<String> listModel;
	private JList<String> courseItems;
	private JPanel topPanel,bottomPanel, westPanel, coursePanel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem downloadMenu, exitMenu;
	private Font font = new Font("Helvetica", Font.BOLD, 24);
	private JScrollPane scrollPane;
	
	public static void main(String[] args) 
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		Ch18Lab7 frame = new Ch18Lab7();
		frame.setSize(800,825);
		frame.setTitle("Courses and Their Descriptions");
		frame.setVisible(true);
	}

	public Ch18Lab7()
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenu();
		createLabel();
		createTextArea();
		createList();
		createCombo();
		createButtons();
		createPanels();
		setDisplay();
		JComponent cp = (JComponent)getContentPane();
		cp.setLayout(new BorderLayout(40,40));
		cp.setBorder(new EmptyBorder(20,20,20,20));
		cp.add(topPanel,BorderLayout.NORTH);
		cp.add(displayArea, BorderLayout.CENTER);
		cp.add(bottomPanel,BorderLayout.SOUTH);
		cp.add(westPanel, BorderLayout.WEST);
		cp.add(coursePanel, BorderLayout.EAST);
	}
	
	public void createMenu()
	{
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		downloadMenu = new JMenuItem("Download Description");
		exitMenu = new JMenuItem("Exit Program");
		fileMenu.add(downloadMenu);
		fileMenu.add(exitMenu);
		fileMenu.setMnemonic('F');
		downloadMenu.setMnemonic('D');
		exitMenu.setMnemonic('E');
		downloadMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Formatter output = new Formatter(new
					FileOutputStream("Ch18Lab6File.txt", true));
					output.format("%n%s%n", displayArea.getText());
					output.close();
				}
				catch(IOException ex)
				{
					JOptionPane.showMessageDialog(null, "Error creating file");
				}
			}
		});
		
		exitMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}
	
	public void createLabel()
	{
		courseIcon = new ImageIcon("courses.jpg");
		courseLabel = new JLabel("Example of the JList Class");
		courseLabel.setIcon(courseIcon);
		courseLabel.setFont(font);
	}

	public void createTextArea()
	{
		displayArea = new JTextArea(10,20);
		displayArea.setLineWrap(true);
		displayArea.setWrapStyleWord(true);
		displayArea.setEditable(false);
		displayArea.setBorder(new EmptyBorder(20,20,20,20));
	}
	
	public void createList()
	{
		listModel = new DefaultListModel<String>();
		courseItems = new JList<String>(listModel);
		courseItems.setVisibleRowCount(20);
		scrollPane = new JScrollPane(courseItems);
		for(int i = 0; i < courseNames.length; i++)
		listModel.addElement(courseNames[i]);
		courseItems.setBorder(new CompoundBorder(new LineBorder(Color.black),new
		EmptyBorder(0,10,0,10)));
	}
	
	public void createCombo()
	{
		coursesCombo = new JComboBox<String>();
		coursesCombo.setPreferredSize(new Dimension(100,25));
		coursesCombo.setModel(new DefaultComboBoxModel(courses.toArray()));
		coursesCombo.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				setDisplay();
			}
		});
	}
	
	public void createButtons()
	{
		addButton = new JButton("Add Item");
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				courses.add(courseItems.getSelectedValue());
				coursesCombo.revalidate();
				coursesCombo.removeAllItems();
				coursesCombo.setModel(new DefaultComboBoxModel(courses.toArray()));
				setDisplay();
			}
		});
		
		removeButton = new JButton("Remove First Item");
		removeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				courses.remove(0);
				coursesCombo.revalidate();
				coursesCombo.removeAllItems();
				coursesCombo.setModel(new DefaultComboBoxModel(courses.toArray()));
				setDisplay();
			}
		});
		
		sortButton = new JButton("Sort List");
		sortButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Collections.sort(courses);
				coursesCombo.revalidate();
				coursesCombo.removeAllItems();
				coursesCombo.setModel(new
				DefaultComboBoxModel(courses.toArray()));
				setDisplay();
			}
		});
		
		shuffleButton = new JButton("Shuffle List");
		shuffleButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Collections.shuffle(courses);
				coursesCombo.revalidate();
				coursesCombo.removeAllItems();
				coursesCombo.setModel(new
				DefaultComboBoxModel(courses.toArray()));
				setDisplay();
			}
		});
	}
	
	public void createPanels()
	{
		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
		topPanel.add(courseLabel);
		bottomPanel = new JPanel(new GridLayout(2,2, 20, 20));
		bottomPanel.add(addButton);
		bottomPanel.add(removeButton);
		bottomPanel.add(sortButton);
		bottomPanel.add(shuffleButton);
		westPanel = new JPanel(new BorderLayout());
		westPanel.setBorder(new EmptyBorder(20,20,20,20));
		westPanel.add(coursesCombo, BorderLayout.NORTH);
		coursePanel = new JPanel();
		coursePanel.add(scrollPane);
	}
	
	public String getDescription()
	{
		StringBuilder result = new StringBuilder();
		try
		{
			Scanner input = new Scanner(new File("text/" + coursesCombo.getSelectedItem() + ".txt"));
			while(input.hasNext())
			{
				result.append(input.nextLine() + '\n');
			}
		input.close();
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null,"Error loading file! Check name and location of file.");
		}
		return result.toString();
	}
	
	public void setDisplay()
	{
		if(coursesCombo.getItemCount() != 0)
		{
			displayArea.setText(getDescription());
		}
		else
		displayArea.setText("To add a course to the combo box, select a course from the list "
		+ "on the right and click either 'Add Item' or 'Add Item To Start'. To display a "
		+ "description of a course in the combo box, selected the course name. ");
	}

}
