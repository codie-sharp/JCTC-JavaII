import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

/**
* The MinutesPanel class for use with the
* Long Distance Calls programming challenge.
*/

public class MinutesPanel extends JPanel 
{
	private JTextField minutes; // To get minutes
	
	public MinutesPanel()
	{
		// Create a label prompting the user and a text field.
		JLabel minutesMsg = new JLabel("Minutes:");
		minutes = new JTextField(10);
		// Create a GridLayout manager.
		setLayout(new GridLayout(1, 2));
		// Create a border.
		setBorder(BorderFactory.createTitledBorder("Time of Call"));
		// Add the labels and text fields to this panel.
		add(minutesMsg);
		add(minutes);
	}
	
	/**
	* The getCharges method uses the specified rate to calculate
	* the charges for the number of minutes entered.
	* @param rate The per-minute rate.
	* @return The charges for the number of minutes used.
	*/
	public double getCharges(double rate)
	{
		double charges = Double.parseDouble(minutes.getText()) * rate;
		return charges;
	}
}
