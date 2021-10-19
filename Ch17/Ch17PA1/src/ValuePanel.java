import javax.swing.*;

public class ValuePanel extends JPanel {
	private JTextField value;
	
	public ValuePanel()
	{
		JLabel valueMsg = new JLabel("Enter the actual property value:");
		value = new JTextField(10);
		add(valueMsg);
		add(value);
	}
	
	public double getAssessmentValue()
	{
		double assessmentValue = Double.parseDouble(value.getText()) * .6;
		return assessmentValue;
	}
}
