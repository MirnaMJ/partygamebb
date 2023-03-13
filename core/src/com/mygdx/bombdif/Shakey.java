package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Shakey extends Challenge{
    private String instruction;
    private String id;
    public Shakey(Language lan){
        super();
        instruction = lan.getInstrucShake();
        id = "shake";
        float accelX = Gdx.input.getAccelerometerX();
        float accelY = Gdx.input.getAccelerometerY();
        float accelZ = Gdx.input.getAccelerometerZ();
    }

    @Override
    public String getInstruc() {
        return instruction;
    }

    @Override
    public void updateState() {
            this.setDone();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public float[] currentZone(float azimuth) {
        return new float[0];
    }

}
