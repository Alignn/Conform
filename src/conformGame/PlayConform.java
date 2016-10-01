package conformGame;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class PlayConform extends JApplet implements MouseListener {
	private Button waitButton;
	private JFrame mainFrame;
	private JPanel gridPanel;
	private Square[][] grid;
	
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
        for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				grid[x][y] = new Square(x,y,this);
				gridPanel.add(grid[x][y]);
			}
		}
        
        //Set up the content pane.
        mainFrame.add(gridPanel);
        mainFrame.add(waitButton);
        mainFrame.setPreferredSize(new Dimension(450,400));
        
        //Display the window.
        mainFrame.pack();
        mainFrame.setVisible(true);
	}

	public void init() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setupGame(25,25); //width, height of grid (in game squares, not pixels)
            }
        });
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==waitButton)
		{
			//adjust colour of all grid squares to the average of their neighbours
			for (Square[] squareRow : grid)
			{
				for (Square square : squareRow)
				{
					/*sum=0 
					neighbors=0
					xPos = i%GRID_WIDTH
					yPos = floor(i/GRID_HEIGHT)
					currentSquare = squares.get(xPos,yPos)
					for x=xPos-1 to xPos+1
					{
						for y=yPos-1 to yPos+1
						{
							square = squares.get(x,y)
							if square != null && square != currentSquare //TODO: fuck... now i remember why i wanted a SquareGrid... need to check for out of bounds squares.
							{
								sum += square.getGreen()
								neighbors++
							}
						}
					}
					average = sum/neighbors
					currentSquare.setColor(255-average,average,0)*/
				}
			}
		}
		else if (e.getSource() instanceof Square)
		{
			//set colour of clicked square to green, and ditto (gradually less so) for neighbours and neighbours' neighbours
			Square clickedSquare = (Square) e.getSource();
			clickedSquare.setBackground(Color.GREEN);
			
			/*for i = 0 to loops
			{
				for j = 0 to i
				{
					square = squares.get(x-i,y-i+j) //from upper left, downwards
					if square != null
						square.setColor(255-green, green, 0) //assuming color is 0-255
					square = squares.get(x-i+j,y-i) //from upper left, rightwards
					if square != null
						square.setColor(255-green, green, 0)
					square = squares.get(x+i,y+i-j) //from lower right, upwards
					if square != null
						square.setColor(255-green, green, 0)
					square = squares.get(x+i-j,y+i) //from lower right, leftwards
					if square != null
						square.setColor(255-green, green, 0)
				}		
				green = green - GREEN_DECAY_FROM_DISTANCE
			}*/
		}
		mainFrame.validate();
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}

}
