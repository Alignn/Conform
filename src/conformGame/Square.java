package conformGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JPanel;

public class Square extends JPanel {
	private static final long serialVersionUID = -8615969317386939559L;
	private int xPos;
	private int yPos;
	
	public Square(int x, int y, JApplet parentApplet) {
		super();
		this.setBackground(Color.RED);
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		setPreferredSize(new Dimension(15,15));
		addMouseListener((MouseListener) parentApplet);
		xPos = x;
		yPos = y;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}
}
