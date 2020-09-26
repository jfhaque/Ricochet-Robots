package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import com.group2.gui.GridSquare;
/*
impo com.group2.gui.GridSquare;

packagimport com.group2.gui.GridSquare;

com.group2.gui.GridSquare; */


public class GameBoard extends JFrame implements ActionListener
{

    //public static final int dim = 16;
    //private static final Color COL_BACKGROUND = new Color(190, 190, 190);
    //private static final Color COL_CELL1 = new Color(210, 210, 210);
    //private static final Color COL_CELL2 = new Color(245, 245, 245);
    //private static final Color COL_WALL = new Color(85, 85, 85);
// gui components that are contained in this frame:
    private JPanel topPanel, bottomPanel;	// top and bottom panels in the main window
    private JLabel topLabel;				// a text label to appear in the top panel
    private JButton topButton;				// a 'reset' button to appear in the top panel
    private gui.GridSquare[][] gridSquares;	// squares to appear in grid formation in the bottom panel
    private int x,y;						// the size of the grid

    /*
     *  constructor method takes as input how many rows and columns of gridsquares to create
     *  it then creates the panels, their subcomponents and puts them all together in the main frame
     *  it makes sure that action listeners are added to selectable items
     *  it makes sure that the gui will be visible
     */
    public GameBoard(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.setSize(600,600);

        // first create the panels
        topPanel = new JPanel();
        topPanel.setLayout( new FlowLayout());

        bottomPanel = new JPanel();
        bottomPanel.setLayout( new GridLayout( x, y));
        bottomPanel.setSize(500,500);

        // then create the components for each panel and add them to it

        // for the top panel:
        //topLabel = new JLabel("Click the Buttons!");
        //topButton = new JButton("Reset");
        //topButton.addActionListener( this);			// IMPORTANT! Without this, clicking the button does nothing.

        //topPanel.add( topLabel);
        //topPanel.add ( topButton);


        // for the bottom panel:
        // create the buttons and add them to the grid
        gridSquares = new gui.GridSquare[x][y];
        for ( int column = 0; column < x; column ++)
        {
            for ( int row = 0; row < y; row ++)
            {
                gridSquares [column][row] = new gui.GridSquare( x,y);
                gridSquares [column][row].setSize( 20, 20);
                gridSquares [column][row].setColor( column + row);


                bottomPanel.add( gridSquares [column][row]);
            }
        }

        // now add the top and bottom panels to the main frame
        getContentPane().setLayout( new BorderLayout());
        getContentPane().add( topPanel, BorderLayout.NORTH);
        getContentPane().add( bottomPanel, BorderLayout.CENTER);		// needs to be center or will draw too small

        // housekeeping : behaviour
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setResizable( false);
        setVisible( true);
    }


    /*
     *  handles actions performed in the gui
     *  this method must be present to correctly implement the ActionListener interface
     */
    public void actionPerformed (ActionEvent aevt)
    {
        // get the object that was selected in the gui
        Object selected = aevt.getSource();

        /*
         * I'm using instanceof here so that I can easily cover the selection of any of the gridsquares
         * with just one piece of code.
         * In a real system you'll probably have one piece of action code per selectable item.
         * Later in the course we'll see that the Command Holder pattern is a much smarter way to handle actions.
         */

        // if a gridsquare is selected then switch its color
        if ( selected instanceof gui.GridSquare)
        {
            ((gui.GridSquare) selected).switchColor();
        }

        // if resetting the squares' colours is requested then do so
        if ( selected.equals( topButton) )
        {
            for ( int column = 0; column < x; column ++)
            {
                for ( int row = 0; row < y; row ++)
                {
                    gridSquares [column][row].setColor( column + row);
                }
            }
        }
    }
}
