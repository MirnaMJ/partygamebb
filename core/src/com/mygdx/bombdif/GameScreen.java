package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

public class GameScreen implements Screen {
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
    private boolean display=true;
    private Image bombI;
    private Label inst;
    private Challenge prompt;
    private ImageButton inputSpace;
    private int inputFlag;
    private float dragSensitivity= 20;
    private float posX;
    private float posY;

    public GameScreen(Bombdife game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);

        language = game.getLanguage();

        bomb = new Bomb();
        customUi = new CustomUiBdf(game);
        challenges = game.getRules().getChallenge();
        selecChallenge();

        chrono = new Chronom(game.getRules().getCountdown());
        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() {
                               if (display) {
                                   display = chrono.updateTimer();
                                   timerL.setText(chrono.display());
                               }
                           }
                       }
                , 1        //    (delay)
                , 1f     //    (seconds)
        );


        //ta
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        stack = new Stack();
        stage.addActor(stack);
        stack.setFillParent(true);

        inputSpace = customUi.createButton("back");
        inputFlag = 0;
        inputSpace.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if (Math.abs(posX-x)>dragSensitivity || Math.abs(posY-y)>dragSensitivity) {
                    //System.out.println("gamescreen: drag  , "+x+" , "+y+", "+pointer);
                    prompt.updateState(inputFlag);
                    inputFlag=0;
                }else {
                    //System.out.println("gamescreen: jut touch  , "+x+" , "+y+", "+pointer);
                    inputFlag=0;
                    prompt.updateState(inputFlag);
                }
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("gamescreen:   , "+x+" , "+y+", "+pointer);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                if (inputFlag==0){
                    posX = x;
                    posY = y;
                }
                inputFlag = 1;

            }
        });


        table = new Table();
        //table.debug();
        table.row();

        timerL = customUi.createLabel(80,chrono.display());
        table.add(timerL).top().padTop(50);//.expand().top().pad(20)

        table.row();
        bombI = bomb.getiBomb();
        table.add(bombI).expand().fill();//.padLeft(100).padRight(100)

        table.row();
        consigne = customUi.createLabel(40, prompt.getInstruc());
        table.add(consigne).expand();//.fill()


        //First add actual content
        stack.add(table);
        //Second add wrapped overlay input pac
        container = new Container(inputSpace).fill().bottom().left();
        //container.add();
        stack.add(container);




        // time to 0
        stateTime = 0;
        //check if im at the next s3c
        secTime = 0;

    }

    @Override
    public void show() {

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

        //animation bomb
        if (chrono.getSec()!= secTime){
            bomb.tick(chrono.getSec());
        }

        //gameloop
        if (prompt.checkShake()){
            if ((Math.abs(Gdx.input.getAccelerometerX())>10 && Math.abs(Gdx.input.getAccelerometerX())<20) ||
                    ((Gdx.input.getAccelerometerZ()>-20 && Gdx.input.getAccelerometerZ()<-5) ||
                    (Gdx.input.getAccelerometerZ()>14 && Gdx.input.getAccelerometerZ()<20))){
                /*
                    ((Gdx.input.getAccelerometerZ()>-20 && Gdx.input.getAccelerometerZ()<-5) ||
                    (Gdx.input.getAccelerometerZ()>11 && Gdx.input.getAccelerometerZ()<20)) */
                inputFlag = 2;
                prompt.updateState(inputFlag);
                System.out.println(Math.abs(Gdx.input.getAccelerometerX())>10);
                System.out.println(Math.abs(Gdx.input.getAccelerometerX())<20);
                System.out.println("x: "+Gdx.input.getAccelerometerX());
                System.out.println((Gdx.input.getAccelerometerZ()>-20 && Gdx.input.getAccelerometerZ()<-5));
                System.out.println((Gdx.input.getAccelerometerZ()>14 && Gdx.input.getAccelerometerZ()<20));
                System.out.println("z: "+Gdx.input.getAccelerometerZ());
            }
        }
        if (prompt.isDone()){
            selecChallenge();
            consigne.setText(prompt.getInstruc());
            chrono.bonusSec(1);
        }
        secTime = chrono.getSec();

        stage.draw();

        if (chrono.ended()){
            System.out.println("gamescreen: chrono rallonge de: "+(int)(stateTime-game.getRules().getCountdown())+"s");
            game.getRules().setScore((int)(stateTime-game.getRules().getCountdown()));
            game.setScreen(new EndGameScreen(game));
            dispose();

        }
    }

    private void selecChallenge(){
        int choice = (int) (Math.random() * challenges.length);//Min + (int)(Math.random() * ((Max - Min) + 1))
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
    }
}
