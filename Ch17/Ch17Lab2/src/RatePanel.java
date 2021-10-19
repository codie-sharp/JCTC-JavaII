import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;

/**
* The RatePanel class for use with the
* Long Distance Calls programming challenge.
*/

public class RatePanel extends JPanel 
{	
	// Named constants for rates
	private final double DAYTIME_RATE = 0.07;
	private final double EVENING_RATE = 0.12;
	private final double OFF_PEAK_RATE = 0.05;
	private JRadioButton daytime; // Radio button for daytime rate
	private JRadioButton evening; // Radio button for evening rate
	private JRadioButton offPeak; // Radio button for off peak rate
	private ButtonGroup bg; // Radio button group
	
	public RatePanel()
	{
		// Create the radio buttons.
		daytime = new JRadioButton(String.format("Daytime ($%,.2f) per minute",
		DAYTIME_RATE));
		evening = new JRadioButton(String.format("Evening ($%,.2f) per minute",
		EVENING_RATE));
		offPeak = new JRadioButton(String.format("Off-Peak ($%,.2f) per minute",
		OFF_PEAK_RATE));
		// Group the radio buttons.
		bg = new ButtonGroup();
		bg.add(daytime);
		bg.add(evening);
		bg.add(offPeak);
		// Create a GridLayout manager.
		setLayout(new GridLayout(3, 1));
		// Create a border.
		setBorder(BorderFactory.createTitledBorder("Select a Rate Category"));
		// Add the radio buttons to this panel.
		add(daytime);
		add(evening);
		add(offPeak);
	}
	
	/**
	* The getRate method returns the rate for the selected
	* rate category.
	* @return One of the constants DAYTIME_RATE, EVENING_RATE, or
	* OFF_PEAK_RATE.
	*/
	public double getRate()
	{
		double rate = 0.0;
		if (daytime.isSelected())
		rate = DAYTIME_RATE;
		else if (evening.isSelected())
		rate = EVENING_RATE;
		else if (offPeak.isSelected())
		rate = OFF_PEAK_RATE;
		return rate;
	}
}


