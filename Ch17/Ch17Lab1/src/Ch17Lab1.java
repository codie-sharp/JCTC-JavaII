import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;

public class Ch17Lab1 extends JFrame implements ActionListener {
	JButton buttons[];
	JPanel topPanel,centerPanel,bottomPanel, hiddenPanel;
	JLabel topLabel, bottomLabel,hiddenLabel;
	JTextField topField;
	JRadioButton[] RBs;
	ButtonGroup RBGroup;
	Border emptyBdr = BorderFactory.createEmptyBorder(20,20,20,20);
	Border lineBorder = LineBorder.createBlackLineBorder();
	Border compoundBorder = new CompoundBorder(lineBorder, emptyBdr);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == RBs[0])
		 {
			 topPanel.remove(hiddenLabel);
			 topPanel.setBorder(emptyBdr);
			 topPanel.revalidate();
			 topPanel.repaint();
			 topPanel.add(topLabel);
			 topPanel.add(topField);
		 }
		 if(e.getSource() == RBs[1])
		 {
			 topPanel.remove(topLabel);
			 topPanel.remove(topField);
			 topPanel.add(hiddenLabel);
			 topPanel.setBorder(compoundBorder);
			 topPanel.revalidate();
			 topPanel.repaint();
		 }
		 String arg = e.getActionCommand();
		 if(arg == "Clear")
		 {
			 topField.setText("");
			 topField.setBackground(Color.white);
		 }
		 if(arg == "Exit")
		 {
			 System.exit(0);
		 }
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		Ch17Lab1 frameInfo = new Ch17Lab1();
		frameInfo.setSize(350, 250);
		frameInfo.setVisible(true);
		frameInfo.setTitle("Chapter 17 Lab 1");
	}
	
	public Ch17Lab1()
	{
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 createLabels();
		 createTextField();
		 createButtons();
		 createRadioButtons();
		 createPanels();
		 addPanelComponents();
	}
	
	public void createLabels()
	{
		 //construct the labels
		 hiddenLabel = new JLabel("Now something different displays",
		 SwingConstants.CENTER);
		 topLabel = new JLabel("Testing Label ",SwingConstants.RIGHT);
	}
	
	public void createTextField()
	{
		 //construct the text field
		 topField = new JTextField();
	}
	
	public void createButtons()
	{
		 //construct the buttons
		 buttons = new JButton[2];
		 buttons[0]=new JButton("Clear");
		 buttons[1]=new JButton("Exit");
	}
	
	public void createRadioButtons()
	{
		 //construct the radio buttons and Button Group
		 RBGroup = new ButtonGroup();
		 RBs = new JRadioButton[2];
		 RBs[0] = new JRadioButton("Show Labels/Fields", true);
		 RBs[1] = new JRadioButton("Hide Labels/Fields", false);
		 for(int i=0;i<RBs.length;i++)
		 {
			 RBGroup.add(RBs[i]);
		 }
	}

	public void createPanels()
	{
		 //construct the different panels
		 topPanel = new JPanel();
		 topPanel.setBorder(emptyBdr);
		 topPanel.setLayout(new GridLayout(1,2));
		 topPanel.add(topLabel);
		 topPanel.add(topField);
		 centerPanel = new JPanel();
		 centerPanel.setLayout(new GridLayout(1,2));
		 for(int i = 0; i < RBs.length; i++)
			 centerPanel.add(RBs[i]);
		 bottomPanel = new JPanel();
		 for(int i = 0; i < buttons.length; i++)
			 bottomPanel.add(buttons[i]);
		 hiddenPanel = new JPanel(new GridLayout(1,2));
	}
	
	public void addPanelComponents()
	{
		 for(int i=0;i<RBs.length;i++)
		 {
			 centerPanel.add(RBs[i]);
			 RBs[i].addActionListener(this);
		 }
		 //add the buttons to the bottom panel
		 for(int i=0;i<buttons.length;i++)
		 {
			 bottomPanel.add(buttons[i]);
			 buttons[i].addActionListener(this);
		 }
		 add(topPanel, BorderLayout.NORTH);
		 add(centerPanel, BorderLayout.CENTER);
		 add(bottomPanel, BorderLayout.SOUTH);
	}
}
