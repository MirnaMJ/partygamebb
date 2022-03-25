package com.mygdx.bombdif;

public class Tapey extends ChallengeEasy{
    private String[] instruction;
    private int iter;
    private int step;
    public Tapey(Language lang){
        super();
        instruction = new String[2];
        instruction[0] = lang.getInstrucTap();
        instruction[1] = lang.getnTimes();
        step = 0;
        iter = 1 + (int)(Math.random() * 5);//Min + (int)(Math.random() * ((Max - Min) + 1))
    }

    public String getInstruc(){
        return instruction[0]+iter+instruction[1];
    }

    public int getIter() {
        return iter;
    }

    public void updateStep() {
        step+=1;
        if (iter == step){
            this.setDone();
        }
    }
}
