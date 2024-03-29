package com.mygdx.bombdif;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Swipey extends Challenge{
    private String instruction;
    private String id;

    public Swipey(Language lang){
        super();
        instruction = lang.getInstrucSwipe();
        id = "swipe";
        /*swipe = new InputListener(){

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                System.out.println("Swipy: ttest");
                updateState();
            }
        };*/
    }

    public String getInstruc() {
        return instruction;
    }

    //public InputListener getInputListener() {
    //    return swipe;}

    public void updateState() {
        this.setDone();
    }

    @Override
    public int getPick() {
        return 99;
    }

    public String getId() {
        return id;
    }

    @Override
    public float[] currentZone(float azimuth) {
        return new float[0];
    }

}
