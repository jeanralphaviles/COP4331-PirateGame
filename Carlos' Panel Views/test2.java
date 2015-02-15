import javax.swing.*;
import java.awt.*;

public class test2 {

	

	public test2()
	{
	gui();
	}

	public void gui(){

	JPanel top = new JPanel(new GridLayout(1,1));
	top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
	
	JPanel bottom = new JPanel(new GridLayout(1,1));
	bottom.setPreferredSize(new Dimension(400,140));
	
	JPanel top1 = new JPanel(new GridLayout(1,1));
	top1.setPreferredSize(new Dimension(400,260));
	
	
	top1.setBackground(Color.YELLOW);
	bottom.setBackground(Color.BLUE);

	JPanel inside = new JPanel();
	inside.setBackground(Color.RED);
	JPanel inside2 = new JPanel();
	inside2.setBackground(Color.GREEN);

	JPanel inside3 = new JPanel();
	inside3.setBackground(Color.ORANGE);

	JPanel inside4 = new JPanel();
	inside4.setBackground(Color.GRAY);

	top1.add(inside);
	top1.add(inside2);

	bottom.add(inside3);
	bottom.add(inside4);


	top.add(top1);
	top.add(bottom);
	JFrame frame = new JFrame();
	frame.add(top);
	frame.setSize(400,400);
	frame.setVisible(true);

	}
	public static void main(String[] args) {
	new test2();
	}
}