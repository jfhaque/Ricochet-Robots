package com.group2.gameplayrules;


import com.group2.javaCode.Robot;
import com.group2.physicalgameobjects.*;

import java.util.ArrayList;

/**
 * This class deals with the bidding process.
 */
public class MakingBidsProcess {

    int bid;

    public MakingBidsProcess(){

    }

    public void makeBid(){

    }

    /**
     * This method takes in the list of players, an array of their robots on the board, and the current setup of the gameboard.
     * It returns the
     * The array must be written this way: first index is player1, second player 2, etc.
     * The array for robots must be written in this way: first index is player1's robot, etc.
     *
     * @param player1
     * @param player2
     * @param player3
     * @param player4
     * @param robot1
     * @param robot2
     * @param robot3
     * @param robot4
     * @param theCurrentGameBoard
     */

    public void makingBidsProcess(Player player1, Player player2, Player player3, Player player4, Robot robot1, Robot robot2, Robot robot3, Robot robot4, int[][] targetTileRowColumnLocation, SimpleBoard theCurrentSimpleGameBoard){
        //First get each of the robot's locations on the board
        int [][] robot1Location = robot1.getCurrentRowColumnLocation();
        int [][] robot2Location = robot2.getCurrentRowColumnLocation();
        int [][] robot3Location = robot3.getCurrentRowColumnLocation();
        int [][] robot4Location = robot4.getCurrentRowColumnLocation();


    }

    public boolean wereMovesLegalAndAccurate(int BidNumber, Square[] listOfSquaresMovedInOrder, Robot robotToMove, Square squareWithTargetTile){

        //One move is going in a straight line until it hits a barrier.

        //You cannot go through a barrier.

        //First count the number of moves. If the moves don't match the bid number, then it's wrong.

        //First confirm that the starting square is the robot's current position and the last square is the target tile.

        Location robotLocation = robotToMove.getRobotLocation();
        Location startSquareLocation = listOfSquaresMovedInOrder[0].getSquaresRowColumnLocation();
        Location squareWithTargetTileLocation = squareWithTargetTile.getSquaresRowColumnLocation();
        Location lastSquareLocation = listOfSquaresMovedInOrder[listOfSquaresMovedInOrder.length-1].getSquaresRowColumnLocation();

        if ( (robotLocation.equals(startSquareLocation) == false) ||  ( lastSquareLocation.equals(squareWithTargetTileLocation) == false) {
            return false; //If it's not, return false and stop.
        }

        //If true, we continue:

        boolean weHaveReachedTargetTile = false;
        boolean moveIllegal = false;
        boolean hasRobotMovedPerpendicularAtLeastOnce = false;

        //TODO Robots block the way too, not just barriers.

        while (weHaveReachedTargetTile == false && moveIllegal == false){
            //get the next square in the array
            for (int i = 0; i < listOfSquaresMovedInOrder.length - 1; i++) {
                Square currentSquareInArray = listOfSquaresMovedInOrder[i];
                Square nextSquareInArray = listOfSquaresMovedInOrder[i + 1];


                //First is there a barrier in between squares in that direction?
                if (isThereABarrierBlockingIntendedDirection(currentSquareInArray, nextSquareInArray) == true) {
                    return false; //If there is, we stop. Player clicked an invalid path. The robot can't pass through a barrier.
                }

                //If not, then check if there is a robot on the next square
                if (nextSquareInArray.isRobotOnSquare() == true) {
                    return false; //If there is, we stop. Player clicked an invalid path. They can't occupy
                                  // the same space as another robot.
                }

            //Later, here is where you can check for the diagonal barrier.

                //If not, we continue:

                //Next check if the nextSquareInArray is a target tile. (We already know we have the RIGHT target tile.)
                if (nextSquareInArray.doesSquareHaveATargetTile() == true){
                    //If this is true, either two things need to happen: There is either a barrier in the
                    //same direction so that the robot stops, or there is a robot in the same direction
                    //that stops this robot on the target square. We need to check both.

                    //First check if there is a barrier in the same direction on the target square that can stop
                    //the robot.
                    if (doesFinalSquareWithTargetTileHaveBarrierToStopIntendedDirection(..., nextSquareInArray) == true){
                        weHaveReachedTargetTile = true;
                        return true;
                    }
                    else if (doesFinalSquareWithTargetTileHaveAnotherRobotByItToStopIntendedDirection(... ...) == true){
                        weHaveReachedTargetTile = true;
                        return true; //The robot would keep going so it doesn't work.
                    }
                    else{
                        return false;
                    }


                }

                //If not, we continue the while loop to the next pair of squares.
                //TODO NOTE THE ROBOT CAN'T GO STRAIGHT TO IT. IT HAS TO TAKE ANOTHER ROUTE (SO AT LEAST GO PERPENDICULAR ONE TIME).
            }



                /* Do Ricochet tests later

                //Is the direction perpendicular to the barrier on the square we are on right now?
                String intendedDirection = ;
                isDirectionPerpendicularToBarrierOnSquareWeAreOn(String intendedDirection, Square squareWeAreOn);


                //Are we hitting a barrier to ricochet off of?
*/




        }




        //Then check
        /*
        while we have
        if (coordinate of first square == coordinate of robot){
            get the next square in the array
            What direction is this square relative to the first square?
            Is there a barrier on the direction side of the first square?
            If yes, the move is not right and return false.
            If no, keep going.



        }
        */




    }



    /**
     * This method takes an arrayList with Square elements and returns as array of Square elements from the
     * ArrayList in the same order as in the ArrayList.
     * @param squareArrayList
     * @return
     */

    private Square[] convertSquareArrayListToSquareArray(ArrayList<Square> squareArrayList){
        Square [] squareArray = new Square [squareArrayList.size()];
        for (int i = 0; i < squareArrayList.size(); i++){
            squareArray[i] = squareArrayList.get(i);
        }
        return squareArray;
    }

    /**
     * This method returns true if there is a barrier on the target square that can stop the robot from moving
     * in it's intended direction, false if not.
     * @param intendedDirection A string "NORTH", "SOUTH", "EAST", or "WEST" for robot's intended direction.
     * @param squareWithTargetTile A square that has the target tile where the robot hopes to stop on.
     * @return true if there is a barrier on the target square that can stop the robot from moving in it's intended
     * direction, false if not.
     */
    private boolean doesFinalSquareWithTargetTileHaveBarrierToStopIntendedDirection(String intendedDirection, Square squareWithTargetTile){
        if (intendedDirection.equals("NORTH") && (squareWithTargetTile.doesSquareHaveNorthEdgeBarrier() == true)){
            return true;
        }
        else if (intendedDirection.equals("SOUTH") && (squareWithTargetTile.doesSquareHaveSouthEdgeBarrier() == true)){
            return true;
        }
        else if (intendedDirection.equals("EAST") && (squareWithTargetTile.doesSquareHaveEastEdgeBarrier() == true)){
            return true;
        }
        else if (intendedDirection.equals("WEST") && (squareWithTargetTile.doesSquareHaveWestEdgeBarrier() == true)){
            return true;
        }
        else{
            return false;
        }

    }

    /* ADD THIS METHOD TO THE SIMPLE BOARD CLASS/COMPLEX THAT BUILDS
    public boolean doesThisWalkableSquareCoordinateExist(int rowCoordinate, int columnCoordinate){
        if ( (rowCoordinate < 0) ||
             (columnCoordinate < 0) ||
             (rowCoordinate > 15) ||
             (columnCoordinate > 15) ||
             //Do the inner coordinates
             ( (row Coordinate == 7) && (columnCoordinate == 7)) ||
             ( (row Coordinate == 7) && (columnCoordinate == 8)) ||
             ( (row Coordinate == 8) && (columnCoordinate == 7)) ||
             ( (row Coordinate == 8) && (columnCoordinate == 8)) ) {

            return false;
            }
            else {
            return true;
            }


    }



     */

    /**
     * This method tests if the FinalSquareWithTargetTileHaveAnotherRobotByItToStopIntendedDirection
     * @param intendedDirection
     * @param squareWithTargetTile
     * @param theCurrentGameBoard
     * @return
     */
    private boolean doesFinalSquareWithTargetTileHaveAnotherRobotByItToStopIntendedDirection(String intendedDirection, Square squareWithTargetTile, GameBoard theCurrentGameBoard){
        //First we need to get the square from the gameboard that is adjacent to the target tile square and in the
        //same direction as the intended direction of the moving robot.

        Location targetTileSquaresLocation = squareWithTargetTile.getSquaresRowColumnLocation();
        Location squareWithPotentialBlockingRobotLocation = new Location(-1, -1);//Default location does not exist. We will change here.

        if (intendedDirection.equals("NORTH")){
            squareWithPotentialBlockingRobotLocation.changeRowCoordinate(targetTileSquaresLocation.getRowCoordinate() + 1);
            squareWithPotentialBlockingRobotLocation.changeColumnCoordinate(targetTileSquaresLocation.getColumnCoordinate());
        }
        else if (intendedDirection.equals("SOUTH")){
            squareWithPotentialBlockingRobotLocation.changeRowCoordinate(targetTileSquaresLocation.getRowCoordinate() - 1);
            squareWithPotentialBlockingRobotLocation.changeColumnCoordinate(targetTileSquaresLocation.getColumnCoordinate());
        }
        else if (intendedDirection.equals("EAST")){
            squareWithPotentialBlockingRobotLocation.changeRowCoordinate(targetTileSquaresLocation.getRowCoordinate());
            squareWithPotentialBlockingRobotLocation.changeColumnCoordinate(targetTileSquaresLocation.getColumnCoordinate() + 1);
        }
        else if (intendedDirection.equals("WEST")){
            squareWithPotentialBlockingRobotLocation.changeRowCoordinate(targetTileSquaresLocation.getRowCoordinate());
            squareWithPotentialBlockingRobotLocation.changeColumnCoordinate(targetTileSquaresLocation.getColumnCoordinate() - 1);
        }

        //Next, does this square exist to be walkable?
        if (theCurrentGameBoard.doesThisWalkableSquareCoordinateExist() == false){
            return false; //TODO Check if returning this is correct.
        }
        //Else, keep going.

        //Finally, is there a robot on this square
        if (theCurrentGameBoard.getSquare... is tehre a robot == false){
            return false;
        }
        else{
            return true;
        }

    }

    /**
     *
     * Note, square MUST have different coordinates that are positive integers, or it won't work.
     * @param startingSquare
     * @param endingSquare
     * @return
     */

    private String returnIntendedDirection(Square startingSquare, Square endingSquare){
        int rowCoordinateStartSquare = startingSquare.getSquaresRowColumnLocation().getRowCoordinate();
        int columnCoordinateStartSquare = startingSquare.getSquaresRowColumnLocation().getColumnCoordinate();

        int rowCoordinateEndingSquare = endingSquare.getSquaresRowColumnLocation().getRowCoordinate();
        int columnCoordinateEndingSquare = endingSquare.getSquaresRowColumnLocation().getColumnCoordinate();

        int EndRowCoordMinusStartRowCoord = rowCoordinateEndingSquare - rowCoordinateStartSquare;
        int EndColCoordMinusStartColCoord = columnCoordinateEndingSquare - columnCoordinateStartSquare;

        if ( (EndColCoordMinusStartColCoord == 0) && (EndRowCoordMinusStartRowCoord > 0) ){
            return "NORTH";
        }
        else if ( (EndColCoordMinusStartColCoord == 0) && (EndRowCoordMinusStartRowCoord < 0) ){
            return "SOUTH";
        }
        else if ((EndColCoordMinusStartColCoord > 0) && (EndRowCoordMinusStartRowCoord == 0)){
            return "EAST";
        }
        else if ((EndColCoordMinusStartColCoord < 0) && (EndRowCoordMinusStartRowCoord == 0)){
            return "WEST";
        }
    }

    /**
     * This worker method checks if there is a barrier blocking the intended direction.
     * @param startingSquare
     * @param endingSquare
     * @return
     */
    private boolean isThereABarrierBlockingIntendedDirection(Square startingSquare, Square endingSquare) {
        String direction = returnIntendedDirection(startingSquare, endingSquare);

        if (direction.equals("NORTH") && (startingSquare.doesSquareHaveNorthEdgeBarrier() == true)) {
            return false;
        } else if (direction.equals("SOUTH") && (startingSquare.doesSquareHaveSouthEdgeBarrier() == true)) {
            return false;
        } else if (direction.equals("EAST") && (startingSquare.doesSquareHaveEastEdgeBarrier() == true)) {
            return false;
        } else if (direction.equals("WEST") && (startingSquare.doesSquareHaveWestEdgeBarrier() == true)) {
            return false;
        } else {
            return true;
        }

    }



        /**
         *
         */
        private boolean isDirectionPerpendicularToBarrierOnSquareWeAreOn(String intendedDirection, Square squareWeAreOn){
            if (intendedDirection.equals("NORTH") {
                if (squareWeAreOn.doesSquareHaveEastEdgeBarrier() || squareWeAreOn.doesSquareHaveWestEdgeBarrier()){
                    return true;
            }

        }



    }


    /*
        //First calculate the shortest path from robot1 to the target tile on this gameboard.
        //This uses's Disjksra's algorithm (shortest path to target tile on this gameboard)


        //First get all the squares on the gameboard into an array by adding them starting in top left corner
        //and going from left to right, row by row.
        //Square [] squareArray = theCurrentSimpleGameBoard.getArrayOfAllSquaresRowByRow();

        //Within this  whole board, set all nodes to not visited, and set all distances to infinity by writing -1.
        for (int row = 0; row < 15; row++){
            for (int column = 0; column < 15; column++){
                theCurrentSimpleGameBoard.getSquareAtRowColumnLocation(row, column).setSquareToUnvisted();
                theCurrentSimpleGameBoard.getSquareAtRowColumnLocation(row,column).changeActualDistanceValueForDA(-1);
            }
        }


        //Our initial node right now is the player1 robot, robot1. Set the square this robot sits on to a distance
        //value of 0.


        //First get the robot's location.
        int[]robot1Location = robot1.getCurrentRowColumnLocation();//Fix later.
        Square robotsSquare = theCurrentSimpleGameBoard.getSquareAtRowColumnLocation(robot1Location[0], robot1Location[1]);

        robotsSquare.changeActualDistanceValueForDA(0);
        for (int i = 0; i < squareArray.length; i++){
            if ( robot1Location.equals(squareArray[i].getSquaresRowColumnLocation()) ) { //TODO check this.
                squareArray[i].changeDistanceValueForDA(0);
                robotsSquare = squareArray[i];
            }
        }

        //Make a hashset for visited squares, and make a set for unvisited squares, both of which are first empty.

        HashSet<Square> unvistedSquaresHashSet = new HashSet<Square>();
        HashSet<Square> visitedSquaresHashSet = new HashSet<Square>();

        //Add all the unvisited squares to the unvisitedSquaresHashSet

        for (int row = 0; row < 15; row++){
            for (int column = 0; column < 15; column++){
                unvistedSquaresHashSet.add(theCurrentSimpleGameBoard.getSquareAtRowColumnLocation(row, column));
            }
        }

        //Now find the robot square in this hash set, and set it to



        //Now, check the four north, south, east, west squares adjacent to the current square, and assign
        //a preliminary distance value. Remember that we need to check if there's a barrier. If there is, we can't get
        //to the square, so the distance would be infinite.

        //First check the north square if there is no barrier on the north edge of the square. If there is, we can't
        //go there.

        if (robotsSquare.doesSquareHaveNorthEdgeBarrier() == false){
            if (theCurrentSimpleGameBoard.getSquareAtRowColumnLocation())
            theCurrentSimpleGameBoard.getSquareAtRowColumnLocation()

        }





        //First set all squares as unvisited
        //



*/

    /*



     */

    /**
     * This method recieves an array of squares clicked in the order the player from start to every time it
     * richochet, until it hit the target.
     * @return
     */
    public Square[] getFullPathFromPlayersFewClicks(Square [] playersClickedSquares, Gameboard theGameboard){
        //First determine
    }

}
