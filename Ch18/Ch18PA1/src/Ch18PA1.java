import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Ch18PA1 extends JFrame 
{
	private JSlider slider;
	private JTextField purchase, tax;
	private JLabel purchaseMsg, sliderMsg, taxMsg;
	private JPanel nPanel, cPanel, sPanel;
	private double purchaseAmt = 200000;
	private int sliderValue = 6;
	DecimalFormat df = new DecimalFormat("#,###.00");
	private String salesTax = df.format(((double)sliderValue / 100) * purchaseAmt);

	
	public Ch18PA1()
	{	
		setTitle("Tax Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Fill north panel with purchase info
		purchaseMsg = new JLabel("Enter the amount of the purchase: ");
		purchase = new JTextField(Double.toString(purchaseAmt), 10);		
//Update the Sales Tax field when the enter key is pressed
		purchase.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					purchaseAmt = Double.parseDouble(purchase.getText());
					salesTax = df.format(((double)sliderValue / 100) * purchaseAmt);
					tax.setText(salesTax);
				}
			});
		nPanel = new JPanel();
		nPanel.add(purchaseMsg);
		nPanel.add(purchase);
//Fill center panel with tax slider
		sliderMsg = new JLabel("Sales Tax Slider: ");
		slider = new JSlider(JSlider.HORIZONTAL,0,10, sliderValue);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.addChangeListener(new SliderListener());
		cPanel = new JPanel();
		cPanel.add(sliderMsg);
		cPanel.add(slider);
//Fill south panel with non editable field that calculates sales tax
		JLabel taxMsg = new JLabel("Sales Tax: ");
		tax = new JTextField(salesTax, 10);
		tax.setEditable(false);
		sPanel = new JPanel();
		sPanel.add(taxMsg);
		sPanel.add(tax);
//Add to frame
		setLayout(new GridLayout(3,1));
		add(nPanel);
		add(cPanel);
		add(sPanel);
		pack();
		setVisible(true);
	}
	
	private class SliderListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			purchaseAmt = Double.parseDouble(purchase.getText());
			sliderValue = slider.getValue();
			salesTax = df.format(((double)sliderValue / 100) * purchaseAmt);
			tax.setText(salesTax);
		}
	}

	public static void main(String[] args) 
	{
		new Ch18PA1();
	}
}
