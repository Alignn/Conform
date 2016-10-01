package conformGame;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JPanel;

public class Square extends JPanel {
	private int xPos;
	private int yPos;
	
	public Square(int x, int y, JApplet parentApplet) {
		super();
		this.setBackground(Color.RED);
		setBorder(BorderFactory.createEtchedBorder());
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
