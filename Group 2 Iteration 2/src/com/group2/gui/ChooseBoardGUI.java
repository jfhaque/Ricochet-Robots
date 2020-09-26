package com.group2.gui;

public class ChooseBoardGUI {

    private com.group2.gui.GameSettings newGameSettings;

    public ChooseBoardGUI(){
        newGameSettings = new GameSettings();
    }

    public GameSettings getGameSettingsObject(){
        return this.newGameSettings;
    }
}