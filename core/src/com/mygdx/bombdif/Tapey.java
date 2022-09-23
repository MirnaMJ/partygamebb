package com.mygdx.bombdif;

public class Tapey extends Challenge{
    private String[] instruction;
    private int goal;
    private int step;
    private String id;
    //private InputListener tap;

    public Tapey(Language lang){
        super();
        instruction = new String[2];
        id = "tap";
        instruction[0] = lang.getInstrucTap();
        instruction[1] = lang.getnTimes();
        step = 0;
        goal = 1 + (int)(Math.random() * 5);//Min + (int)(Math.random() * ((Max - Min) + 1))
        /*tap = new InputListener(){1 a 5
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
        return instruction[0]+(goal -step)+instruction[1];
    }

    public void updateState() {
        step+=1;
        if (goal <= step){
            this.setDone();
        }
    }

    @Override
    public String getId() {
        return id;
    }

}
