package com.group2.physicalgameobjects;

import com.group2.javaCode.Robot;

import java.util.List;

/**
 * This class describes a square on a game board (used for both simple and complex). It has a row and column location on the
 * board (where top left has row col coordinate as (0, 0), top right corner has (0, 15), bottom right corner has
 * (15, 15), and bottom left corner has (15, 0). The square also has a list of what items are currently "on" it
 * in a list, where certain indices are given designated slots for different types of Java objects.
 * It also records if it has an edge barrier that a robot can't pass through, or a diagonal
 * barrier that some robots can pass through (note that two square adjacent to each other that have a edge barrier in between
 * them, have a recorded edge barrier on opposite sides of each other.) Square can also tell you if there is a target
 * tile on it and what that tile is,  and if there is a robot on it, and what that robot is.
 *
 * A square can also tell a robot or use if they can move in a certain direction if the user or robot tells them
 * what direction they want to move.
 */
public class Square {
    private Location rowColumnCoordinate;
    private List listOfItemsOnSquare; //Because you can store different data types in an we need to make a list instead of arraylist(Like barrier and if a robot is on it or not). Designate a index in the array for something.
    //Robot robotOnSquare;
    private boolean robotIsOnSquare;
    private boolean squareIsATargetTile;
    private SquareEdgeBarrier northEdgeBarrier, southEdgeBarrier, eastEdgeBarrier, westEdgeBarrier;
    private boolean doesSquareHaveNorthEdgeBarrier, doesSquareHaveSouthEdgeBarrier, doesSquareHaveEastEdgeBarrier, doesSquareHaveWestEdgeBarrier;
    private boolean wasSquareVisited; //For Dijkstra's Algorithm
    private int actualDistanceValue;//For Dijkstra's Algorithm
    private int preliminaryDistanceValue; //For Dijkstra's Algorithm
    private boolean isSquareTheCurrentNodeForDA;


    /**
     * To first make a square, you need to give it a row coordinate and a column coordinate. Default is that there is
     * nothing on the square at first, you have to add to it.
     * @param rowCoordinate
     * @param columnCoordinate
     */
    public Square(int rowCoordinate, int columnCoordinate){
        //Default is that there is nothing on the square at first, you have to add to it.

        this.rowColumnCoordinate.changeRowCoordinate(rowCoordinate);
        this.rowColumnCoordinate.changeColumnCoordinate(columnCoordinate);
        listOfItemsOnSquare = new List;

        for (int i = 0; i < 4; i++) { //Three spaces for barrier in first index, targetsquare in second, and robotsquare in third.
            //Add null object to all of these now //TODO Is this correct?
            listOfItemsOnSquare.add(null);
        }

        robotIsOnSquare = false;
        squareIsATargetTile = false;

        northEdgeBarrier = null;
        southEdgeBarrier = null;
        eastEdgeBarrier = null;
        westEdgeBarrier = null;

        doesSquareHaveEastEdgeBarrier = false;
        doesSquareHaveNorthEdgeBarrier = false;
        doesSquareHaveSouthEdgeBarrier = false;
        doesSquareHaveWestEdgeBarrier = false;

        wasSquareVisited = false;

        actualDistanceValue = -1;//-1 Represents infinity
        preliminaryDistanceValue = -1; //-1 Represents infinity
        isSquareTheCurrentNodeForDA = false;

    }

    /**
     * This method receives a robot to put on the square, and updates the truth of if a robot is on a square or not,
     * and stores that robot into the square's list (at index 2).
     * @param newRobot
     */

    public addRobotToSquare(Robot newRobot){
        robotIsOnSquare = true;
        listOfItemsOnSquare.add(2, newRobot); //
    }

    /**
     * This method returns true if there is a robot currently on it (the square), or false if not.
     * @return
     */

    public boolean isRobotOnSquare(){
        if (robotIsOnSquare == true){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method returns the actual robot that is on it (the square). WARNING if there is no robot on this
     * and you call it.
     * @return
     */

    public Robot getRobotOnSquare(){
        Robot robotToReturn = (Robot) listOfItemsOnSquare.get(2);//TODO IS THIS OK????? CASTING HERE????
        return robotToReturn;
    }

    /**
     * This method removes a robot from a square.
     */

    public void removeRobotFromSquare(){
        robotIsOnSquare = false;
        listOfItemsOnSquare.set(2, null);

    }

    /*public addBarrierToSquare(){

    }*/ //This is later with a complex board.

    /**
     * This method adds a target tile to a square.
     * @param newTargetTile
     */

    public void addTargetTileToSquare(TargetTile newTargetTile){

        listOfItemsOnSquare.add(1, newTargetTile);
    }

    /**
     * This method returns true if the square has a target tile, and false if not.
     * @return
     */

    public boolean doesSquareHaveATargetTile(){
        if (squareIsATargetTile == true){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * This method add a square edge barrier to the north edge of the square.
     */

    public void addNorthEdgeBarrier(){//TODO may need to tweak later
        this.northEdgeBarrier = new SquareEdgeBarrier();
    }
    /**
     * This method add a square edge barrier to the south edge of the square.
     */

    public void addSouthEdgeBarrier(){
        this.southEdgeBarrier = new SquareEdgeBarrier();
    }
    /**
     * This method add a square edge barrier to the east edge of the square.
     */

    public void addEastEdgeBarrier(){
        this.eastEdgeBarrier = new SquareEdgeBarrier();
    }
    /**
     * This method add a square edge barrier to the west sedge of the square.
     */

    public void addWestEdgeBarrier(){
        this.westEdgeBarrier = new SquareEdgeBarrier();
    }

    public boolean doesSquareHaveNorthEdgeBarrier(){

    }

    public boolean doesSquareHaveSouthEdgeBarrier(){

    }
    public boolean doesSquareHaveEastEdgeBarrier(){

    }
    public boolean doesSquareHaveWestEdgeBarrier(){

    }

    /**
     * This method accepts a string direction "NORTH" "SOUTH" "EAST" "WEST" and returns if the player or user can move
     * in that
     * @param directionIWishToMove
     * @return
     */

    public boolean canIMoveNorth(String directionIWishToMove){
        if (doesSquareHaveNorthEdgeBarrier == true){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean canIMoveSouth(String directionIWishToMove){
        if (doesSquareHaveSouthEdgeBarrier == true){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean canIMoveEast(String directionIWishToMove){
        if (doesSquareHaveEastEdgeBarrier == true){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean canIMoveWest(String directionIWishToMove){
        if (doesSquareHaveWestEdgeBarrier == true){
            return false;
        }
        else{
            return true;
        }
    }

    public void setSquareToUnvisted(){
        this.wasSquareVisited = false;
    }

    public void setSquareToVisited(){
        this.wasSquareVisited = true;
    }

    public void setSquareAsCurrentNodeInDA(){
        this.isSquareTheCurrentNodeForDA = true;
    }

    public void setSquareAsNOTCurrentNodeInDA(){
        this.isSquareTheCurrentNodeForDA = false;
    }

    public void changeActualDistanceValueForDA(int newDistanceValue){
        this.actualDistanceValue = newDistanceValue;
    }

    public void changePreliminaryDistanceValueForDA(int preliminaryDistanceValue){
        this.preliminaryDistanceValue = preliminaryDistanceValue;
    }

    public Location getSquaresRowColumnLocation(){
        return this.rowColumnCoordinate;
    }








}
