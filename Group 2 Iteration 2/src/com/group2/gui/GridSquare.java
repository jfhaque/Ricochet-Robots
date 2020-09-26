package gui;

import javax.swing.*;
import java.awt.*;

/*
 *  a simple extension of JButton which allows the background colour to be set and switched back and forth with ease
 *
 *  there are other ways of doing this, but it's a neat way to demonstrate how to create your own gui components
 *  (as well as how to use ternary operators)
 */
public class GridSquare extends JButton
{
    private int xcoord, ycoord;			// not used in this demo, but might be helpful in future...

    // constructor takes the x and y coordinates of this square
    public GridSquare( int xcoord, int ycoord)
    {
        super();
        this.setSize(50,50);
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }

    // if the decider is even, it chooses black, otherwise white (for 'column+row' will allow a chequerboard effect)
    public void setColor( int decider)
    {
        Color colour = (int) (decider/2.0) == (decider/2.0) ? Color.WHITE : Color.white;
        this.setBackground( colour);
    }

    // if the square is black it becomes white, and vice-versa
    public void switchColor()
    {
        Color colour = (getBackground() == Color.black) ? Color.white: Color.black;
        this.setBackground( colour);
    }
}