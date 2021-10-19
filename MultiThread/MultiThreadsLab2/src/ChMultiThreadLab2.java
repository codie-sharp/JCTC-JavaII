import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;

public class ChMultiThreadLab2 extends JFrame {
//Declare two number panels
	private NumberControl control1, control2;
	
	public static void main(String[] args) {
		//Create a frame
		ChMultiThreadLab2 frame = new ChMultiThreadLab2();
		
		//Display the frame
		frame.setSize(600,400);
		frame.setTitle("MultiThreads Lab 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); //Center the frame
		frame.setVisible(true);
	}
		
		/**Initialize the frame */
		public ChMultiThreadLab2()
		{
			// Panel p1 for holding two numbers
			JPanel p1 = new JPanel();
			p1.setLayout(new GridLayout(1, 2));
			// Create a number to display on the left
			p1.add(control1 = new NumberControl());
			// Create a number to display on the right
			p1.add(control2 = new NumberControl());
			// Add panel p1 to the frame
			add(p1, BorderLayout.CENTER);
		}
		class NumberControl extends JPanel implements ActionListener
		{
			private Numbers numbers = new Numbers();
			private JButton jbtSuspend = new JButton("Suspend");
			private JButton jbtResume = new JButton("Resume");
			public NumberControl()
			{
				// Group buttons in a panel
				JPanel panel = new JPanel();
				panel.add(jbtSuspend);
				panel.add(jbtResume);
				// Add number and buttons to the panel
				setLayout(new BorderLayout());
				add(numbers, BorderLayout.CENTER);
				add(panel, BorderLayout.SOUTH);
				// Register listeners
				jbtSuspend.addActionListener(this);
				jbtResume.addActionListener(this);
			}
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == jbtSuspend)
				{
				numbers.suspend();
				}
				else if (e.getSource() == jbtResume)
				{
				numbers.resume();
				}
			}
			class Numbers extends RandomNumbers implements Runnable
			{
				private boolean suspended;
				public Numbers()
				{
					new Thread(this).start();
				}
				public void run()
				{
					while (true)
					{
						draw();
						repaint();
						try
						{
							Thread.sleep(1000);
							waitForNotificationToResume();
						}
						catch (InterruptedException ex)
						{
						}
					}
				}
				public synchronized void suspend()
				{
					suspended = true;
				}
				public synchronized void resume()
				{
					if (suspended)
					{
						suspended = false;
						notifyAll();
					}
				}
				private synchronized void waitForNotificationToResume() throws InterruptedException
				{
					while (suspended)
					{
						wait();
					}
				}//closes NumberControl class
			}//closes Numbers class
		}	
	
}
