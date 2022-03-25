package com.mygdx.bombdif;

public abstract class Challenge {
    private boolean done;
    private String instruc;
    public Challenge(){
        done = false;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone() {
        done = true;
    }

    public abstract String getInstruc();


}
