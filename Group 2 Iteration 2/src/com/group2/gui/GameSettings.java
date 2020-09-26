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

}
