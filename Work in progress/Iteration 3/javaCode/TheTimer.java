//package com.group2.gameplayrules;

public class TheTimer {

    //int timer;
    int timeRemaining;
    boolean hasBiddingTimeStopped;

    /**
     * This object is created every time the a player makes the first bid in a new round of bidding.
     */

    public TheTimer() {
        this.timeRemaining = 17;
        this.hasBiddingTimeStopped = false;


    }

    private void countdownTheTimerAndChangeIfTimeStopped() {
        while (this.timeRemaining != 0) {
            //delay for 1 second
            this.timeRemaining -= 1;
        }
        this.hasBiddingTimeStopped = true;
    }

    public void startTime() {
        this.timeRemaining = 17;
        this.hasBiddingTimeStopped = false;
        countdownTheTimerAndChangeIfTimeStopped();
    }

    public String getTimeRemaining() {
        return String.valueOf(timeRemaining);
    }

    public void setTimeRemaining() {

    }

    public boolean hasBiddingTimeStopped() {
        if (this.timeRemaining == 0) {
            return true;
        } else {
            return false;
        }
    }


}



