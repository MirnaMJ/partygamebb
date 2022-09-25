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
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.concurrent.TimeUnit;

public class GameScreen implements Screen, GestureDetector.GestureListener {
    private Stage stage;
    private Table table;
    private Label timerL;

    final Bombdife game;
    private OrthographicCamera camera;
    private FitViewport viewport;
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
    private Label inst;
    private Challenge prompt;
    private ImageButton inputSpace;
    private int inputFlag;
    private float dragSensitivity= 20;
    //private Timer.Task timer;
    private FloatingText bonus;
    private Sound tickingSound;
    private Sound tockingSound;
    private Sound boomSound;


    public GameScreen(Bombdife game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);

        language = game.getLanguage();

        tickingSound = Gdx.audio.newSound(Gdx.files.internal("tick.wav"));
        tockingSound = Gdx.audio.newSound(Gdx.files.internal("tick2.wav"));
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
        /*
        *no task =0
        * tap=1
        * swipe=2
        * shake=3
        *


         */
        //inputSpace.addListener(new InputListener(){});


        table = new Table();
        //table.debug();
        table.row();

        timerL = customUi.createLabel(80,chrono.display());
        table.add(timerL).top().padTop(50);//.expand().top().pad(20)

        table.row();
        bombI = bomb.getiBomb();
        table.add(bombI).expand().fill();//.padLeft(100).padRight(100)

        table.row();
        //SWText = customUi.createSWText(text);
        long animationDuration = TimeUnit.SECONDS.toMillis(1);
        BitmapFont fontw = game.getFont40();
        bonus = new FloatingText(fontw,"+1s",animationDuration);
        //bonus.setPosition(10, 10);

        bonus.setDeltaY(400);
        table.add(bonus);

        table.row();
        consigne = customUi.createLabel(40, prompt.getInstruc());
        table.add(consigne).expand();//.fill()


        //First add actual content
        stack.add(table);
        //Second add wrapped overlay input pac
        container = new Container(inputSpace).fill().bottom().left();
        //container.add();
        stack.add(container);


        /*Vector2 coords = new Vector2(bonus.getX(), bonus.getY());
        bonus.localToStageCoordinates(/*in/outcoords);
        bonus.getStage().stageToScreenCoordinates(/*in/outcoords);
        posYbonus=coords.y;*/


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
            bomb.tick();
            chrono.updateTimer();
            timerL.setText(chrono.display());
            tickingSound.play(game.getPrefs().getFloat("volumeS"));

        }

        //gameloop
        //if (prompt.checkShake()){
            if ((Math.abs(Gdx.input.getAccelerometerX())>10 && Math.abs(Gdx.input.getAccelerometerX())<20) ||
                    ((Gdx.input.getAccelerometerZ()>-20 && Gdx.input.getAccelerometerZ()<-5) ||
                            (Gdx.input.getAccelerometerZ()>14 && Gdx.input.getAccelerometerZ()<20))){

                /**/


                /*Gdx.input.getAccelerometerZ()>11 || Gdx.input.getAccelerometerZ()<-10.5
                *
              *
                    ((Gdx.input.getAccelerometerZ()>-20 && Gdx.input.getAccelerometerZ()<-5) ||
                    (Gdx.input.getAccelerometerZ()>11 && Gdx.input.getAccelerometerZ()<20)) */
                //inputFlag = 2;
                //prompt.updateState(inputFlag);

                if (prompt.getId().equals("shake")) {
                    prompt.updateState();
                }else {
                    System.out.println("shouldnt shake");
                    System.out.println("z: " + Gdx.input.getAccelerometerZ());
                    System.out.println("abovz: " + (Gdx.input.getAccelerometerZ() > 11.65 || Gdx.input.getAccelerometerZ() < -10.5));
                    Gdx.input.vibrate(150);
                }

            }

        //}



        stage.draw();

        if (chrono.ended()){
            System.out.println("gamescreen: chrono rallonge de: "+(int)(stateTime-game.getRules().getCountdown())+"s");
            boomSound.play(game.getPrefs().getFloat("volumeS"));
            Gdx.input.vibrate(250);
            game.getRules().setScore((int)(stateTime-game.getRules().getCountdown()));
            game.setScreen(new EndGameScreen(game));
            //timer.cancel();
            dispose();
        }else{
            if (prompt.isDone()){
                //if (!bonus.isAnimated()) {
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
        }else{
            prompt = new Tapey(language);
            System.out.println("gamescreen: nothing was selected");

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
        }else{
            System.out.println("shouldnt tap");
            Gdx.input.vibrate(150);
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
        }else{
            System.out.println("dont swipe");
            Gdx.input.vibrate(150);
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
        tockingSound.dispose();
        boomSound.dispose();
    }
}
