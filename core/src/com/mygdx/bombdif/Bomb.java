package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Bomb extends Actor {
    private Image iBomb;
    private Color tint;
    private String[] state;
    private String[] urgenceState;
    private int time_since_last_tick;
    private Animation<TextureRegion> tickingbomb;
    private TextureAtlas bombdife;
    private Skin skin;

    public Bomb(){
        bombdife = new TextureAtlas(Gdx.files.internal("bombdif.atlas"));
        skin = new Skin();
        skin.addRegions(bombdife);
        tint = new Color(1f, 0.4f, 0.4f, 1f);
        //dBomb = skin.getDrawable("Bomb_in_game_back");
        //iBomb = new Image(dBomb);
        iBomb = new Image(bombdife.findRegion("Boomb_in_game"));

        state = new String[3];
        state[0]="";
        state[1]="";
        state[2]="";
        urgenceState  = new String[3];
        urgenceState[0]="";
        urgenceState[1]="";
        urgenceState[2]="";

        time_since_last_tick = 0;
        iBomb.setColor(0.2f,0.8f,1f,1f);
        /*
         *
         * Animation
         *
         */

        tickingbomb = new Animation<TextureRegion>(2f, bombdife.findRegions("bombe"), Animation.PlayMode.LOOP);

    }

    public void tick(){
        if (time_since_last_tick <2) {
            time_since_last_tick += 1;
            iBomb.setColor(0.2f,0.8f,1f,1f);
        }else{
            time_since_last_tick = 0;
            iBomb.setColor(tint);
        }

    }

    public Image getiBomb() {
        return iBomb;
    }

    public void setBombColor(float r, float g, float b){
        iBomb.setColor(r,g,b,1f);
    }


    public Animation<TextureRegion> getTickingbomb() {
        return tickingbomb;
    }
    /*
            tic play ound, put rd tint on
            tac: t rid of tint
            cool: tim_inc_lat_tic 5 <10
            urnt : tim_inc_lat_tic 2 5-10
            imminnt: tim 1 <5

            drawabl
    put a rd tinit ovr tit for t duration of tictac
            function ticin bomb bomb, int tim ticin
    bomb.updattat timticin
    bomb.act

if losing bomb sound, vibration, red screen else nice sound no vibration

tick tick every seconds or playing on loop and cut by nice sound when 30seconds/1 mn was added
    */
}
