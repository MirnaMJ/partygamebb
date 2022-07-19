package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Shakey extends Challenge{
    private String instruction;
    public Shakey(Language lan){
        super();
        instruction = lan.getInstrucShake();
        float accelX = Gdx.input.getAccelerometerX();
        float accelY = Gdx.input.getAccelerometerY();
        float accelZ = Gdx.input.getAccelerometerZ();
    }

    @Override
    public String getInstruc() {
        return instruction;
    }

    @Override
    public boolean updateState(int flag) {
        if (flag==2){
            this.setDone();
            return true;
        }return false;
    }

    public boolean checkShake(){
        return true;
    }
}
