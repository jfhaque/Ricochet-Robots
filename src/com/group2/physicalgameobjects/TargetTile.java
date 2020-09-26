package com.group2.physicalgameobjects;

import com.group2.physicalgameobjects.Color.*;
import com.group2.physicalgameobjects.Symbols.*;

/**
 * This class creates a target tile found on either a simple or complex board. It has a color and a shape.
 */

public class TargetTile {
    private Symbol symbol;
    private Color color;

    public TargetTile(Color newColor, Symbol newSymbol){
        this.color = newColor;
        this.symbol = newSymbol;
    }


    public Color getColor(){
        return this.color;
    }

    public Symbol getSymbol(){
        return this.symbol;
    }

//TODO May not need below
    public void addCircleSymbol(){
        this.symbol = new GearSymbol();
    }

    public void addSquareSymbol(){
        this.symbol = new SaturnSymbol();
    }

    public void addHalfMoonSymbol(){
        this.symbol = new CrescentMoonSymbol();
    }

    public void addStarSymbol(){
        this.symbol = new StarSymbol();
    }

    public void addRedColor{
        this.color = new RedColor();
    }

    public void addGreenColor(){
        this.color = new GreenColor();
    }

    public void addBlueColor(){
        this.color = new BlueColor();
    }

    public void addYellowColor(){
        this.color = new YellowColor();
    }

    public String getImageFileLocation(){

    }

    public setImageFileLocation(String imageFileLocation){

    }




}
