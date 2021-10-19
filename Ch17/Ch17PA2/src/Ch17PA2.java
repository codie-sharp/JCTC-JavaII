import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class Ch17PA2 extends JFrame 
{	
	private GasPanel gasPanel;
	private JButton calcButton;
	private JPanel buttonPanel;
	
	public Ch17PA2()
	{
		setTitle("Miles Per Gallon Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gasPanel = new GasPanel();
		buildButtonPanel();
		add(gasPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}
	
	private void buildButtonPanel()
	{
		calcButton = new JButton("Calculate MPG");
		calcButton.addActionListener(new CalcButtonListener());
		buttonPanel = new JPanel();
		buttonPanel.add(calcButton);
	}
	
	private class CalcButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			double mpg = gasPanel.getMiles() / gasPanel.getGallons();
			JOptionPane.showMessageDialog(null, String.format("You got %,.2f miles per gallon.", mpg));
		}
	}
	
	public static void main(String[] args) 
	{
		Ch17PA2 mpgc = new Ch17PA2();
	}

}
