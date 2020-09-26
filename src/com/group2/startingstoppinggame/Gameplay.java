package com.group2.startingstoppinggame;

import com.group2.gui.GameSettings;
import com.group2.javaCode.Robot;
import com.group2.physicalgameobjects.*;
import com.group2.gameplayrules.*;

/**
 * This class creates a new gameplay for the game. It takes the settings recorded in the GameSettings and then creates
 * all of the different physical objects needed for the game.
 */
public class Gameplay {
    Player player1, player2, player3, player4, player5;
    Robot redStarRobot, greenSquareRobot, yellowCircleRobot, blueHalfMoonRobot;
    TargetChip redStarTargetChip, redCircleTargetChip, redSquareTargetChip, redHalfMoonTargetChip;
    TargetChip greenStarTargetChip, greenCircleTargetChip, greenSquareTargetChip, greenHalfMoonTargetChip;
    TargetChip yellowStarTargetChip, yellowCircleTargetChip, yellowSquareTargetChip, yellowHalfMoonTargetChip;
    TargetChip blueStarTargetChip, blueCircleTargetChip, blueSquareTargetChip, blueHalfMoonTargetChip;
    TargetChip multicoloredTargetChip;
    GameSettings theRecordedGameSettings;
    CurrentGamePlay theCurrentGameplay;
    Timer biddingTimer;
    GameBoard theGameBoard;


    Player playerArray[];
    Robot robotArray[];
    TargetChip arrayOfAllTargetChipsInGame[];
    TargetChip arrayOfShuffledTargetChipsToBeDrawn[];//TODO Maybe Arraylist Later for easy popping




    public Gameplay(GameSettings newGameSettings){
        this.theRecordedGameSettings = newGameSettings;
        //First set up the game //TODO if this isn't a new game you load old game here and skip this.
        initializeBrandNewGameObjects();

        //Start the Gameplay

        //When a Player makes the first bid

        this.biddingTimer.startTime();

        while (this.biddingTimer.hasBiddingTimeStopped() == false){

        }

        //After this, the timer has stopped.












    }

    /**
     * This worker method returns what player had the lower bid.
     * @return
     */
    private Player getPlayerWithCurrentLowestBid(){

        int currentLowestBid = player1.getBidNumber();
        Player currentPlayerWithLowestBid = player1;

        if (currentLowestBid > player2.getBidNumber()){
            currentLowestBid = player2.getBidNumber();
            currentPlayerWithLowestBid = player2;
        }

        if (currentLowestBid > player3.getBidNumber()) {
            currentLowestBid = player3.getBidNumber();
            currentPlayerWithLowestBid = player3;
        }

        if (currentLowestBid > player4.getBidNumber(){
            currentLowestBid = player4.getBidNumber();
            currentPlayerWithLowestBid = player4;
        }

        return currentPlayerWithLowestBid;
    }

    private Player getPlayerWithNextLowestBid(){

    }

    public void aPlayersMakesFIRSTBidButtonIsClicked(Player playerWhoMa{
        //boolean timerHasStopped = false;
        //Start the timer


    })


    /**
     * This worker method breaks down the making of objects into separate steps.
     */
    private void initializeBrandNewGameObjects() {
        createAllRobots();
        createAllNewPlayers();
        createAllTokens();
        createTheSelectedBoard();
        initializeTimer();
    }

    /**
     * This method initializes a new timer object for the whole game.
     */
    private void initializeTimer(){
        this.biddingTimer = new Timer();
    }

    /**
     * This method creates all the robots.
     */
    private void createAllRobots(){
        //First make the robot array
        robotArray = new Robot[4];

        //Next create robot objects.
        redStarRobot = new Robot("RED", "STAR");
        greenSquareRobot = new Robot("GREEN", "SQUARE");
        yellowCircleRobot = new Robot("YELLOW", "CIRCLE");
        blueHalfMoonRobot = new Robot("BLUE", "HALFMOON");

        //Set up the locations of all robots for each player

        //Then put these robots into the robot array.
        robotArray[0] = redStarRobot;
        robotArray[1] = greenSquareRobot;
        robotArray[2] = yellowCircleRobot;
        robotArray[3] = blueHalfMoonRobot;
    }

    /**
     * This method creates all the players, and gives them their robots.
     */
    private void createAllNewPlayers(){
        //First create arrays to hold all players and robots
        playerArray = new Player[4];

        //First create Player Objects

        player1 = new Player();
        player2 = new Player();
        player3 = new Player();
        player4 = new Player();

        //Get all of the players' recorded type, and then
        //change the player type of these players according to what is recorded in the GameSettings class.

        //Player 1
        if (theRecordedGameSettings.player1PlayerTypeisHuman()){
            player1.setPlayerTypeToHuman();
        }
        else{
            player1.setPlayerTypeToComputer();
        }
        //Player 2
        if (theRecordedGameSettings.player2PlayerTypeisHuman()){
            player2.setPlayerTypeToHuman();
        }
        else{
            player2.setPlayerTypeToComputer();
        }
        //Player 3
        if (theRecordedGameSettings.player3PlayerTypeisHuman()){
            player3.setPlayerTypeToHuman();
        }
        else{
            player3.setPlayerTypeToComputer();
        }

        //Player 4
        if (theRecordedGameSettings.player3PlayerTypeisHuman()){
            player4.setPlayerTypeToHuman();
        }
        else{
            player4.setPlayerTypeToComputer();
        }


        //Then put these players into the player array.
        playerArray[0] = player1;
        playerArray[1] = player2;
        playerArray[2] = player3;
        playerArray[3] = player4;

        //Hook up players with their robots //TODO This may need to be fixed.
        player1.setPlayersRobot(robotArray[0]);//Player 1 is always with the red star robot
        player2.setPlayersRobot(robotArray[1]);//Player 2 is always with the green star robot
        player3.setPlayersRobot(robotArray[2]);//Player 3 is always with the yellow circle robot
        player4.setPlayersRobot(robotArray[3]);//Player 4 is always with the blue half moon robot

    }

    /**
     * This method creates all the tokens, and puts them in an array. Later this tokens will be put in a random order
     * for gameplay when a player draws them.
     */
    private void createAllTokens(){
        //Generate the array of all tokens in the game //TODO token to targetChips
        //TODO Are these target chips designed correctly?

        redStarTargetChip = new TargetChip("RED", "STAR");
        redCircleTargetChip = new TargetChip("RED", "CIRCLE");
        redSquareTargetChip = new TargetChip("RED", "SQUARE");
        redHalfMoonTargetChip = new TargetChip("RED", "HALFMOON");

        greenStarTargetChip = new TargetChip("GREEN", "STAR");
        greenCircleTargetChip = new TargetChip("GREEN", "CIRCLE");
        greenSquareTargetChip = new TargetChip("GREEN", "SQUARE");
        greenHalfMoonTargetChip = new TargetChip("GREEN", "HALFMOON");

        yellowStarTargetChip = new TargetChip("YELLOW", "STAR");
        yellowCircleTargetChip = new TargetChip("YELLOW", "CIRCLE");
        yellowSquareTargetChip = new TargetChip("YELLOW", "SQUARE");
        yellowHalfMoonTargetChip = new TargetChip("YELLOW", "HALFMOON");

        blueStarTargetChip = new TargetChip("BLUE", "STAR");
        blueCircleTargetChip = new TargetChip("BLUE", "CIRCLE");
        blueSquareTargetChip = new TargetChip("BLUE", "SQUARE");
        blueHalfMoonTargetChip = new TargetChip("BLUE", "HALFMOON");

        multicoloredTargetChip = new TargetChip("MULTICOLOR", "VORTEX");



        //Declare array for all chips, and add all these chip objects to this array.
        arrayOfAllTargetChipsInGame = new TargetChip[17];

        arrayOfAllTargetChipsInGame[0] = redStarTargetChip;
        arrayOfAllTargetChipsInGame[1] = redCircleTargetChip;
        arrayOfAllTargetChipsInGame[2] = redSquareTargetChip;
        arrayOfAllTargetChipsInGame[3] = redHalfMoonTargetChip;

        arrayOfAllTargetChipsInGame[4] = greenStarTargetChip;
        arrayOfAllTargetChipsInGame[5] = greenCircleTargetChip;
        arrayOfAllTargetChipsInGame[6] = greenSquareTargetChip;
        arrayOfAllTargetChipsInGame[7] = greenHalfMoonTargetChip;

        arrayOfAllTargetChipsInGame[8] = yellowStarTargetChip;
        arrayOfAllTargetChipsInGame[9] = yellowCircleTargetChip;
        arrayOfAllTargetChipsInGame[10] = yellowSquareTargetChip;
        arrayOfAllTargetChipsInGame[11] = yellowHalfMoonTargetChip;

        arrayOfAllTargetChipsInGame[12] = blueStarTargetChip;
        arrayOfAllTargetChipsInGame[13] = blueCircleTargetChip;
        arrayOfAllTargetChipsInGame[14] = blueSquareTargetChip;
        arrayOfAllTargetChipsInGame[15] = blueHalfMoonTargetChip;

        arrayOfAllTargetChipsInGame[16] = multicoloredTargetChip;



        //Randomize the tokens for the gameplay order. //TODO Randomize the tokens for the gameplay order and put them into the arrayOfShuffledTargetChipsToBeDrawn.
        //TODO Right now these are in the same order. REMEMBER ABOUT COPYING ARRAY"S IT"S WIERD, IT EDITS ITSELF! DEEP COPY!

        for (int i = 0; i < arrayOfAllTargetChipsInGame.length; i++){
            arrayOfShuffledTargetChipsToBeDrawn[i] = arrayOfAllTargetChipsInGame[i]; //TODO I THINK THIS IS CORRECT, CHECK IT WILL NOT CHANGE ORIGINAL ARRAY OF ALL TARGET CHIPS.
        }//TODO Does multicolored vortex chip need to be in list?


    }

    /**
     * This method creates either the simple or complex board depending on the recorded game settings.
     */
    private void createTheSelectedBoard(){
        if (theRecordedGameSettings.boardTypeIsSimple() == true){
            theGameBoard = new SimpleBoard();
        }
        else{
            theGameBoard = new ComplexBoard();
        }

    }









}







