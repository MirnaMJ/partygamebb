package com.mygdx.bombdif;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Shakey extends ChallengeEasy{
    private String instruction;
    public Shakey(Language lan){
        super();
        instruction = lan.getInstrucShake();
    }

    @Override
    public String getInstruc() {
        return instruction;
    }

    @Override
    public void updateState(int flag) {
int a = 2;
    }
}
