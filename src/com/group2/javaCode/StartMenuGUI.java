package com.group2.javaCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//InputStream in = getClass().getResourceAsStream("/gui/images/guiImages/ricochetRobotsStartImage.jpeg");
//TODO Is this needed for running in jar file?

public class StartMenuGUI extends JFrame implements ActionListener{
    //Instance Variables
    private Container mainPane;
    private JButton startNewGameButton, loadOldGameButton;
    private JPanel buttonPanel, informationPanel;
    private JLabel infoMessage ;
   
  
    

    //Constructor
    public StartMenuGUI(){
        super();

        mainPane = getContentPane();
        mainPane.setPreferredSize(new Dimension(500,500));
        mainPane.setLayout(new BorderLayout());


        //Make Buttons
        startNewGameButton = new JButton("START NEW GAME");
        startNewGameButton.addActionListener(this);

        loadOldGameButton = new JButton("LOAD OLD GAME");
        loadOldGameButton.addActionListener(this);

        //Make the button label
        buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(startNewGameButton);
        buttonPanel.add(loadOldGameButton);



        mainPane.add(buttonPanel, BorderLayout.SOUTH);


        //TODO Add listeners to buttons
      


        //Make the information panel
        infoMessage = new JLabel("Welcome to Ricochet Robots!");
        informationPanel = new JPanel();
        informationPanel.add(infoMessage);
        mainPane.add(informationPanel, BorderLayout.NORTH);

        /*

        //Add a Ricochet Robot Nice Start Image //TODO Add this later.

        ImageIcon ricochetRobotStartImage = new ImageIcon("/gui/images/guiImages/ricochetRobotsStartImage.jpeg");
        JLabel ricochetRobotStartLabel = new JLabel(ricochetRobotStartImage);
        ricochetRobotStartLabel.setBounds(10, 10, 400, 400);//TODO Fix these dimensions.
        ricochetRobotStartLabel.setVisible(true);

        mainPane.add(ricochetRobotStartLabel, BorderLayout.CENTER); //Add to main pane
  */



        //Pack and make visible
        pack();
        setVisible(true);

        
    }

    public void actionPerformed(ActionEvent e){
        Object x= e.getSource();
        if(x== startNewGameButton){
            super.dispose();
            gui.SetupPlayersGUI newSetupPlayersGUI = new gui.SetupPlayersGUI();

        }
        else if (x == loadOldGameButton){
            super.dispose();
            gui.GameBoardGUI newGameBoardGUI = new gui.GameBoardGUI(16,16);
        }
       
    }

    //Methods


}
