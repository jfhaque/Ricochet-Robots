package com.group2.physicalgameobjects;

import java.awt.*;

public class Player {
//INSTANCE VARIABLES
    String name;
    int playerID;
    int tokenCount;
    Robot playersRobot;
    //ArrayList<> //TODO Set up list of movements later

//CONSTRUCTOR
public Player(){
    this.name = "";
    this.tokenCount = 0;
    this.playersRobot = new Robot();
    this.playerID = -1;
}
//METHODS
    public Robot getRobot(){
        return this.playersRobot;
    }

    public void changeRobot(Robot newRobot){
        this.playersRobot = newRobot;
    }

    public String getName(){
        return this.name;
    }

    public void changeName(String newName){
        this.name = newName;
    }

    public void changePlayerID( int newID){
        this.playerID = newID;
    }


    public void addOneTokenToCount(){
        this.tokenCount += 1;
    }

}