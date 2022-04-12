package com.mygdx.bombdif;

public abstract class ChallengeEasy extends Challenge{
    private int countdown;
    public ChallengeEasy(){
        super();
        countdown = 20;
        /*challeng chosen are tap flip or shake
        30 sec
         */
    }

    @Override
    public int getCountdown() {
        return countdown;
    }

}
