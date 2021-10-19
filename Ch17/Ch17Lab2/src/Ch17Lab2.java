import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
* This program demonstrates a solution to the
* Long Distance Calls programming challenge.
*/

public class Ch17Lab2 extends JFrame 
{
	private RatePanel ratePanel; // A panel for rates
	private MinutesPanel minutesPanel; // A panel for minutes
	private JPanel buttonPanel; // A panel for the buttons
	private JButton calcButton; // Calculates everything
	private JButton exitButton; // Exits the application
	
	public Ch17Lab2()
	{
		// Display a title.
		setTitle("Long Distance Charges");
		// Specify what happens when the close button is clicked.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create a RatePanel object.
		ratePanel = new RatePanel();
		// Create a MinutesPanel object.
		minutesPanel = new MinutesPanel();
		// Build the panel that contains the buttons.
		buildButtonPanel();
		// Add the panels to the content pane.
		add(ratePanel, BorderLayout.NORTH);
		add(minutesPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		// Pack and display the window.
		pack();
		setVisible(true);
	}
	
	/**
	* The buildButtonPanel method creates a panel containing
	* buttons.
	*/
	private void buildButtonPanel()
	{
		// Create a button to calculate the charges.
		calcButton = new JButton("Calculate Charges");
		// Add an action listener to the button.
		calcButton.addActionListener(new CalcButtonListener());
		// Create a button to exit the application.
		exitButton = new JButton("Exit");
		// Add an action listener to the button.
		exitButton.addActionListener(new ExitButtonListener());
		// Put the buttons in their own panel.
		buttonPanel = new JPanel();
		buttonPanel.add(calcButton);
		buttonPanel.add(exitButton);
	}

	/**
	* CalcButtonListener is an action listener class for the
	* calcButton component.
	*/
	private class CalcButtonListener implements ActionListener
	{
		/**
		* actionPerformed method
		* @param e An ActionEvent object.
		*/
		public void actionPerformed(ActionEvent e)
		{
			double rate; // Applicable rate
			double totalCharges; // Total charges
			// Get the applicable rate.
			rate = ratePanel.getRate();
			// Get the total charges
			totalCharges = minutesPanel.getCharges(rate);
			// Display the message.
			JOptionPane.showMessageDialog(null, String.format(
			"Total Charges: $%,.2f", totalCharges));
		}
	} // End of inner class
	
	/**
	* ExitButtonListener is an action listener class for the
	* exitButton component.
	*/
	private class ExitButtonListener implements ActionListener
	{
		/**
		* actionPerformed method
		* @param e An ActionEvent object.
		*/
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	} // End of inner class
	
	public static void main(String[] args) 
	{
		Ch17Lab2 ld = new Ch17Lab2();
	}
}
