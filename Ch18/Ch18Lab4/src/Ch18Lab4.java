import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;

public class Ch18Lab4 extends JFrame 
{	
	private TopPanel topPanel = new TopPanel();
	private CenterPanel centerPanel = new CenterPanel();
	private JButton resetButton;
	private JButton stopButton;
	private Border emptyBdr;
	private JPanel bottomPanel, mainPanel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu pickNumbersMenu;
	private JMenuItem clearMenu, exitMenu;
	private JRadioButtonMenuItem pick5Numbers;
	private JRadioButtonMenuItem pickPowerball;
	private ButtonGroup group;
	private Font font = new Font("Arial", Font.BOLD, 14);
	
	public Ch18Lab4()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultLookAndFeelDecorated(true);
		createBorder();
		createButtons();
		createPanels();
		createMenu();
		add(mainPanel);
	}
	
	public void createBorder()
	{
		emptyBdr = BorderFactory.createEmptyBorder(20,20,20,20);
	}
	
	public void createButtons()
	{
		resetButton = new JButton("Clear");
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				centerPanel.setBallLabels("");
			}
		});
		
		stopButton = new JButton("Exit");
		stopButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}
	
	public void createPanels()
	{
		bottomPanel = new JPanel();
		bottomPanel.add(resetButton);
		bottomPanel.add(stopButton);
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(emptyBdr);
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		mainPanel.add(topPanel,BorderLayout.NORTH);
		mainPanel.add(bottomPanel,BorderLayout.SOUTH);
	}
	
	public void createMenu()
	{
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		fileMenu.setFont(font);
		pickNumbersMenu = new JMenu("Pick Your Numbers");
		pickNumbersMenu.setFont(font);
		pickNumbersMenu.setMnemonic('P');
		menuBar.add(fileMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(pickNumbersMenu);
		clearMenu = new JMenuItem("Reset Numbers");
		clearMenu.setToolTipText("Click here to clear numbers");
		clearMenu.setFont(font);
		clearMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				centerPanel.setBallLabels("");
			}
		});
		
		exitMenu = new JMenuItem("Exit Program");
		exitMenu.setToolTipText("Click here to exit program");
		exitMenu.setFont(font);
		exitMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		fileMenu.add(clearMenu);
		fileMenu.add(exitMenu);
		group = new ButtonGroup();
		pick5Numbers = new JRadioButtonMenuItem("Pick First 5 Numbers", false);
		pick5Numbers.setToolTipText("Click here to randomly generate first five numbers");
		pick5Numbers.setFont(font);
		pickPowerball = new JRadioButtonMenuItem("Pick Powerball Number", false);
		pickPowerball.setToolTipText("Click here to randomly generate powerball number");
		pickPowerball.setFont(font);
		group.add(pick5Numbers);
		group.add(pickPowerball);
		pickNumbersMenu.add(pick5Numbers);
		pickNumbersMenu.add(pickPowerball);
		pick5Numbers.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				centerPanel.changeNumberValues();
				group.clearSelection();
			}
		});
		
		pickPowerball.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				centerPanel.changePowerballValue();
			}
		});
	}

	public static void main(String[] args) 
	{
		Ch18Lab4 frame = new Ch18Lab4();
		frame.pack();
		frame.setTitle("Pick Your Numbers");
		frame.setVisible(true);
	}

}
