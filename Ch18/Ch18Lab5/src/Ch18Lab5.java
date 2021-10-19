import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JColorChooser;

public class Ch18Lab5 extends JFrame 
{
	boolean foundKey;
	JButton colorButtons[];
	JPanel centerPanel;
	JTextField colorField;
	JLabel colorLabel;
	JMenuBar menuBar;
	JMenu fileMenu, editMenu, aboutMenu,changeColors;
	JMenuItem displayMenu,clearMenu,closeMenu,aboutColorMenu, changeTextFieldColor;
	
	public static void main(String[] args) 
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		Ch18Lab5 frameInfo = new Ch18Lab5 ();
		frameInfo.setSize(450,250);
		frameInfo.setVisible(true);
		frameInfo.setTitle("Chapter 18 Lab 5");
	}

	public Ch18Lab5()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3,2));
		colorLabel = new JLabel("Welcome to Your Favorite Colors!");
		colorField = new JTextField();
		add(colorField,BorderLayout.NORTH);
		add(colorLabel,BorderLayout.SOUTH);
		add(centerPanel,BorderLayout.CENTER);
		createButtons();
		createMenu();
	}
	
	public void createButtons()
	{
		colorButtons = new JButton[6];
		colorButtons[0]=new JButton("blue");
		colorButtons[0].setBackground(Color.blue);
		colorButtons[1]=new JButton("black");
		colorButtons[1].setBackground(Color.black);
		colorButtons[2]=new JButton("darkGray");
		colorButtons[2].setBackground(Color.darkGray);
		colorButtons[3]=new JButton("gray");
		colorButtons[3].setBackground(Color.gray);
		colorButtons[4]=new JButton("red");
		colorButtons[4].setBackground(Color.red);
		colorButtons[5]=new JButton("magenta");
		colorButtons[5].setBackground(Color.magenta);
		for(int i=0;i<colorButtons.length;i++)
		{
			colorButtons[i].setForeground(Color.white);
		}
		for(int i=0;i<colorButtons.length;i++)
		{
			centerPanel.add(colorButtons[i]);
			colorButtons[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					foundKey = false;
					for(int i=0;i<colorButtons.length & !foundKey;i++)
					{
						if(e.getSource() == colorButtons[i])
						{
							foundKey = true;
							colorField.setBackground(colorButtons[i].getBackground());
							colorField.setText(colorButtons[i].getText()+" is such a lovely color");
							colorField.setForeground(colorButtons[i].getForeground());
						}
					}//closes for loop
				}//closes actionPerformed
			});
		}
	}
	
	public void createMenu()
	{
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		aboutMenu = new JMenu("About");
		changeColors = new JMenu("Change Colors");
		displayMenu = new JMenuItem("Display");
		clearMenu = new JMenuItem("Clear");
		closeMenu = new JMenuItem("Exit");
		aboutColorMenu = new JMenuItem("About Color Buttons");
		changeTextFieldColor = new JMenuItem("Change Field Color");
		
		fileMenu.setMnemonic('F');
		editMenu.setMnemonic('E');
		aboutMenu.setMnemonic('A');
		changeColors.setMnemonic('H');
		displayMenu.setMnemonic('D');
		clearMenu.setMnemonic('C');
		closeMenu.setMnemonic('X');
		aboutColorMenu.setMnemonic('B');
		
		setJMenuBar(menuBar);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(fileMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(editMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(aboutMenu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(changeColors);
		menuBar.add(Box.createHorizontalGlue());
		
		fileMenu.add(displayMenu);
		fileMenu.add(closeMenu);
		editMenu.add(clearMenu);
		aboutMenu.add(aboutColorMenu);
		changeColors.add(changeTextFieldColor);
		
		displayMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				colorField.setText("Press the key for your favorite color");
				colorField.setBackground(Color.white);
				colorField.setForeground(Color.black);
			}
		});
		
		clearMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				colorField.setText("");
				colorField.setBackground(Color.white);
			}
		});
		
		closeMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		aboutColorMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String message = "Drop Down Menus and Layout Managers";
				JOptionPane.showMessageDialog(null,message,"About Program",
				JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		changeTextFieldColor.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Color newColor = JColorChooser.showDialog(colorField,"Choose Background Color",
				colorField.getBackground());
				if(newColor != null)
				{
					colorField.setBackground(newColor);
				}
			}
		});
	}
}
