import javax.swing.*;
import java.awt.*;

public class Test {


	public Test()
	{
	gui();
	}

	public void gui(){

	JPanel top = new JPanel(new GridLayout(1,1));
	//top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
	
	JPanel right = new JPanel(new GridLayout(1,1));
	right.setLayout(new BoxLayout(right,BoxLayout.Y_AXIS));
	JPanel left = new JPanel(new GridLayout(1,1));
	left.setLayout(new BoxLayout(left,BoxLayout.Y_AXIS));
	left.setBackground(Color.YELLOW);
	right.setBackground(Color.BLUE);
	
	JPanel inside = new JPanel();
	inside.setBackground(Color.RED);
	JPanel inside2 = new JPanel();
	inside2.setBackground(Color.GREEN);

	JPanel inside3 = new JPanel();
	inside3.setBackground(Color.ORANGE);

	JPanel inside4 = new JPanel();
	inside4.setBackground(Color.GRAY);

	right.add(inside);
	right.add(inside2);

	left.add(inside3);
	left.add(inside4);

	top.add(left);
	top.add(right);
	JFrame frame = new JFrame();
	frame.add(top);
	frame.setSize(400,400);
	frame.setVisible(true);

	}

	public static void main(String[] args) {
	new Test();
	}
}