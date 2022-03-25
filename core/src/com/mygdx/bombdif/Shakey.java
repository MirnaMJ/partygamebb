package com.mygdx.bombdif;

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
}
