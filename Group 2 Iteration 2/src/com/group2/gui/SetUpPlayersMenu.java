package com.group2.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SetUpPlayersMenu extends JFrame {
    //Instance Variables
    private Container mainPane;
    private JButton playGameButton;
    private JPanel player1Panel, player2Panel, player3Panel, player4Panel,  groupOfPlayersPanel, playGamePanel;
    private JLabel infoMessage;

    public SetUpPlayersMenu(){
        super();

        mainPane = getContentPane();
        mainPane.setLayout(new BorderLayout());

        //Make four player panels
        player1Panel = new JPanel("Player 1");
        player2Panel = new JPanel("Player 2");
        player3Panel = new JPanel("Player 3");
        player4Panel = new JPanel("Player 4");

        //Set Layouts
        player1Panel.setLayout(new BorderLayout());
        player2Panel.setLayout(new BorderLayout());
        player3Panel.setLayout(new BorderLayout());
        player4Panel.setLayout(new BorderLayout());

        //Add JLabel to Top of each player panel
        player1Panel.add(new JLabel("Player 1"), BorderLayout.NORTH);
        player2Panel.add(new JLabel("Player 2"), BorderLayout.NORTH);
        player3Panel.add(new JLabel("Player 3"), BorderLayout.NORTH);
        player4Panel.add(new JLabel("Player 4"), BorderLayout.NORTH);

        //Add Symbol Picture to each player Panel
        //TODO Add symbols here!
        player1Panel.add(new JLabel("SYMBOL 1 GOES HERE"), BorderLayout.CENTER);
        player2Panel.add(new JLabel("SYMBOL 2 GOES HERE"), BorderLayout.CENTER);
        player3Panel.add(new JLabel("SYMBOL 3 GOES HERE"), BorderLayout.CENTER);
        player4Panel.add(new JLabel("SYMBOL 4 GOES HERE"), BorderLayout.CENTER);

        //Add Textbox for players to write their names
        //TODO Figure out how to get textbook and listen!


        //Now add these player panels to the group player panel


        //Make two panes, one with 4 by 1 grid for players, and 1 for Play Button.
        groupOfPlayerPanel = new JPanel();
        groupOfPlayerPanel.setLayout(new GridLayout(1, 4));



        //Make Button
        playGameButton = new JButton("PLAY");
        //TODO Add listeners to buttons




        pack();
        setVisible(true);
    }
    //TODO Make colour drop down list.
    //TODO Connect symbols to players.
    //TODO Placeholders to put in Player's Names
    //TODO When user presses PLAY, it will need to take the names of the players 
    //TODO
    //TODO
    //TODO
    //TODO

}
