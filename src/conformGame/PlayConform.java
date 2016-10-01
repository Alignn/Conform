package conformGame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class PlayConform extends JApplet implements MouseListener {
	private static final long serialVersionUID = -2404586489507281325L;
	private Button waitButton;
	private JFrame mainFrame;
	private JPanel gridPanel;
	private Square[][] grid;
	private static int gridWidth=25;
	private static int gridHeight=25;
	private static int greenDecay=100; //when a square is clicked, surrounding squares are also colored green, but this much less
	
	public PlayConform() throws HeadlessException {
	}

	public void setupGame(int width, int height) {
        grid = new Square[width][height];
		mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());
        waitButton = new Button("Wait");
        waitButton.addMouseListener(this);
        gridPanel = new JPanel(new GridLayout(height,width));
        for (int x = 0  ;  x < width  ;  x++) {
			for (int y = 0  ;  y < height  ;  y++) {
				grid[x][y] = new Square(x,y,this);
				gridPanel.add(grid[x][y]);
			}
		}
        
        //Set up the content pane.
        mainFrame.add(gridPanel);
        mainFrame.add(waitButton);
        mainFrame.setPreferredSize(new Dimension(450,420));
        
        //Display the window.
        mainFrame.pack();
        mainFrame.setVisible(true);
	}

	public void averageOutColours() {
		//make temporary new grid where all changes are made
		Square[][] newGrid = new Square[gridWidth][gridHeight];
		for (int x = 0  ;  x < gridWidth  ;  x++) {
			for (int y = 0  ;  y < gridHeight  ;  y++) {
				newGrid[x][y] = new Square(x,y,this);
			}
		}
		
		//adjust colour of all grid squares to the average of their neighbours
		for (Square[] squareRow : grid) {
			for (Square square : squareRow) {
				int sum=0;
				int neighbours=0;
				int xPos=square.getXPos();
				int yPos=square.getYPos();
				for (int x = xPos-1  ;  x <= xPos+1  ;  x++) {
					if (x >= 0  &&  x < gridWidth) {
						for (int y = yPos-1  ;  y <= yPos+1  ;  y++) {
							if (y >= 0  &&  y < gridHeight  &&  !square.equals(grid[x][y])) {
								neighbours++;
								sum+=grid[x][y].getBackground().getGreen();
							}
						}
					}
				}
				int average=sum/neighbours;
				newGrid[xPos][yPos].setBackground(new Color(255-average,average,0));
			}
		}
		for (int x = 0  ;  x < gridWidth  ;  x++) {
			for (int y = 0  ;  y < gridHeight  ;  y++) {
				grid[x][y].setBackground(newGrid[x][y].getBackground());
			}
		}
	}
	
	
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==waitButton)
		{
			averageOutColours();
		}
	}
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof JPanel)
		{
			((JPanel)e.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN));
		}
	}
	public void mouseExited(MouseEvent e) {
		if (e.getSource() instanceof JPanel)
		{
			((JPanel)e.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		}
	}
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof Square) {
			averageOutColours();
			
			//set colour of clicked square to green, and ditto for neighbours and neighbours' neighbours (gradually less so)
			Square square = (Square) e.getSource();
			int xPos=square.getXPos();
			int yPos=square.getYPos();
			int loops=255/greenDecay;
			for (int i = 0  ;  i <= loops  ;  i++) {
				for (int x = xPos-i  ;  x <= xPos+i  ;  x++) {
					if (x >= 0  &&  x < gridWidth) {
						for (int y = yPos-i  ;  y <= yPos+i  ;  y++) {
							if (y >= 0  &&  y < gridHeight) {
								int green = 255 - i*greenDecay;
								green += grid[x][y].getBackground().getGreen();
								green = Integer.min(green, 255);
								int red = grid[x][y].getBackground().getRed();
								red -= green;
								red = Integer.max(red, 0);
								
								grid[x][y].setBackground(new Color(red,green,0));
							}
						}
					}
				}
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
	}

	public void init() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setupGame(gridWidth,gridHeight); //width, height of grid (in game squares, not pixels)
            }
        });
	}
}
