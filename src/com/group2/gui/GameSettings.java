package com.group2.gui;

public class GameSettings {
    private int boardType;
    private int difficulty;
    private int player1PlayerType;
    private int player2PlayerType;
    private int player3PlayerType;
    private int player4PlayerType;

    public GameSettings(){
        //These are the default settings.
        this.boardType = 0; //0 is simple board, 1 is complex board
        this.difficulty = 0; // 0 is easy mode, 1 is hard mode
        this.player1PlayerType = 0; //0 is Human, 1 is Computer
        this.player2PlayerType = 0; //0 is Human, 1 is Computer
        this.player3PlayerType = 0; //0 is Human, 1 is Computer
        this.player4PlayerType = 0; //0 is Human, 1 is Computer
    }



    public void setBoardType(int newBoardType){
        this.boardType = newBoardType;
    }

    public boolean boardTypeIsSimple(){
        if (this.boardType == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void setDifficulty(int newDifficulty){
        this.difficulty = newDifficulty;
    }

    public void setPlayer1PlayerType(int newPlayerType){
        this.player1PlayerType = newPlayerType;
    }

    public void setPlayer2PlayerType(int newPlayerType){
        this.player2PlayerType = newPlayerType;
    }
    public void setPlayer3PlayerType(int newPlayerType){
        this.player3PlayerType = newPlayerType;
    }
    public void setPlayer4PlayerType(int newPlayerType){
        this.player4PlayerType = newPlayerType;
    }

    //Getters //TODO Change integers to strings reading what board types and difficulties are.

    public boolean recordedBoardTypeIsSimple(){
        if (this.boardType == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean recordedAllComputerDifficultyIsEasy(){
        if (this.difficulty == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean player1PlayerTypeisHuman(){
        if (this.player1PlayerType == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean player2PlayerTypeisHuman(){
        if (this.player2PlayerType == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean player3PlayerTypeisHuman(){
        if (this.player3PlayerType == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean player4PlayerTypeisHuman(){
        if (this.player4PlayerType == 0){
            return true;
        }
        else{
            return false;
        }
    }

}
