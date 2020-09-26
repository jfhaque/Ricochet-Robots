package com.group2.physicalgameobjects;

/**
 * This class creates one of the 17 target chips. It has a color and shape.
 */
public class TargetChip {

    private String color;
    private String shape;
    private int location;

    public TargetChip(String newColor, String newShape){ //TODO I updated signature compared to class diagram.
        this.color = newColor;
        this.shape = newShape;
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String newColor){
        this.color = newColor;
    }

    public String getShape(){
        return this.shape;
    }

    public void setShape(String newShape){
        this.shape = newShape;
    }

    public String getImageFileLocation(){

    }

    public setImageFileLocation(String imageFileLocation){

    }



}
