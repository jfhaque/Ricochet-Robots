import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StartMenuGUI extends JFrame implements ActionListener{
    //Instance Variables
    private Container mainPane;
    private JButton startNewGameButton, loadSavedGameButton;
    private JPanel buttonPanel, informationPanel;
    private JLabel infoMessage;


    //Constructor
    public StartMenuGUI(){
        super();

        mainPane = getContentPane();
        mainPane.setPreferredSize(new Dimension(500,500));
        mainPane.setLayout(new BorderLayout());
        

        //Make Buttons
        startNewGameButton = new JButton("START NEW GAME");
        loadSavedGameButton = new JButton("LOAD SAVED GAME");

        //TODO Add listeners to buttons
        
        startNewGameButton.addActionListener(this);
        loadSavedGameButton.addActionListener(this);
        //Make the information label
        infoMessage = new JLabel("Welcome to Ricochet Robots!");
        informationPanel = new JPanel();
        informationPanel.add(infoMessage);
        //Image bgImage= Toolkit.getDefaultToolkit().getImage("/home/lrh036/Computer Science/Winter 2020/COMP 2005");   
        //mainPane.setBackgroundImage(bgImage);

        mainPane.add(informationPanel, BorderLayout.NORTH);

        //Make the button label
        buttonPanel = new JPanel();
        
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(startNewGameButton);
        buttonPanel.add(loadSavedGameButton);
        mainPane.add(buttonPanel, BorderLayout.SOUTH);


        //Pack and make visible
        pack();
        setVisible(true);

        
    }

    public void actionPerformed(ActionEvent e){
        Object x= e.getSource();
        if(x== startNewGameButton){
            ChooseDisplayGUI newChooseDisplayGUI= new ChooseDisplayGUI();

        }
        else if(x== loadSavedGameButton){

        }
    }

    //Methods


}