import javax.swing.JApplet;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;

public class Ch18Lab3 extends JApplet 
{
	private JPanel radioPanel, fieldPanel, topPanel, centerPanel, bottomPanel, saddlePanel, draftPanel;
	private JTextField coatColorField,ageField,heightField,horseIDField, saddleField, gearField;
	private JComboBox<String> saddleBreedList;
	private JComboBox<String> draftBreedList;
	private JLabel coatColorLabel, ageLabel, heightLabel, idLabel,saddleBreedLabel,drafthorseLabel,
	logoLabel, saddleLabel, gearLabel, saddleHorseLabel, draftLabel;
	private JRadioButton westernSaddleRB, englishSaddleRB, saddleHorseRB, draftHorseRB,
	draftSaddleRB,harnessRB;
	private ButtonGroup typeOfHorse, drafthorseGroup, saddleHorseGroup;
	private JButton clearButton,closeButton;
	private JButton[] addButtons;
	private JSplitPane splitPane;
	private Font fontLabel;

	public Ch18Lab3()
	{
		createLabels();
		createTextFields();
		createButtons();
		createComboBox();
		createRadioButtons();
		createSaddlePanel();
		createDraftPanel();
		createCenterPanel();
		createBottomPanel();
		add(logoLabel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	private void createLabels()
	{
		logoLabel = new JLabel("Horse Types", SwingConstants.CENTER);
		logoLabel.setBorder(new EmptyBorder(20,0,20,0));
		logoLabel.setIcon(new ImageIcon("horses.gif"));
		logoLabel.setFont(new Font("Helvetica", Font.BOLD,24));
		fontLabel = new Font("Helvetica",Font.BOLD, 12);
		coatColorLabel = new JLabel("Horse's Coat Color: ",SwingConstants.RIGHT);
		coatColorLabel.setFont(fontLabel);
		ageLabel = new JLabel("Horse's Age: ", SwingConstants.RIGHT);
		ageLabel.setFont(fontLabel);
		heightLabel = new JLabel("Horse's Height: ", SwingConstants.RIGHT);
		heightLabel.setFont(fontLabel);
		idLabel = new JLabel("Horse's ID #: ", SwingConstants.RIGHT);
		idLabel.setFont(fontLabel);
		saddleBreedLabel = new JLabel("Saddle Horse Breed: ", SwingConstants.CENTER);
		saddleBreedLabel.setFont(fontLabel);
		drafthorseLabel = new JLabel("Draft Horse Breed: ", SwingConstants.CENTER);
		drafthorseLabel.setFont(fontLabel);
		saddleLabel = new JLabel("Type of saddle ",SwingConstants.RIGHT);
		gearLabel = new JLabel("Saddle or Harness ",SwingConstants.RIGHT);
		saddleHorseLabel = new JLabel("Saddle Horse Information");
		draftLabel = new JLabel("Draft Horse Information");
	}
	
	private void createTextFields()
	{
		coatColorField = new JTextField(10);
		ageField = new JTextField(10);
		heightField = new JTextField(10);
		horseIDField = new JTextField(10);
		saddleField = new JTextField(10);
		saddleField.setEditable(false);
		gearField = new JTextField(10);
		gearField.setEditable(false);
	}

	private void createComboBox()
	{
		saddleBreedList = new JComboBox<String>();
		draftBreedList = new JComboBox<String>();
		saddleBreedList.addItem("American Saddlebred");
		saddleBreedList.addItem("Appaloosa");
		saddleBreedList.addItem("Arabian");
		saddleBreedList.addItem("Morgan");
		saddleBreedList.addItem("Quarter Horse");
		saddleBreedList.addItem("Tennessee Walking Horse");
		draftBreedList.addItem("Belgian");
		draftBreedList.addItem("Boulonnais");
		draftBreedList.addItem("Clydesdale");
		draftBreedList.addItem("Percheron");
		draftBreedList.addItem("Shire");
		draftBreedList.addItem("Suffolk");
	}
	
	private void createRadioButtons()
	{
		saddleHorseRB = new JRadioButton("Saddle Horse", false);
		draftHorseRB = new JRadioButton("Draft Horse", false);
		typeOfHorse = new ButtonGroup();
		typeOfHorse.add(saddleHorseRB);
		typeOfHorse.add(draftHorseRB);
		westernSaddleRB = new JRadioButton("Western Saddle", true);
		englishSaddleRB = new JRadioButton("English Saddle", false);
		saddleHorseGroup = new ButtonGroup();
		saddleHorseGroup.add(westernSaddleRB);
		saddleHorseGroup.add(englishSaddleRB);
		draftSaddleRB = new JRadioButton("Saddle", true);
		harnessRB = new JRadioButton("Harness",false);
		drafthorseGroup = new ButtonGroup();
		drafthorseGroup.add(draftSaddleRB);
		drafthorseGroup.add(harnessRB);
		saddleHorseRB.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					addButtons[0].setEnabled(true);
					addButtons[1].setEnabled(false);
				}
			});
		
		draftHorseRB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addButtons[1].setEnabled(true);
				addButtons[0].setEnabled(false);
			}
		});
		
		westernSaddleRB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saddleField.setText("Western saddle.");
			}
		});
		
		englishSaddleRB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saddleField.setText("English saddle");
			}
		});
		
		draftSaddleRB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gearField.setText("Saddle");
			}
		});
		
		harnessRB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gearField.setText("Harness");
			}
		});
	}
	
	private void createButtons()
	{
		addButtons = new JButton[2];
		addButtons[0] = new JButton("Add Saddle Horse");
		addButtons[1] = new JButton("Add Draft Horse");
		
		for(int i = 0; i < addButtons.length; i++)
		{
			addButtons[i].setEnabled(false);
		}
		clearButton = new JButton("Clear");
		closeButton = new JButton("Close");
		for(int i = 0; i < addButtons.length; i++)
		{
			addButtons[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					addHorse();
				}
			});
		}
		
		clearButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearHorse();
			}
		});
		
		closeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}

	private void createCenterPanel()
	{
		topPanel = new JPanel(new GridLayout(2,1));
		centerPanel = new JPanel(new GridLayout(2,1));
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, saddlePanel, draftPanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setResizeWeight(0.4);
		fieldPanel = new JPanel(new GridLayout(3,4));
		fieldPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		fieldPanel.add(coatColorLabel);
		fieldPanel.add(coatColorField);
		fieldPanel.add(ageLabel);
		fieldPanel.add(ageField);
		fieldPanel.add(heightLabel);
		fieldPanel.add(heightField);
		fieldPanel.add(idLabel);
		fieldPanel.add(horseIDField);
		fieldPanel.add(saddleLabel);
		fieldPanel.add(saddleField);
		fieldPanel.add(gearLabel);
		fieldPanel.add(gearField);
		radioPanel = new JPanel(new FlowLayout());
		radioPanel.add(saddleHorseRB);
		radioPanel.add( draftHorseRB);
		topPanel.add(fieldPanel);
		topPanel.add(radioPanel);
		centerPanel.add(topPanel);
		centerPanel.add(splitPane);
		centerPanel.setBorder(new EmptyBorder(0,10,0,10));
	}

	private void createSaddlePanel()
	{
		saddlePanel = new JPanel(new GridLayout(5,1));
		saddlePanel.add(saddleBreedLabel);
		saddlePanel.add(saddleBreedList);
		saddlePanel.add(westernSaddleRB);
		saddlePanel.add(englishSaddleRB);
		saddlePanel.add(addButtons[0]);
		//create border for the panel
		saddlePanel.setBorder(new LineBorder(Color.red, 1, true));
	}

	private void createDraftPanel()
	{
		draftPanel = new JPanel(new GridLayout(5,1));
		draftPanel.add(drafthorseLabel);
		draftPanel.add(draftBreedList);
		draftPanel.add(draftSaddleRB);
		draftPanel.add(harnessRB);
		draftPanel.add(addButtons[1]);
		draftPanel.setBorder(new LineBorder(Color.green, 1, true));
	}
	
	private void createBottomPanel()
	{
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		bottomPanel.add(clearButton);
		bottomPanel.add(closeButton);
	}

	private void addHorse()
	{
		try
		{
			String coatColor = coatColorField.getText();
			String strAge = ageField.getText();
			String strHeight = heightField.getText();
			String strHorseID = horseIDField.getText();
			if(coatColor.length() == 0 || strAge.length() == 0 || strHeight.length() == 0 ||
			strHorseID.length() == 0)
			JOptionPane.showMessageDialog(this, "Please Enter All Data");
			
			else
			{
				int age = Integer.parseInt(strAge);
				double height = Double.parseDouble(strHeight);
				int horseId = Integer.parseInt(strHorseID);
				if(saddleHorseRB.isSelected())
				{
					addSaddleHorse(horseId,coatColor,age,height);
				}
				else
				{
					addDraftHorse(horseId,coatColor,age,height);
				}
			}
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this,"Age, height and ID must be numeric");
		}
	}

	private void clearHorse()
	{
		coatColorField.setText("");
		ageField.setText("");
		heightField.setText("");
		horseIDField.setText("");
		saddleBreedList.setSelectedIndex(0);
		draftBreedList.setSelectedIndex(0);
		saddleHorseRB.doClick();
		coatColorField.requestFocus();
		saddleField.setText("");
		gearField.setText("");
	}

	private void addSaddleHorse(int anIdNo, String aCoatColor,int anAge,double aHeight)
	{
		String saddleBreed = "";
		String saddleType="";
		ArrayList <SaddleHorse> saddleHorses = new ArrayList<SaddleHorse>();
		saddleBreed = saddleBreedList.getItemAt(saddleBreedList.getSelectedIndex());
		if(westernSaddleRB.isSelected())
		{
			saddleType = "Western Saddle";
			saddleField.setText(saddleType);
		}
		else
		{
			saddleType = "English Saddle";
			saddleField.setText(saddleType);
		}
		SaddleHorse aSaddleHorse = new SaddleHorse(anIdNo, aCoatColor, anAge, aHeight,
		saddleType, saddleBreed);
		saddleHorses.add(aSaddleHorse);
		JOptionPane.showMessageDialog(this, "Saddle Horse added");
		for(int i = 0; i < saddleHorses.size(); i++)
		{
			try
			{
				FileOutputStream saddleHorseOut = new FileOutputStream("SaddleHorse.dat", true);
				PrintWriter saddleOut = new PrintWriter(saddleHorseOut);
				aSaddleHorse = (SaddleHorse) saddleHorses.get(i);
				saddleOut.println(aSaddleHorse.getIdNo());
				saddleOut.println(aSaddleHorse.getCoatColor());
				saddleOut.println(aSaddleHorse.getAge());
				saddleOut.println(aSaddleHorse.getHeight());
				saddleOut.println(aSaddleHorse.getSaddleType());
				saddleOut.println(aSaddleHorse.getSaddleBreed());
				saddleOut.close();
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(null, "Please check your input. The id number "
				+ "and age must be whole numbers. The height field must be numeric. "
				+ "All fields must be entered.");
			}
		}
		clearHorse();
	}
	
	private void addDraftHorse(int anIdNo, String aCoatColor, int anAge, double aHeight)
	{
		String draftBreed,classification;
		ArrayList<DraftHorse> draftHorses = new ArrayList<DraftHorse>();
		draftBreed = draftBreedList.getItemAt(draftBreedList.getSelectedIndex());
		if(draftSaddleRB.isSelected())
		{
			classification = "Saddle Horse";
			gearField.setText(classification);
		}
		else
		{
			classification = "Harness Horse";
			gearField.setText(classification);
		}
		DraftHorse aDraftHorse = new DraftHorse(anIdNo, aCoatColor, anAge, aHeight, classification,
		draftBreed);
		draftHorses.add(aDraftHorse);
		JOptionPane.showMessageDialog(this, "Heavy Horse has been added");
		for(int i = 0; i < draftHorses.size(); i++)
		{
			try
			{
			FileOutputStream draftHorseOut = new FileOutputStream("DraftHorse.dat",
			true);
			PrintWriter draftOut = new PrintWriter(draftHorseOut);
			aDraftHorse = (DraftHorse) draftHorses.get(i);
			draftOut.println(aDraftHorse.getIdNo());
			draftOut.println(aDraftHorse.getCoatColor());
			draftOut.println(aDraftHorse.getAge());
			draftOut.println(aDraftHorse.getHeight());
			draftOut.println(aDraftHorse.getClassification());
			draftOut.println(aDraftHorse.getDraftBreed());
			draftOut.close();
			}
			catch(IOException e)
			{
			JOptionPane.showMessageDialog(null, "Please check your input. The id number "
			+ "and age must be whole numbers. The height field must be numeric. "
			+ "All fields must be entered.");
			}
		}
		clearHorse();
	}

	public static void main(String[] args)
	{
		Ch18Lab3 applet = new Ch18Lab3();
		JFrame frame = new JFrame();
		frame.add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(625,450);
		frame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Horse Types");
		frame.setVisible(true);

	}

}
