import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CenterPanel extends JPanel 
{
	private int[] numbers = new int[5];
	private int powerball;
	private JPanel labelPanel, buttonPanel;
	private JButton quickPickButton;
	private JButton powerBallButton;
	private ImageIcon ball;
	private JLabel[] ballLabels;
	private JLabel[] numberLabels;
	Border emptyBdr;

	public CenterPanel()
	{
		createBorder();
		setBorder(emptyBdr);
		setLayout(new BorderLayout(0,20));
		createIcon();
		createLabels();
		createButtons();
		createPanels();
		add(labelPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void createBorder()
	{
		emptyBdr = BorderFactory.createEmptyBorder(20,20,20,20);
	}
	
	public void createLabels()
	{
		ballLabels = new JLabel[6];
		for(int i = 0; i < ballLabels.length; i++)
		{
			ballLabels[i] = new JLabel(ball);
			ballLabels[i].setHorizontalTextPosition(JLabel.CENTER);
			ballLabels[i].setVerticalTextPosition(JLabel.CENTER);
		}
		numberLabels = new JLabel[6];
		for(int i = 0; i < numberLabels.length - 1; i++)
		{
			numberLabels[i] = new JLabel("Number " + (i + 1));
		}
		numberLabels[5] = new JLabel("Powerball Number");
	}

	public void createIcon()
	{
		ball= new ImageIcon("ball4.gif");
	}
	
	public void createButtons()
	{
		quickPickButton = new JButton("Quick Pick First 5 Numbers");
		quickPickButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				changeNumberValues();
			}
		});
		
		powerBallButton = new JButton("Quick Pick Powerball Number");
		powerBallButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				changePowerballValue();
			}
		});
	}
	
	public void createPanels()
	{
		labelPanel = new JPanel(new GridLayout(3,4, 0, 10));
		labelPanel.add(ballLabels[0]);
		labelPanel.add(numberLabels[0]);
		labelPanel.add(ballLabels[3]);
		labelPanel.add(numberLabels[3]);
		labelPanel.add(ballLabels[1]);
		labelPanel.add(numberLabels[1]);
		labelPanel.add(ballLabels[4]);
		labelPanel.add(numberLabels[4]);
		labelPanel.add(ballLabels[2]);
		labelPanel.add(numberLabels[2]);
		labelPanel.add(ballLabels[5]);
		labelPanel.add(numberLabels[5]);
		buttonPanel = new JPanel();
		buttonPanel.add(quickPickButton);
		buttonPanel.add(powerBallButton);
	}

	public void changeNumberValues()
	{
		//makes certain first 5 numbers are unique
		for (int i = 0; i < numbers.length; i++)
		{
			//note, this generates numbers from [1 to 69]
			numbers[i] = (int)(Math.random() * 69) + 1;
			for (int j = 0; j < i; j++)
			{
				if (numbers[i] == numbers[j])
				{
					i--; //if a[i] is a duplicate of a[j], then run the outer loop on i again
					break; //this will break out of the if statement and inner for loop
				}
			}
		}
		for(int i = 0; i < numbers.length; i++)
		{
			ballLabels[i].setText("" + numbers[i]);
		}
	}

	public void changePowerballValue()
	{
		powerball = getPowerball();
		ballLabels[5].setText(""+powerball);
	}

	public int getPowerball()
	{
		powerball = (int)(Math.random() * 26) + 1;
		return powerball;
	}
	
	public JLabel[] getNumberLabels()
	{
		return numberLabels;
	}
	
	public void setBallLabels(String k)
	{
		for(int i = 0; i < ballLabels.length; i++)
		{
			ballLabels[i].setText(k);
		}
	}
}
