import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class Ch17PA1 extends JFrame 
{
	private ValuePanel valuePanel;
	private JButton calcButton;
	private JPanel buttonPanel;
	
	public Ch17PA1()
	{
		setTitle("Poperty Taxes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		valuePanel = new ValuePanel();
		buildButtonPanel();
		add(valuePanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}
	
	private void buildButtonPanel()
	{
		calcButton = new JButton("Calculate Property Tax");
		calcButton.addActionListener(new CalcButtonListener());
		buttonPanel = new JPanel();
		buttonPanel.add(calcButton);
	}
	
	private class CalcButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			double propertyTax = valuePanel.getAssessmentValue() * .0064;
			JOptionPane.showMessageDialog(null,String.format("Assessment Value: $%,.2f "
			+ "\nProperty Tax: $%,.2f", valuePanel.getAssessmentValue(), propertyTax));
		}
	}
	
	public static void main(String[] args) 
	{
		Ch17PA1 pt = new Ch17PA1();
	}

}
