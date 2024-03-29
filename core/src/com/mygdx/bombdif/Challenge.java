package com.mygdx.bombdif;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public abstract class Challenge {
    private boolean done;
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
    public abstract void updateState();

    public abstract int getPick();
    public abstract String getId();

    public abstract float[] currentZone(float azimuth);
}
