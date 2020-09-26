package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class ChooseDisplayGUI extends JFrame implements ActionListener{
    //Instance Variables
    private Container mainPane;
    private JButton startButton;
    private JPanel buttonPanel, informationPanel, playerPanel, boardPanel, difficultyPanel;
    private JLabel infoMessage, difficulty, board,humanPlayer, player1, player2, player3, player4;
    private JRadioButton simple, complex, easy, hard;
    private ButtonGroup boardTypes, difficultyRadioButtons;
    private JComboBox<String> playerList,playerList2,playerList3;


    //Constructor
    public ChooseDisplayGUI(){
        
        getContentPane().setBackground(Color.CYAN);
        mainPane = getContentPane();
        mainPane.setPreferredSize(new Dimension(1000,1000));
        mainPane.setLayout(new GridLayout(5,1));
        
        

        //Board Style
        simple= new JRadioButton("Simple");
        complex= new JRadioButton("Complex");

    

        
        //TODO Add listeners to buttons

        
      
        


        //Making the board panel
        boardPanel= new JPanel();
        boardPanel.setBackground(Color.CYAN);
        board= new JLabel("Please Choose a board type: ");
        boardTypes= new ButtonGroup();
        
       
        boardPanel.add(board);
        boardPanel.add(simple);
        boardPanel.add(complex);
        boardTypes.add(simple);
        boardTypes.add(complex);
        mainPane.add(boardPanel);

        //Making the player panel
        

        playerPanel= new JPanel(new GridLayout(6,2));
        playerPanel.setBackground(Color.YELLOW);
        playerList= new JComboBox<String>();
        playerList.addItem("Human Player");
        playerList.addItem("Computer Player");
        
        playerList2= new JComboBox<String>();
        playerList2.addItem("Human Player");
        playerList2.addItem("Computer Player");
        
        playerList3= new JComboBox<String>();
        playerList3.addItem("Human Player");
        playerList3.addItem("Computer Player");


        playerList.setSelectedIndex(1);
        playerList2.setSelectedIndex(1);
        playerList3.setSelectedIndex(1);

        //Labels for the Number of Players
        player1= new JLabel("Player 1:     Red Robot");
        humanPlayer= new JLabel("Human Player");
        player2= new JLabel("Player 2:     Green Square");
        player3= new JLabel("Player 3:     Yellow Circle");
        player4= new JLabel("Player 4:     Blue Moon");
        

        //Adding all the components
        playerPanel.add(player1);
        playerPanel.add(humanPlayer);
        playerPanel.add(player2);
        playerPanel.add(playerList);
        playerPanel.add(player3);
        playerPanel.add(playerList2);
        playerPanel.add(player4);
        playerPanel.add(playerList3);


        mainPane.add(playerPanel);
        
        
          
        //Make the Difficulty panel
        difficultyPanel= new JPanel();
        difficultyPanel.setBackground(Color.YELLOW);
        difficultyRadioButtons= new ButtonGroup();
        difficulty= new JLabel("Please select the difficulty of the computer player (if any): ");
        easy= new JRadioButton("Easy");
        hard= new JRadioButton("Hard");
        difficultyRadioButtons.add(easy);
        difficultyRadioButtons.add(hard);
        difficultyPanel.add(difficulty);
        difficultyPanel.add(easy);
        difficultyPanel.add(hard);

        mainPane.add(difficultyPanel);
        
        //Make the button label
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.YELLOW);
        startButton= new JButton("Start");
        startButton.addActionListener(this);
        buttonPanel.add(startButton);
        mainPane.add(buttonPanel);


        //Pack and make visible
        pack();
        setVisible(true);
        
    }

    //Methods
    
    public void actionPerformed(ActionEvent e){
        Object event= e.getSource();
        if(event== startButton){
            super.dispose();
            GameBoard gameboard= new GameBoard(16,16);

        }
       
    }



}

