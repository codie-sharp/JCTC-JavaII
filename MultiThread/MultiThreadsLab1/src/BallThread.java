import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Color;

public class BallThread extends JFrame {
	MoveItBall b = new MoveItBall();
	BallThread()
	{
		add(b);
		setTitle("Bouncing Ball");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550,400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new BallThread();
	}
	
	class MoveItBall extends JPanel
	{
		final int XPOSITION = 225;
		final int SIZE = 50;
		int yposition = 0; 
		int yincrements = 2, yPositionTop = 0, yPositionBottom = 300;
		MoveBallThread b = new MoveBallThread();
		MoveItBall()
		{
			Thread thread = new Thread(b);
			thread.start();
		}//end contructor
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			setBackground(Color.CYAN);
			
			g2d.fillOval(XPOSITION, yposition, SIZE, SIZE);
		}
		public class MoveBallThread extends Thread
		{
			public void move()
			{
				if(yposition > yPositionBottom)
				{
					yincrements = -10;
				}
				else if (yposition <= yPositionTop)
				{
					yincrements = 10;
				}
				yposition += yincrements;
			}
			public void run()
			{
				while(true)
				{
					move();
					try
					{
						Thread.sleep(20);
					}
					catch(Exception e)
					{
					}
					repaint();
				}
			}
		}
	}
}
