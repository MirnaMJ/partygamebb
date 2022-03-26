package com.mygdx.bombdif;

public abstract class ChallengeEasy extends Challenge{
    private int countdown;
    public ChallengeEasy(){
        super();
        countdown = 30;
        /*challeng chosen are tap flip or shake
        30 sec
         */
    }

    public int getCountdown() {
        return countdown;
    }
}
