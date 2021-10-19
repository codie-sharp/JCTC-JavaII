import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Ch18PA2 extends JFrame 
{
//create an ImageIcon array with the 6 dice png. 
	ImageIcon[] dice = {new ImageIcon("Die1.png"), new ImageIcon("Die2.png"), new ImageIcon("Die3.png"),
	new ImageIcon("Die4.png"), new ImageIcon("Die5.png"), new ImageIcon("Die6.png")};
//declare interface components	
	private JPanel buttonPanel, die1Panel, die2Panel;
	private JLabel die1, die2;
	private JButton rollButton;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem rollMenu, exitMenu; 
//constructor	
	public Ch18PA2()
	{
		setTitle("Dice Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		createMenu();
		buildImagePanel();
		buildButtonPanel();
		add(die1Panel, BorderLayout.WEST);
		add(die2Panel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}
//set panels for the 2 dice and set them to a random number in the ImageIcon array
	private void buildImagePanel()
	{
		die1 = new JLabel();
		die1.setIcon(dice[(int)(Math.random() * 6)]);
		die1Panel = new JPanel();
		die1Panel.add(die1);
		
		die2 = new JLabel();
		die2.setIcon(dice[(int)(Math.random() * 6)]);
		die2Panel = new JPanel();
		die2Panel.add(die2);
	}
//set the roll button	
	private void buildButtonPanel()
	{
		rollButton = new JButton("Roll the Dice");
		rollButton.setMnemonic('R');
		rollButton.addActionListener(new ButtonListener());
		buttonPanel = new JPanel();
		buttonPanel.add(rollButton);
	}
//roll the dice!	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			die1.setIcon(dice[(int)(Math.random() * 6)]);
			die2.setIcon(dice[(int)(Math.random() * 6)]);
		}
	}
	
	public void createMenu()
	{
	//create menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
	//create file menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		menuBar.add(fileMenu);
	//create roll item & add to file menu	
		rollMenu = new JMenuItem("Click here to roll the dice.");
		rollMenu.setMnemonic('R');
		rollMenu.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					die1.setIcon(dice[(int)(Math.random() * 6)]);
					die2.setIcon(dice[(int)(Math.random() * 6)]);
				}
			});
		fileMenu.add(rollMenu);
	//create exit item & add to file menu
		exitMenu = new JMenuItem("Exit Program");
		exitMenu.setMnemonic('E');
		exitMenu.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			});
		fileMenu.add(exitMenu);
	}
//send it	
	public static void main(String[] args) 
	{
		new Ch18PA2();
	}

}