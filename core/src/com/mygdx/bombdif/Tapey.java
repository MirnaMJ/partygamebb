package com.mygdx.bombdif;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Tapey extends Challenge{
    private String[] instruction;
    private int iter;
    private int step;
    //private InputListener tap;

    public Tapey(Language lang){
        super();
        instruction = new String[2];
        instruction[0] = lang.getInstrucTap();
        instruction[1] = lang.getnTimes();
        step = 0;
        iter = 1 + (int)(Math.random() * 5);//Min + (int)(Math.random() * ((Max - Min) + 1))
        /*tap = new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                updateState();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        };*/
    }

    //public InputListener getInputListener(){return tap;}

    public String getInstruc(){
        return instruction[0]+(iter-step)+instruction[1];
    }

    public boolean updateState(int flag ) {
        if (flag == 0){
            step+=1;
            if (iter == step){
                this.setDone();
                return true;
            }
        }return false;
    }

    public boolean checkShake(){
        return false;
    }
}
