import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class TopPanel extends JPanel 
{
	private Border emptyBdr;
	private Border border;
	private Border compoundBorder;
	private JLabel output;
	private Font font;
	
	public TopPanel()
	{
		createBorders();
		createLabel();
		setBorder(compoundBorder);
		add(output);
	}
	
	public void createBorders()
	{
		emptyBdr = BorderFactory.createEmptyBorder(20,20,20,20);
		border = LineBorder.createBlackLineBorder();
		compoundBorder = new CompoundBorder(border, emptyBdr);
	}
	
	public void createLabel()
	{
		output = new JLabel("Pick the Powerball Numbers", SwingConstants.CENTER);
		font = new Font("TimesRoman",Font.BOLD,24);
		output.setFont(font);
	}
}
