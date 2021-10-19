/* Display file in a text area. File is chosen from a dialog box. 
 * The JFileChooser class will be demonstrated. */

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JColorChooser;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class Ch18Lab1 extends JFrame 
{
	private JButton jbtBrowse;
	// Text field to receive file name
	private JTextField jtfFile;
	// Text area to display file
	private JTextArea jtaFileContent;
	// Create jFileChooser
	private JFileChooser jFileChooser;
	//Create panel
	private JPanel p;
	//ScrollPane
	private JScrollPane jsp;
	//Create Menu
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenu editMenu;
	private JMenu colorMenu;
	private JMenuItem openMenu;
	private JMenuItem exitMenu;
	private JMenuItem selectAllMenu;
	private JMenu chooseColor;
	private JMenuItem setBackgroundMenu;
	private JMenuItem setForegroundMenu;
	private JMenuItem aboutMenu; 
	//Create a Font object
	private Font font = new Font("Helvetica", Font.BOLD, 16);
	
	public static void main(String[] args)
	{
		Ch18Lab1 frame = new Ch18Lab1();
		frame.setSize(400, 300);
		frame.setTitle("Chapter 18 Lab1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);
	}

	public Ch18Lab1()
	{
		createMenu();
		createPanel();
		// Use BorderLayout for the frame
		setLayout(new BorderLayout());
		add(jsp, BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);
		jtaFileContent.setBackground(Color.white);
		jtaFileContent.setForeground(Color.black);
	}
	
	public void createMenu()
	{
		ImageIcon bulletIcon = new ImageIcon("bullet.gif");
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		fileMenu = new JMenu("File");
		fileMenu.setFont(font);
		editMenu = new JMenu("Edit");
		colorMenu = new JMenu("Set Color");
		helpMenu = new JMenu("Help");
		menuBar.add(fileMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(editMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(colorMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(helpMenu);
		ImageIcon icon = new ImageIcon("file.png");
		
		openMenu = new JMenuItem("Open File");
		openMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				browse();
			}
		});
		openMenu.setIcon(bulletIcon);
		
		exitMenu = new JMenuItem("Exit Program");
		exitMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		exitMenu.setIcon(bulletIcon);
		
		selectAllMenu = new JMenuItem("Select All");
		selectAllMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jtaFileContent.selectAll();
			}
		});
		selectAllMenu.setIcon(bulletIcon);
		
		chooseColor = new JMenu("Change Color");
		chooseColor.setIcon(bulletIcon);
		setBackgroundMenu = new JMenuItem("Set Background Color");
		setBackgroundMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Color selectedColor = JColorChooser.showDialog(null,
				"Choose Background Color", jtaFileContent.getBackground());
				
				if(selectedColor !=null)
					jtaFileContent.setBackground(selectedColor);
			}
		});
		setBackgroundMenu.setIcon(bulletIcon);
		
		setForegroundMenu = new JMenuItem("Set Text Color");
		setForegroundMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Color selectedColor = JColorChooser.showDialog(null, 
				"Choose Text Color", jtaFileContent.getForeground());
				
				if(selectedColor !=null)
					jtaFileContent.setForeground(selectedColor);
			}
		});
		setForegroundMenu.setIcon(bulletIcon);
		
		aboutMenu = new JMenuItem("About Program");
		aboutMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Click Browse and Choose a File to Display", 
				"JFileChooser Demo", JOptionPane.INFORMATION_MESSAGE, icon);
			}
		});
		aboutMenu.setIcon(bulletIcon);
		
		fileMenu.add(openMenu);
		fileMenu.add(exitMenu);
		editMenu.add(selectAllMenu);
		colorMenu.add(chooseColor);
		chooseColor.add(setBackgroundMenu);
		chooseColor.add(setForegroundMenu);
		helpMenu.add(aboutMenu);
		fileMenu.setMnemonic('F');
		helpMenu.setMnemonic('H');
		editMenu.setMnemonic('E');
		colorMenu.setMnemonic('C');
		openMenu.setMnemonic('O');
		exitMenu.setMnemonic('X');
		selectAllMenu.setMnemonic('S');
		setBackgroundMenu.setMnemonic('B');
		setForegroundMenu.setMnemonic('T');
		aboutMenu.setMnemonic('A');
	}
	
	public void createPanel()
	{
		// Create a Panel to hold a label, a text field, and a button
		p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("Filename"), BorderLayout.WEST);
		jtfFile = new JTextField();
		p.add(jtfFile, BorderLayout.CENTER);
		jtfFile.setBackground(Color.white);
		jtfFile.setForeground(Color.black);
		jtfFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				showFile(new File(jtfFile.getText().trim()));
			}
		});
		
		jbtBrowse = new JButton("Browse");
		jbtBrowse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				browse();
			}
		});
		
		p.add(jbtBrowse, BorderLayout.EAST);
		// Create a scrollable text area
		jtaFileContent = new JTextArea();
		jsp = new JScrollPane(jtaFileContent);
		// Set default directory to the current directory
		jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(new File("."));
	}
	
	private void browse()
	{
		if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			showFile(jFileChooser.getSelectedFile());
		}
	}
	
	private void showFile(File file)
	{
		BufferedReader infile = null; //declare buffered stream
		//get file name from the text field
		String inLine;
		jtfFile.setText(file.getName());
		
		try
		{
			//create a buffered stream
			infile = new BufferedReader(new FileReader(file));
			//read a line
			inLine = infile.readLine();
			boolean firstLine = true;
			
			//append the line to the text area
			while (inLine != null)
			{
				if (firstLine)
				{
					firstLine = false;
					jtaFileContent.append(inLine);
				}
				else
				{
					jtaFileContent.append("\n" + inLine);
				}
				
				inLine = infile.readLine();
			}
		}
		
		catch (FileNotFoundException ex)
		{
			System.out.println("File not found: " + file.getName());
		}
		catch (IOException ex)
		{
			System.out.println(ex.getMessage());
		}
		finally
		{
			try
			{
				if (infile != null)
					infile.close();
			}
			catch (IOException ex)
			{
			}
		}
	}
}
