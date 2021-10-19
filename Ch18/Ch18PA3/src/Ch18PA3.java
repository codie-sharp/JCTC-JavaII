import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Ch18PA3 extends JFrame 
{
//Create arrays of dorms, meal plans, and their costs.
	private String[] dormNames = {"Allen Hall", "Pike Hall", "Farthing Hall", "University Suites"};
	private int[] dormCosts = {1500, 1600, 1200, 1800};
	private String[] mealPlans = {"7 meals per week", "14 meals per week", "Unlimited meals per week"};
	private int[] mealCosts = {560, 1095, 1500};
//Generic array lists so they can be used in JList? No idea what I'm doing
	//private DefaultListModel<String> listModel;
	//private ArrayList<String> dorms = new ArrayList<String>();
	//private ArrayList<String> meals = new ArrayList<String>();
	//private JList<String> dormItems, mealItems;
	//private JScrollPane scrollDorms, scrollMeals;
//Window components
	private JComboBox mealCombo, dormCombo;
	private JLabel dormMsg, mealMsg, chargesMsg;
	private JPanel comboPanel, buttonPanel;
	private JButton calcButton, exitButton;
	private JTextField chargesField;
	private int dormCharges, mealCharges;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem menuCalc, menuExit;
	NumberFormat money = NumberFormat.getCurrencyInstance();
	
	public Ch18PA3()
	{
	//Resizing makes it ugly, so I disabled it
		setVisible(true);
		setTitle("Dorm and Meal Plan Calculator");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//Create content pane and set layout and border
		JComponent cp = (JComponent)getContentPane();
		cp.setLayout(new BorderLayout(10, 10));
		cp.setBorder(new EmptyBorder(10,10,10,10));
	//Build menu, lists, buttons, panels
		createMenu();
		//createLists();
		buildComboPanel();
		buildButtonPanel();
		add(comboPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
	
	public void createMenu()
	{
	//Create menu bar and add "file"
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
	//Calc menu item
		menuCalc = new JMenuItem("Calculate Charges");
		menuCalc.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					chargesField.setText(money.format(dormCharges + mealCharges));
				}
			});
		fileMenu.add(menuCalc);
	//Exit menu item
		menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			});
		fileMenu.add(menuExit);
	}
	
	/*public void createLists()
	{
		//Create JLists from arrays
		listModel = new DefaultListModel<String>();
		dormItems = new JList<String>(listModel);
		dormItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dormItems.setVisibleRowCount(3);
		for(int i = 0; i < dormNames.length; i++)
			listModel.addElement(dormNames[i]);
		dormItems.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				int i = dormItems.getSelectedIndex();
				dormCharges = dormCosts[i];
			}
		});
		scrollDorms = new JScrollPane(dormItems);
		
		mealItems = new JList<String>(listModel);
		mealItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mealItems.setVisibleRowCount(3);
		for(int i = 0; i < mealPlans.length; i++)
			listModel.addElement(mealPlans[i]);
		mealItems.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				int i = mealItems.getSelectedIndex();
				mealCharges = mealCosts[i];
			}
		});
		scrollMeals = new JScrollPane(mealItems);
	}*/
	
	public void buildComboPanel()
	{
	//Create combo panel
		comboPanel = new JPanel(new GridLayout(2,2, 10, 5));
	//Label and combo for dorm cost
		dormMsg = new JLabel("Select Dorm: ");
		comboPanel.add(dormMsg);
		dormCombo = new JComboBox<String>(dormNames);
		dormCombo.setMaximumRowCount(3);
		dormCombo.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int i = dormCombo.getSelectedIndex();
					dormCharges = dormCosts[i];
				}
			});
		dormCombo.setSelectedIndex(0);
		//dormCombo.setModel(new DefaultComboBoxModel(dorms.toArray()));
		comboPanel.add(dormCombo);
		mealMsg = new JLabel("Select Meal Plan: ");
		comboPanel.add(mealMsg);
		mealCombo = new JComboBox<String>(mealPlans);
		mealCombo.setMaximumRowCount(3);
		mealCombo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i = mealCombo.getSelectedIndex();
				mealCharges = mealCosts[i];
			}
		});
		mealCombo.setSelectedIndex(0);
		//mealCombo.setModel(new DefaultComboBoxModel(meals.toArray()));
		comboPanel.add(mealCombo);
	}
	
	public void buildButtonPanel()
	{	
	//Create button panel 	
		buttonPanel = new JPanel(new GridLayout(2,2, 10, 5));
	//Label and text field to display charges
		chargesMsg = new JLabel("Total Charges per Semester: ");
		buttonPanel.add(chargesMsg);
		chargesField = new JTextField(money.format(dormCharges + mealCharges), 10);
		chargesField.setEditable(false);
		buttonPanel.add(chargesField);
	//Calculate charges based on selections
		calcButton = new JButton("Calculate Charges");
		calcButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					chargesField.setText(money.format(dormCharges + mealCharges));
				}
			});
		buttonPanel.add(calcButton);
	//Exit
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			});
		buttonPanel.add(exitButton);
	}
	
	public static void main(String[] args) 
	{
		new Ch18PA3();
	}
}