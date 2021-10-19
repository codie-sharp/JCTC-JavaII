/*
* Program: Chapter 17 Lab 3. This program will create Saddle horses and
* Draft horses. It will also demonstrate the use of the CardLayout layout manager.
*/

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.JFrame;

public class Ch17Lab3 extends JFrame 
{
	private JPanel horsePanel;
	private CardLayout horseLayout;
	private JTextField coatColorField,ageField,heightField,horseIDField;
	private JTextField saddleBreedField, draftBreedField;
	private JLabel coatColorLabel, ageLabel, heightLabel, idLabel,saddleBreedLabel,drafthorseLabel;
	private JRadioButton saddleHorseRB, draftHorseRB;
	private JRadioButton americanSaddleRB, englishSaddleRB;
	private JRadioButton farmingRB,haulingRB;
	private JButton addButton, clearButton,closeButton;

	public static void main(String[] args) 
	{
		Ch17Lab3 frame = new Ch17Lab3();
		frame.setBounds(100,100,575,275);
		frame.setTitle("Horse Types");
		frame.setVisible(true);
	}
	
	public Ch17Lab3()
	{
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(5,1));
		JPanel upperPanel = new JPanel(new GridLayout(2,4));
		JPanel centerPanel = new JPanel(new FlowLayout());
		JPanel saddlePanel = new JPanel(new FlowLayout());
		JPanel draftPanel = new JPanel(new FlowLayout());
		JPanel lowerPanel = new JPanel(new FlowLayout());
		horseLayout = new CardLayout();
		horsePanel = new JPanel();
		horsePanel.setLayout(horseLayout);
		JLabel logoLabel = new JLabel(" ", SwingConstants.CENTER);
		logoLabel.setFont(new Font("Helvetica", Font.BOLD,24));
		logoLabel.setText("Horse Types");
		c.add(logoLabel);
		
		//build upper panel
		Font fontLabel = new Font("Helvetica",Font.BOLD, 12);
		coatColorField = new JTextField(10);
		ageField = new JTextField(4);
		heightField = new JTextField(4);
		horseIDField = new JTextField(3);
		coatColorLabel = new JLabel("Horse's Coat Color: ",SwingConstants.RIGHT);
		upperPanel.add(coatColorLabel);
		coatColorLabel.setFont(fontLabel);
		upperPanel.add(coatColorField);
		ageLabel = new JLabel("Horse's Age: ", SwingConstants.RIGHT);
		ageLabel.setFont(fontLabel);
		upperPanel.add(ageLabel);
		upperPanel.add(ageField);
		heightLabel = new JLabel("Horse's Height: ", SwingConstants.RIGHT);
		heightLabel.setFont(fontLabel);
		upperPanel.add(heightLabel);
		upperPanel.add(heightField);
		idLabel = new JLabel("Horse's ID #: ", SwingConstants.RIGHT);
		idLabel.setFont(fontLabel);
		upperPanel.add(idLabel);
		upperPanel.add(horseIDField);
		c.add(upperPanel);
		
		//build center panel
		saddleHorseRB = new JRadioButton("Saddle Horse", true);
		draftHorseRB = new JRadioButton("Draft Horse", false);
		//create a Button Group for horse type buttons
		ButtonGroup typeOfHorse = new ButtonGroup();
		typeOfHorse.add(saddleHorseRB);
		typeOfHorse.add(draftHorseRB);
		//add radio buttons to centerPanel
		centerPanel.add(saddleHorseRB);
		centerPanel.add(draftHorseRB);
		c.add(centerPanel);
		
		//build saddle horse panel
		JPanel leftSaddlehorsePanel = new JPanel(new GridLayout(1,1));
		JPanel rightSaddlehorsePanel = new JPanel(new FlowLayout());
		saddleBreedField = new JTextField(10);
		americanSaddleRB = new JRadioButton("American Saddle", true);
		englishSaddleRB = new JRadioButton("English Saddle", false);
		ButtonGroup saddleHorseGroup = new ButtonGroup();
		saddleHorseGroup.add(americanSaddleRB);
		saddleHorseGroup.add(englishSaddleRB);
		saddleBreedLabel = new JLabel("Saddle Horse Breed: ");
		saddleBreedLabel.setFont(fontLabel);
		leftSaddlehorsePanel.add(saddleBreedLabel);
		leftSaddlehorsePanel.add(saddleBreedField);
		rightSaddlehorsePanel.add(americanSaddleRB);
		rightSaddlehorsePanel.add(englishSaddleRB);
		saddlePanel.add(leftSaddlehorsePanel);
		saddlePanel.add(rightSaddlehorsePanel);
		//create border for the panel
		saddlePanel.setBorder(BorderFactory.createLineBorder(Color.red));
		horsePanel.add(saddlePanel,"Saddle Horse");

		//build draft horse panel
		draftBreedField = new JTextField(10);
		farmingRB = new JRadioButton("Farming Horse", true);
		haulingRB = new JRadioButton("Hauling Horse",false);
		ButtonGroup drafthorseGroup = new ButtonGroup();
		drafthorseGroup.add(farmingRB);
		drafthorseGroup.add(haulingRB);
		drafthorseLabel = new JLabel("Draft Horse Breed: ");
		drafthorseLabel.setFont(fontLabel);
		draftPanel.add(drafthorseLabel);
		draftPanel.add(draftBreedField);
		draftPanel.add(farmingRB);
		draftPanel.add(haulingRB);
		draftPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		horsePanel.add(draftPanel,"Draft Horse");
		c.add(horsePanel);
		
		//create buttons for bottom panel
		addButton = new JButton("Add");
		clearButton = new JButton("Clear");
		closeButton = new JButton("Close");
		lowerPanel.add(addButton);
		lowerPanel.add(clearButton);
		lowerPanel.add(closeButton);
		c.add(lowerPanel);
		
		//register frame as listener for events
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addHorse();
			}
		});
		
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
				shutDown();
			}
		});
		
		saddleHorseRB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				horseLayout.first(horsePanel);
			}
		});
		
		draftHorseRB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				horseLayout.next(horsePanel);
			}
		});
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
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
					addSaddlehorse(horseId,coatColor,age,height);
				}
				
				else
				{
					addDrafthorse(horseId,coatColor,age,height);
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
		saddleBreedField.setText("");
		draftBreedField.setText("");
		saddleHorseRB.doClick();
		americanSaddleRB.doClick();
		farmingRB.doClick();
		coatColorField.requestFocus();
	}
	
	private void shutDown()
	{
		System.exit(0);
	}
	
	private void addSaddlehorse(int anIdNo, String aCoatColor,int anAge,double aHeight)
	{
		ArrayList <SaddleHorse>saddleHorses=new ArrayList<SaddleHorse>();
		String saddleBreed = "";
		String saddleType= "";
		saddleBreed = saddleBreedField.getText();
		
		if(saddleHorseRB.isSelected())
		saddleType = "American Saddle";
		
		else
		saddleType = "English Saddle";
		SaddleHorse aSaddleHorse = new SaddleHorse(anIdNo, aCoatColor, anAge,
		aHeight,saddleType,saddleBreed);
		saddleHorses.add(aSaddleHorse);
		JOptionPane.showMessageDialog(this, "Saddle Horse added");
		
		for(int i = 0;i<saddleHorses.size();i++)
		{
			try
			{
				SaddleHorse nextHorse;
				FileOutputStream saddleHorseOut = new
				FileOutputStream("SaddleHorse.dat",true);
				PrintWriter saddleOut = new PrintWriter(saddleHorseOut);
				aSaddleHorse = (SaddleHorse) saddleHorses.get(i);
				saddleOut.println("ID #: " + aSaddleHorse.getIdNo());
				saddleOut.println("Coat Color: " + aSaddleHorse.getCoatColor());
				saddleOut.println("Age: " + aSaddleHorse.getAge());
				saddleOut.println("Height: " + aSaddleHorse.getHeight() + " hands high");
				saddleOut.println("Saddle Type: " + aSaddleHorse.getSaddleType());
				saddleOut.println("Breed: " + aSaddleHorse.getSaddleBreed());
				saddleOut.close();
			}
			
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(null,"Please check your input. The id number "
				+ "and age must be whole numbers. The height field must be numeric. "
				+ "All fields must be entered");
			}
		}
		
		clearHorse();
	}

	private void addDrafthorse(int anIdNo, String aCoatColor, int anAge, double aHeight)
	{
		ArrayList <DraftHorse>draftHorses=new ArrayList<DraftHorse>();
		String draftBreed,classification;
		draftBreed = draftBreedField.getText();
		
		if(farmingRB.isSelected())
		classification = farmingRB.getText();
		
		else
		classification = haulingRB.getText();
		DraftHorse aDraftHorse = new DraftHorse(anIdNo, aCoatColor, anAge, aHeight,
		classification,draftBreed);
		draftHorses.add(aDraftHorse);
		JOptionPane.showMessageDialog(this, "Draft Horse has been added");
		
		for(int i = 0;i<draftHorses.size();i++)
		{
			try
			{
				DraftHorse nextHorse;
				FileOutputStream draftHorseOut = new FileOutputStream("DraftHorse.dat",true);
				PrintWriter draftOut = new PrintWriter(draftHorseOut);
				aDraftHorse =(DraftHorse) draftHorses.get(i);
				draftOut.println("ID #: " + aDraftHorse.getIdNo());
				draftOut.println("Coat Color: " + aDraftHorse.getCoatColor());
				draftOut.println("Age: " + aDraftHorse.getAge());
				draftOut.println("Height: " + aDraftHorse.getHeight() + " hands high");
				draftOut.println("Classification: " + aDraftHorse.getClassification());
				draftOut.println("Breed: " + aDraftHorse.getDraftBreed());
				draftOut.close();
			}
			
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(null,"Please check your input. The id "
				+ "number and age must be whole numbers. The height field must be "
				+ "numeric. All fields must be entered");
			}
		}
		clearHorse();
	}
}
