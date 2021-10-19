import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class RandomNumbers extends JPanel {
	int maximum = 500;
	int minimum = 0;
	Random rn = new Random();
	int range = maximum - minimum + 1;
	int randomNum = rn.nextInt(range) + minimum;
	final int XPOSITION = 80;
	final int SIZE = 50;
	final int YPOSITION = 200;
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 72));
		
		//draw the number
		g.drawString(String.valueOf(randomNum), XPOSITION, YPOSITION);
	}
	public void draw()
	{
		randomNum = rn.nextInt(range) + minimum;
	}
}
