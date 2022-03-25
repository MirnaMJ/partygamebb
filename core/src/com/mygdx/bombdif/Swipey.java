package com.mygdx.bombdif;

public class Swipey extends ChallengeEasy{
    private String instruction;
    public Swipey(Language lang){
        super();
        instruction = lang.getInstrucShake();
    }

    @Override
    public String getInstruc() {
        return instruction;
    }
}
