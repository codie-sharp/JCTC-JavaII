import java.awt.GridLayout;
import javax.swing.*;

public class GasPanel extends JPanel {
	private JTextField totalGallons, numberMiles;
	
	public GasPanel()
	{
		setLayout(new GridLayout(2,2));
		JLabel gallonsMsg = new JLabel("Total number of gallons: ");
		totalGallons = new JTextField(10);
		add(gallonsMsg);
		add(totalGallons);
		JLabel milesMsg = new JLabel("Number of miles driven: ");
		numberMiles = new JTextField(10);
		add(milesMsg);
		add(numberMiles);
	}
	
	public double getGallons()
	{
		double gallons = Double.parseDouble(totalGallons.getText());
		return gallons;
	}
	
	public double getMiles()
	{
		double miles = Double.parseDouble(numberMiles.getText());
		return miles;
	}
}
