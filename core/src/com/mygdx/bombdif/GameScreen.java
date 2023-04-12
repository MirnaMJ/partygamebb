package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.concurrent.TimeUnit;

public class GameScreen implements Screen, GestureDetector.GestureListener {
    private Stage stage;
    private Table table;
    private Label timerL;

    final Bombdife game;
    private OrthographicCamera camera;
    private ExtendViewport viewport;
    private Language language;
    private CustomUiBdf customUi;
    private Bomb bomb;
    private Stack stack;
    private Container container;
    private Label consigne;
    private Window window;
    private TextureRegion currentFrame;
    private String[] challenges;
    private float stateTime;
    private float secTime;
    private Chronom chrono;
    //private boolean display=true;
    private Image bombI;
    private float[] bombColor;
    private Label inst;
    private Challenge prompt;
    //private ImageButton inputSpace;
    private int inputFlag;
    private float dragSensitivity= 20;
    //private Timer.Task timer;
    private FloatingText bonus;
    private Sound tickingSound;
    //private Sound tockingSound;
    private Sound boomSound;
    private boolean vibe;
    private int miss = 0;
    private float tLastShake=0;
    private float az;
    private boolean tap=false;
    private boolean shake=false;
    private boolean swipe=false;

    /*
    debugging purposes cursed compass
    */
    private Label curpos;
    private Label goal;
    private Table table0;
    private String dir="na";
    private int zone=99;
    private int[][] zones;


    public GameScreen(Bombdife game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480,800);//Gdx.graphics.getWidth(), Gdx.graphics.getHeight()
        viewport = new ExtendViewport(camera.viewportWidth, camera.viewportHeight, camera);

        language = game.getLanguage();

        vibe = game.getPrefs().getBoolean("vibe");

        tickingSound = Gdx.audio.newSound(Gdx.files.internal("tick.wav"));
        //tockingSound = Gdx.audio.newSound(Gdx.files.internal("tick2.wav"));
        boomSound = Gdx.audio.newSound(Gdx.files.internal("atari_boom6.wav"));

        bomb = new Bomb();
        customUi = new CustomUiBdf(game);
        inputFlag = 0;
        challenges = game.getRules().getChallenge();
        selecChallenge();

        chrono = new Chronom(game.getRules().getCountdown());
        /*timer = Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() {
                               if (display) {
                                   display = chrono.updateTimer();
                                   timerL.setText(chrono.display());
                               }else {
                                   System.out.println("else of chronoin time.scedule");
                                   timer.cancel();
                               }
                           }
                       }
                , 1        //    (delay)
                , 1f     //    (seconds)
        );*/


        //ta
        InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(this);
        stage = new Stage();

        im.addProcessor(gd);
        im.addProcessor(stage);

        Gdx.input.setInputProcessor(im);

        stack = new Stack();
        stage.addActor(stack);
        stack.setFillParent(true);


        //inputSpace = customUi.createButton("back");
        //inputSpace.addListener(new InputListener(){});


        table = new Table();
        //table.debug();
        table.row();

        timerL = customUi.createLabel(100,chrono.display());
        table.add(timerL).top().padTop(50);//.expand().top().pad(20)

        table.row();
        bombI = bomb.getiBomb();
        table.add(bombI).expand().fill();//.padLeft(100).padRight(100)

        table.row();
        //SWText = customUi.createSWText(text);
        long animationDuration = TimeUnit.SECONDS.toMillis(1);
        BitmapFont fontw = game.getFont80();
        bonus = new FloatingText(fontw,"+1s",animationDuration);
        bonus.setAs("bonus");
        //bonus.setPosition(10, 10);

        bonus.setDeltaY(400);
        table.add(bonus);

        table.row();
        consigne = customUi.createLabel(40, prompt.getInstruc());
        table.add(consigne).expand();//.fill()


        //First add actual content
        stack.add(table);
        //Second add wrapped overlay input pac
        table0 = new Table();
        //table0.debug();
        table0.row();
        goal = customUi.createLabel(30,"pick : "+prompt.getPick());
        table0.add(goal).top().left();

        table0.row();
        curpos = customUi.createLabel(30,"I'm at "+dir+" = "+zone+", az="+Gdx.input.getAzimuth());
        table0.add(curpos).top().left();

        container = new Container(table0).fill().bottom().left();
        //container.add();
        stack.add(container);


        /*Vector2 coords = new Vector2(bonus.getX(), bonus.getY());
        bonus.localToStageCoordinates(/*in/outcoords);
        bonus.getStage().stageToScreenCoordinates(/*in/outcoords);
        posYbonus=coords.y;*/

        for (int i=0;i< challenges.length;i++){
            if (challenges[i].equals("tap")){
                tap=true;
            }
            if (challenges[i].equals("swipe")){
                swipe=true;
            }
            if (challenges[i].equals("shake")){
                shake=true;
            }
        }

        // time to 0
        stateTime = 0;
        //check if im at the next s3c
        secTime = 0;
        Gdx.graphics.setContinuousRendering(true);
    }

    @Override
    public void show() {
        game.menuMusic.pause();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.1f, 1);
        camera.update();

        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        //currentFrame = tickingbomb.getKeyFrame(stateTime, true);

        game.getBatch().setProjectionMatrix(camera.combined);
        /*game.getBatch().begin();
        game.getFont80().draw(game.getBatch(), chrono.display(), camera.viewportWidth/6, camera.viewportHeight*7/8);
        game.getBatch().draw(currentFrame, 10, 10,camera.viewportWidth-20,camera.viewportWidth); // Draw current frame at (x, y)
        game.getBatch().end()*/

        //animation bomb, count seconds
        if (!(chrono.ended()) && stateTime-secTime >= 1){
            if (!(prompt.getId().equals("compass"))){
                bomb.tick();
            }
            chrono.updateTimer();
            timerL.setText(chrono.display());
            tickingSound.play(game.getPrefs().getFloat("volumeS"));
            //System.out.println("amcrn: x ptc, y ro, z azmut: " + Gdx.input.getPitch() +" "+Gdx.input.getRoll()+" "+ Gdx.input.getAzimuth());

        }

        //gameloop
        //if (prompt.checkShake()){
            if ((Math.pow(Gdx.input.getAccelerometerX(),2)+Math.pow(Gdx.input.getAccelerometerY(),2)+Math.pow(Gdx.input.getAccelerometerZ(),2)) > 180){

                /*(Math.abs(Gdx.input.getAccelerometerX())>10 && Math.abs(Gdx.input.getAccelerometerX())<20) ||
                    ((Gdx.input.getAccelerometerZ()>-20 && Gdx.input.getAccelerometerZ()<-5) ||
                            (Gdx.input.getAccelerometerZ()>14 && Gdx.input.getAccelerometerZ()<20))*/


                /*Gdx.input.getAccelerometerZ()>11 || Gdx.input.getAccelerometerZ()<-10.5
                *
              *
                    ((Gdx.input.getAccelerometerZ()>-20 && Gdx.input.getAccelerometerZ()<-5) ||
                    (Gdx.input.getAccelerometerZ()>11 && Gdx.input.getAccelerometerZ()<20)) */
                //inputFlag = 2;
                //prompt.updateState(inputFlag);

                if ((stateTime-tLastShake >= 0.3)) {
                    tLastShake = stateTime;
                    if (prompt.getId().equals("shake")) {
                        prompt.updateState();
                    } else if (shake){
                        System.out.println("shouldnt shake");
                        System.out.println("x: " + Gdx.input.getAccelerometerX());
                        System.out.println("z: " + Gdx.input.getAccelerometerZ());
                        System.out.println("abovz: " + (((Gdx.input.getAccelerometerZ() > -20 && Gdx.input.getAccelerometerZ() < -6.1) ||
                                (Gdx.input.getAccelerometerZ() > 14 && Gdx.input.getAccelerometerZ() < 20))));

                        bonus.setAs("malus");
                        bonus.animate();
                        miss += 1;
                        chrono.updateTimer();
                        chrono.updateTimer();//take a second out when mistake made
                        if (vibe) {
                            Gdx.input.vibrate(150);
                        }
                    }
                }

            }

        //}


        if (prompt.getId().equals("compass")){
            prompt.updateState();
            if (!(prompt.isDone())){
                az = Gdx.input.getAzimuth();
                bombColor = prompt.currentZone(az);
                bomb.setBombColor(bombColor[0],bombColor[1],bombColor[2]);
                //System.out.println("hy is nothing happening on color bomb "+ bombColor[1]);
            }
            //debug compass
            checkComp();
            goal.setText("pick : "+prompt.getPick());
            curpos.setText("I'm at "+dir+" = "+zone+", az="+az);
            //debug comp
        }


        stage.draw();

        if (chrono.ended()){
            System.out.println("gamescreen: chrono rallonge de: "+(int)(stateTime-game.getRules().getCountdown())+"s");
            boomSound.play(game.getPrefs().getFloat("volumeBS"));
            if (vibe) {
                Gdx.input.vibrate(250);
            }

            if(stateTime-game.getRules().getCountdown()<0){
                game.getRules().setScore(0);
            }else{
                game.getRules().setScore((int)(stateTime-game.getRules().getCountdown()));
            }
            game.getRules().setMiss(miss);
            game.setScreen(new TransitionGameEndScreen(game,prompt.getInstruc()));
            dispose();
        }else{
            if (prompt.isDone()){
                //if (!bonus.isAnimated()) {
                bonus.setAs("bonus");
                bonus.animate();
                //tockingSound.play(game.getPrefs().getFloat("volumeS"));
                //}
                stage.act();
                stage.draw();
                selecChallenge();
                consigne.setText(prompt.getInstruc());
                chrono.bonusSec(1);
                timerL.setText(chrono.display());

            }
        }
        if (stateTime-secTime >= 1){
            secTime = stateTime;

        }
    }


    private void selecChallenge(){
        int choice = (int) (Math.random() * challenges.length);//Min + (int)(Math.random() * ((Max - Min) + 1))
        inputFlag = choice+1;
        System.out.println("gamescreen: choice "+choice);
        if (challenges[choice].equals("tap")) {
            prompt = new Tapey(language);
        }else if (challenges[choice].equals("swipe")) {
            prompt = new Swipey(language);
        }else if (challenges[choice].equals("shake")) {
            prompt = new Shakey(language);
        }else if (challenges[choice].equals("pointTo")){
            prompt = new Compassey(language);
        }
        else{
            prompt = new Tapey(language);
            System.out.println("gamescreen: nothing was selected");

        }
    }

    private void checkComp(){
        zones = new int[][]{{-22, 22}, {22, 67}, {67, 112}, {112, 158}, {-158, 158}, {-158, -112}, {-112, -67}, {-67, -22}};
        int i =0;
        zone = 99;
        while(zone==99) {
            //System.out.println("compassey: zones(i) ["+zones[i][0]+","+zones[i][1]+"] "+i);
            //System.out.println("compassey: azimuth: "+az);
            if (Math.abs(az) >= 158) {
                zone = 4;
                //System.out.println("compassey: is it south? "+az);
            } else if (zones[i][0] <= az && az <= zones[i][1] && i != 4) {
                zone = i;
                //System.out.println("compassey: current zone "+i+" "+zones[i][0]+" , "+zones[i][1]+" zones[i]");
            }
            i++;
        }
        switch (zone) {
            case 0:
                dir = "N";
                break;
            case 1:
                dir = "NE";
                break;
            case 2:
                dir = "E";
                break;
            case 3:
                dir = "SE";
                break;
            case 4:
                dir = "S";
                break;
            case 5:
                dir = "SO";
                break;
            case 6:
                dir = "O";
                break;
            case 7:
                dir = "NO";
                break;
            default:
                System.out.println("compay: number picked wasnt  between 0 znd 7");
                break;
        }
    }



    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (prompt.getId().equals("tap")){
            prompt.updateState();
        }else if (tap){
            System.out.println("shouldnt tap");
            bonus.setAs("malus");
            bonus.animate();
            miss+=1;
            chrono.updateTimer();
            chrono.updateTimer();//take a second out when mistake made
            if (vibe) {
                Gdx.input.vibrate(150);
            }
        }
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (prompt.getId().equals("swipe")){
            prompt.updateState();
        }else if (swipe){
            System.out.println("dont swipe");
            bonus.setAs("malus");
            bonus.animate();
            miss+=1;
            chrono.updateTimer();
            chrono.updateTimer();//take a second out when mistake made
            if (vibe) {
                Gdx.input.vibrate(150);
            }
        }
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }




    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        tickingSound.dispose();
        //tockingSound.dispose();
        boomSound.dispose();
    }
}
