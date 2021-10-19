import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ItemEvent;
import java.text.NumberFormat;

public class Keno extends JFrame
{
	private int count = 0;
	private int correct = 0;
	private int numbersBet = 0;
	private int amountBet = 0;
	private double amountWon = 0;
	private int counter = 0;
	private int i =0;
	NumberFormat currency = NumberFormat.getCurrencyInstance();
	private TitledBorder radioBorder = BorderFactory.createTitledBorder("Choose Bet Amount ($) ");
	private TitledBorder spotsBorder = BorderFactory.createTitledBorder("Choose Numbers to Play");
	private TopPanel buttonPanel1;
	private BottomPanel buttonPanel2;
	private MiddlePanel middlePanel;
	private JButton[] buttons = new JButton[80];
	private JButton betMaxButton = new JButton("Bet Max Amount");
	private JButton eraseButton = new JButton("Clear Board");
	private JButton startButton = new JButton("Generate Numbers");
	private ButtonGroup dollarGroup = new ButtonGroup();
	private JRadioButton[] dollars = new JRadioButton[6];
	private ButtonGroup spotsGroup = new ButtonGroup();
	private JRadioButton[] spots = new JRadioButton[12];
	private Font font = new Font("Arial", Font.BOLD, 38);
	private Font font2 = new Font("Arial", Font.BOLD,18);
	private JLabel luckyLabel = new JLabel(new ImageIcon("images/Lucky.png"));
	private ImageIcon icon = new ImageIcon("images/checkmark.gif");
	private ArrayList<String> picks = new ArrayList<String>();
	private ArrayList<Integer> shuffleNumbers = new ArrayList<Integer>();
	private ArrayList<Integer> winningNumbers = new ArrayList<Integer>();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu help = new JMenu("Help");
	private JMenu payScale = new JMenu("Payoffs");
	private JMenuItem exit = new JMenuItem("Exit Program");
	private JMenuItem info = new JMenuItem("How to Play Keno");
	private JMenuItem payoffs[] = new JMenuItem[12];
	private Color color;
	private Timer timer;
	
	public Keno()
	{
		setLayout(new GridLayout(3,1));
		createMenu();
		createButtons();
		createRadioButtons();
		createPanels();
		populateArrayList();
		add(buttonPanel1);
		add(middlePanel);
		add(buttonPanel2);
	}

	public void createButtons()
	{
		for(int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton(String.valueOf(i+1));
			buttons[i].setHorizontalAlignment(JButton.CENTER);
			buttons[i].setFont(font);
			buttons[i].setBackground(Color.BLUE);
			buttons[i].setForeground(Color.YELLOW);
			buttons[i].addActionListener(new ButtonListener()); //this was added
		}
	}

	public void createRadioButtons()
	{
		dollars[0] = new JRadioButton("1");
		dollars[1] = new JRadioButton("2");
		dollars[2] = new JRadioButton("3");
		dollars[3] = new JRadioButton("5");
		dollars[4] = new JRadioButton("10");
		dollars[5] = new JRadioButton("20");
		for(int i = 0; i < dollars.length; i++)
		{
			dollars[i].setFont(font2);
			dollars[i].addItemListener(new RadioListener());//added
			dollarGroup.add(dollars[i]);
		}
		for(int i = 0; i < spots.length; i++)
		{
			spots[i] = new JRadioButton(String.valueOf(i + 1) + "");
			spots[i].addItemListener(new RadioListener());//added
			spotsGroup.add(spots[i]);
			spots[i].setFont(font2);
		}
	}

	public void createMenu()
	{
		setJMenuBar(menuBar);
		menuBar.add(file);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(betMaxButton);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(eraseButton);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(startButton);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(payScale);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(help);
		menuBar.add(Box.createHorizontalGlue());
		file.add(exit);
		help.add(info);
		for(int i = 0; i < payoffs.length; i++)
		{
			payoffs[i] = new JMenuItem(String.valueOf(i + 1) + "   Spots Selected");
			payScale.add(payoffs[i]);
			payoffs[i].setFont(font2);
		}
		for(int i = 0; i < payoffs.length; i++)
		payoffs[i].setHorizontalAlignment(SwingConstants.RIGHT);
		file.setFont(font2);
		payScale.setFont(font2);
		help.setFont(font2);
		exit.setFont(font2);
		info.setFont(font2);
		file.setMnemonic('F');
		help.setMnemonic('H');
		exit.setMnemonic('E');
		info.setMnemonic('K');
		payScale.setMnemonic('P');
		betMaxButton.setFont(font2);
		betMaxButton.addActionListener(new Listener());
		info.addActionListener(new Listener());
		exit.addActionListener(new Listener());
		for(int i = 0; i < payoffs.length; i++)
		{
			payoffs[i].addActionListener(new Listener());
		}
		eraseButton.addActionListener(new Listener());
		startButton.addActionListener(new Listener());
		eraseButton.setFont(font2);
		startButton.setFont(font2);
	}
	
	public void createPanels()
	{
		buttonPanel1 = new TopPanel();
		buttonPanel2 = new BottomPanel();
		middlePanel = new MiddlePanel();
	}

	public static void main(String[] args)
	{
		Keno frame = new Keno();
		frame.setTitle("Let's Play Keno");
		frame.pack();
		frame.setSize(900,750);
		frame.setVisible(true);
	}

	public void clear()
	{
		winningNumbers.clear();
		winningNumbers.trimToSize();
		count = 0;
		correct = 0;
		amountWon = 0;
		i = 0;
		for(int i = 0; i < buttons.length; i++)
		{
			buttons[i].setText(String.valueOf(i + 1));
			buttons[i].setBackground(Color.BLUE);
			buttons[i].setForeground(Color.YELLOW);
			buttons[i].setIcon(null);
		}
		dollarGroup.clearSelection();
		spotsGroup.clearSelection();
		numbersBet = 0;
		amountBet = 0;
	}

	public void generateNumbers()
	{
		winningNumbers.clear();
		winningNumbers.trimToSize();
		Collections.shuffle(shuffleNumbers);
		if(numbersBet <= 0)
		{
			JOptionPane.showMessageDialog(null, "Choose numbers to play");
			clear();
		}
		else if(count != numbersBet)
		{
			JOptionPane.showMessageDialog(null, "You must select " +numbersBet +" numbers");
			clear();
		}
		else
		{
			for(int i = 0; i < 20; i++)
			{
				winningNumbers.add(shuffleNumbers.get(i));
			}
			timer = new Timer(3000, new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if(i < buttons.length)
					{
						if(buttons[winningNumbers.get(i)].getIcon() != null)
						{
							correct++;
							buttons[winningNumbers.get(i)].setBackground(Color.GREEN);
						}
						else
						{
							buttons[winningNumbers.get(i)].setBackground(Color.RED);
						}
					}
					if(i < 19)
					{
						i++;
					}
					else
					{
						((Timer)e.getSource()).stop();
						//notify the user that the download is complete
						JOptionPane.showMessageDialog(null, "The numbers you have selected and the numbers generated, "
						+ "have been successfully downloaded");
						displayWinnings();
					}
				}
			});
			
			if(!timer.isRunning())
			{
				timer.start();
			}
			
			try
			{
				Formatter output = new Formatter(new FileOutputStream("Final.txt", true));
				Formatter output2 = new Formatter(new FileOutputStream("UserPicks.txt",true));
				
				for(int i = 0; i < 20; i++)
				{
					output.format("%d ", (winningNumbers.get(i) + 1));
				}
				
				for(int i = 0; i < picks.size(); i++)
				{
					output2.format("%s ", (picks.get(i)));
				}
				
				output.close();
				output2.close();
			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog(null, "Error creating file");
			}
		}
	}

	public void displayWinnings()
	{
		switch(numbersBet)
		{
			case 1:
			switch(correct)
			{
				case 1:
				amountWon = amountBet * 2.5;
				break;
				case 0:
				JOptionPane.showMessageDialog(null, "You matched " +correct +" numbers. Sorry but you must match the number to win");
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " +correct +" numbers. Sorry but you must match the number to win");
			}
			break;
			
			case 2:
			switch(correct)
			{
				case 1:
				amountWon = amountBet;
				break;
				case 2:
				amountWon = amountBet * 5;
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " +correct +" numbers. Sorry but you must match the number to win");
			}
			break;
			
			case 3:
			switch(correct)
			{
				case 2:
				amountWon = 2.50 * amountBet;
				break;
				case 3:
				amountWon = 25 * amountBet;
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers.  Sorry but you must match at least 2 numbers to win.");
				break;
			}
			break;
			
			case 4:
			switch(correct)
			{
				case 2:
				amountWon = 1 * amountBet;
				break;
				case 3:
				amountWon = 4 * amountBet;
				break;
				case 4:
				amountWon = 100 * amountBet;
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers.  Sorry but you must match at least two numbers to win.");
				break;
			}
			break;
			
			case 5:
			switch(correct)
			{
				case 3:
				amountWon = 2 * amountBet;
				break;
				case 4:
				amountWon = 20 * amountBet;
				break;
				case 5:
				amountWon = 450 * amountBet;
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers.  Sorry but you must match at least three numbers to win.");
				break;
			}
			break;
			
			case 6:
			switch(correct)
			{
				case 3:
				amountWon = 1 * amountBet;
				break;
				case 4:
				amountWon = 7 * amountBet;
				break;
				case 5:
				amountWon = 50 * amountBet;
				break;
				case 6:
				amountWon = 1600 * amountBet;
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers.  Sorry but you must match at least three numbers to win.");
			}
			break;
			
			case 7:
			switch(correct)
			{
				case 3:
				amountWon = 1 * amountBet;
				break;
				case 4:
				amountWon = 3 * amountBet;
				break;
				case 5:
				amountWon = 20 * amountBet;
				break;
				case 6:
				amountWon = 100 * amountBet;
				break;
				case 7:
				amountWon = 5000 * amountBet;
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers.  Sorry but you must match at least three numbers to win.");
				break;
			}
			break;
			
			case 8:
			switch(correct)
			{
				case 4:
				amountWon = 2 * amountBet;
				break;
				case 5:
				amountWon = 10 * amountBet;
				break;
				case 6:
				amountWon = 50 * amountBet;
				break;
				case 7:
				amountWon = 1000 * amountBet;
				break;
				case 8:
				amountWon = 15000 * amountBet;
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers.  Sorry but you must match at least 4 numbers to win.");
				break;
			}
			break;
			
			case 9:
			switch(correct)
			{
				case 4:
				amountWon = 1 * amountBet;
				break;
				case 5:
				amountWon = 5 * amountBet;
				break;
				case 6:
				amountWon = 25 * amountBet;
				break;
				case 7:
				amountWon = 200 * amountBet;
				break;
				case 8:
				amountWon = 4000 * amountBet;
				break;
				case 9:
				amountWon = 40000 * amountBet;
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers.  Sorry but you must match at least 4 numbers to win.");
				break;
			}
			break;
			
			case 10:
			switch(correct)
			{
				case 0:
				amountWon = 2 * amountBet;
				break;
				case 5:
				amountWon = 2 * amountBet;
				break;
				case 6:
				amountWon = 20 * amountBet;
				break;
				case 7:
				amountWon = 80 * amountBet;
				break;
				case 8:
				amountWon = 500 * amountBet;
				break;
				case 9:
				amountWon = 10000 * amountBet;
				break;
				case 10:
				amountWon = 100000 * amountBet;
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers.  Sorry but you match either zero numbers, or five to ten numbers to win.");
				break;
			}
			break;
			
			case 11:
			switch(correct)
			{
				case 0:
				amountWon = 2 * amountBet;
				break;
				case 5:
				amountWon = 1 * amountBet;
				break;
				case 6:
				amountWon = 10 * amountBet;
				break;
				case 7:
				amountWon = 50 * amountBet;
				break;
				case 8:
				amountWon = 250 * amountBet;
				break;
				case 9:
				amountWon = 1500 * amountBet;
				break;
				case 10:
				amountWon = 15000 * amountBet;
				break;
				case 11:
				amountWon = 500000 * amountBet;
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers.  Sorry but you match either zero numbers, or five to eleven numbers to win.");
				break;
			}
			break;
			
			case 12:
			switch(correct)
			{
				case 0:
				amountWon = 4 * amountBet;
				break;
				case 6:
				amountWon = 5 * amountBet;
				break;
				case 7:
				amountWon = 25 * amountBet;
				break;
				case 8:
				amountWon = 150 * amountBet;
				break;
				case 9:
				amountWon = 1000 * amountBet;
				break;
				case 10:
				amountWon = 2500 * amountBet;
				break;
				case 11:
				amountWon = 25000 * amountBet;
				break;
				case 12:
				amountWon = 1000000 * amountBet;
				break;
				default:
				JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers.  Sorry but you match either zero numbers, or six to twelve numbers to win.");
				break;
			}
			break;
		}
		
		if(amountWon > 0)
		JOptionPane.showMessageDialog(null, "Congratulations you matched " + correct + " number(s) and won " + currency.format(amountWon));
	}

	public void populateArrayList()
	{
		for(int i = 0; i <= 79; i++)
		{
			shuffleNumbers.add(i);
		}
	}

	class TopPanel extends JPanel
	{
		public TopPanel()
		{
			setLayout(new GridLayout(4,10));
			for(int i = 0; i < 40; i++)
			{
				add(buttons[i]);
			}
		}
	}

	class BottomPanel extends JPanel
	{
		public BottomPanel()
		{
			setLayout(new GridLayout(4,10));
			for(int i = 40; i < buttons.length; i++)
			{
				add(buttons[i]);
			}
		}
	}

	class MiddlePanel extends JPanel
	{
		JPanel dollarPanel = new JPanel(new GridLayout(6,1));
		JPanel spotsPanel = new JPanel(new GridLayout(6,2));
	
		public MiddlePanel()
		{
			setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
			dollarPanel.setBorder(radioBorder);
			dollarPanel.setPreferredSize(new Dimension(175,200) );
			for(int i = 0; i < dollars.length; i++)
			{
				dollarPanel.add(dollars[i]);
			}
			spotsPanel.setBorder(spotsBorder);
			spotsPanel.setPreferredSize(new Dimension(175,200));
			spotsPanel.add(spots[0]);// 1
			spotsPanel.add(spots[6]);// 7
			spotsPanel.add(spots[1]);// 2
			spotsPanel.add(spots[7]);// 8
			spotsPanel.add(spots[2]);// 3
			spotsPanel.add(spots[8]);//9
			spotsPanel.add(spots[3]);//4
			spotsPanel.add(spots[9]); //10
			spotsPanel.add(spots[4]); //5
			spotsPanel.add(spots[10]); //11
			spotsPanel.add(spots[5]);//6
			spotsPanel.add(spots[11]);//12
			setBackground(Color.white);
			add(spotsPanel);
			add(luckyLabel);
			add(dollarPanel);
		}
	}
	
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for(int i = 0; i < spots.length; i++)
			{
				if(spots[i].isSelected())
				numbersBet = Integer.parseInt(spots[i].getText());
			}
			if(count < numbersBet)
			{
				count++;
				if( e.getSource() instanceof JButton)
				{
					((JButton)e.getSource()).setIcon(icon);
					String numberLabel = ((JButton)e.getSource()).getText();
					picks.add(numberLabel);
					((JButton)e.getSource()).setText("");
				}
			}
			else if(count == 0)
			{
				JOptionPane.showMessageDialog(null, "You have to choose to bet at least one number");
				numbersBet = 0;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You have already chosen your " + numbersBet + " numbers.\nYou cannot choose "
				+ "any additional numbers");
			}
		}
	}

	class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == betMaxButton)
			{
				dollars[5].setSelected(true);
			}
			if(e.getSource() == eraseButton)
			{
				clear();
			}
			if(e.getSource() == info)
			{
				JOptionPane.showMessageDialog(null, "Click the numbers you wish to bet (1-12).\nClick Generate Numbers"
				+ "\n20 Random numbers will be chosen and highlighted in Red.\nA message will display as to whether you have won."
				+ "\nSee Help > Payoffs for a list of payoffs");
			}
			if(e.getSource() == exit)
			{
				System.exit(0);
			}
			if(e.getSource() == payoffs[0])
			{
				JOptionPane.showMessageDialog(null, "Match\n1:  $2.50");
			}
			if (e.getSource() == payoffs[1])
			{
				JOptionPane.showMessageDialog(null, "Match\n1:  $1.00\n2:  $5");
			}
			if(e.getSource() == payoffs[2])
			{
				JOptionPane.showMessageDialog(null, "Match\n2:  $2.50\n3:  $25");
			}
			if(e.getSource() == payoffs[3])
			{
				JOptionPane.showMessageDialog(null, "Match\n2:  $1.00\n3:  $4\n4:   $100");
			}
			if(e.getSource() == payoffs[4])
			{
				JOptionPane.showMessageDialog(null, "Match\n3:  $2.00\n4:  $20\n5:   $450");
			}
			if(e.getSource() == payoffs[5])
			{
				JOptionPane.showMessageDialog(null, "Match\n3:  $1.00\n4:  $7\n5:   $50\n6:  $1,600");
			}
			if(e.getSource() == payoffs[6])
			{
				JOptionPane.showMessageDialog(null, "Match\n3:  $1.00\n4:  $3\n5:   $20\n6:  $100\n7:  $5,000");
			}
			if(e.getSource() == payoffs[7])
			{
				JOptionPane.showMessageDialog(null, "Match\n4:  $2.00\n5:  $10\n6:   $50\n7:  $1,000\n8:  $15,000");
			}
			if(e.getSource() == payoffs[8])
			{
				JOptionPane.showMessageDialog(null, "Match\n4:  $1.00\n5:  $5\n6:   $25\n7:  $200\n8:  $4,000\n9:  $40,000");
			}
			if(e.getSource() == payoffs[9])
			{
				JOptionPane.showMessageDialog(null, "Match\n0:  $2.00\n5:  $2\n6:   $20\n7:  $80\n8:  $500\n9:  $10,000\n10:  $100,000");
			}
			if(e.getSource() == payoffs[10])
			{
				JOptionPane.showMessageDialog(null, "Match\n0:  $2.00\n5:  $1\n6:   $10\n7:  $50\n8:  $250\n9:  $1,500\n10:  $15,000"
				+ "\n11:  $500,000");
			}
			if(e.getSource() == payoffs[11])
			{
				JOptionPane.showMessageDialog(null, "Match\n0:  $4.00\n6:  $5\n7:   $25\n8:  $150\n9:  $1,000\n10:  $2,500"
				+ "\n11:  $25,000\n12:  $1,000,000");
			}
			if(e.getSource() == startButton)
			{
				generateNumbers();
			}
		}
	}

	class RadioListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			for(int i = 0; i < spots.length; i++)
			{
				if(e.getSource() == spots[i])
				{
					numbersBet = Integer.parseInt(spots[i].getText());
					break;
				}
			}
			for(int i = 0; i < dollars.length; i++)
			{
				if(e.getSource() == dollars[i])
				{
					if(dollars[i].isSelected())
					{
						amountBet = Integer.parseInt(dollars[i].getText());
					}
				}
			}
		}
	}
}

