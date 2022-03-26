package com.mygdx.bombdif;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Swipey extends ChallengeEasy{
    private String instruction;
    private InputListener swipe;

    public Swipey(Language lang){
        super();
        instruction = lang.getInstrucShake();
        swipe = new InputListener(){

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                //super.touchDragged(event, x, y, pointer);
                System.out.println("ttest");
                updateState();
            }
        };
    }

    public String getInstruc() {
        return instruction;
    }

    public InputListener getSwipe() {
        return swipe;
    }

    public void updateState() {
        this.setDone();
    }
}
