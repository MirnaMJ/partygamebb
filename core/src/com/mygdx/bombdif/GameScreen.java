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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
    private Animation<TextureRegion> tickingbomb;
    private TextureRegion currentFrame;
    private float stateTime;
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
        //tickingbomb = bomb.getTickingbomb();

        /*int choice = (int) (Math.random() * 1);//Min + (int)(Math.random() * ((Max - Min) + 1))
        if (game.getRules().getDifficulty().equals(language.getEasy())){
            switch(choice){
                case 0:
                    prompt = new Swipey(language);
                    System.out.println("gamescreen: prompt 0");
                    break;
                default:
                    prompt = new Tapey(language);
                    System.out.println("gamescreen: prompt d3fault");
                    break;
            }

        }else {
            prompt = new Tapey(language);
            System.out.println("gamescreen: if rules jot easy ");
        }*/
        selecChallenge();

        chrono = new Chronom(prompt.getCountdown());
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

        table = new Table();
        //table.debug();
        stage.addActor(table);
        table.setFillParent(true);

        table.row();

        timerL = customUi.createLabel(80,chrono.display());
        table.add(timerL).top().padTop(50);//.expand().top().pad(20)

        table.row();
        bombI = bomb.getiBomb();
        table.add(bombI).expand().fill().padLeft(120).padRight(120);//

        table.row();

        inputFlag = 0;
        inputSpace = customUi.createButton("back");
        //
        //touch down
        //touch dragged keep where the drag started to be detected
        // touch up look how long the drag was and if iwas long enough will consider it a drag
        // tap is flag =0
        // drag is flag=1
        inputSpace.addListener( new InputListener(){
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

        table.add(inputSpace).fill().expand();

        table.row();

        inst = customUi.createLabel(40, prompt.getInstruc());
        table.add(inst).padBottom(10);


        // time to 0
        stateTime = 0;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.1f, 1);
        camera.update();

        //stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        //currentFrame = tickingbomb.getKeyFrame(stateTime, true);

        game.getBatch().setProjectionMatrix(camera.combined);
        /*game.getBatch().begin();
        game.getFont80().draw(game.getBatch(), chrono.display(), camera.viewportWidth/6, camera.viewportHeight*7/8);
        game.getBatch().draw(currentFrame, 10, 10,camera.viewportWidth-20,camera.viewportWidth); // Draw current frame at (x, y)
        game.getBatch().end()*/

        //animation bomb
        if (chrono.getSec()!= stateTime){
            bomb.tick(chrono.getSec());
        }

        //gameloop
        if (prompt.isDone()){
            selecChallenge();
            inst.setText(prompt.getInstruc());
            chrono.bonusSec(5);
        }
        stateTime = chrono.getSec();

        stage.draw();

    }

    private void selecChallenge(){
        int choice = (int) (Math.random() * 2);//Min + (int)(Math.random() * ((Max - Min) + 1))
        if (game.getRules().getDifficulty().equals(language.getEasy())){
            switch(choice){
                case 0:
                    prompt = new Swipey(language);
                    System.out.println("gamescreen: prompt 0");
                    break;
                default:
                    prompt = new Tapey(language);
                    System.out.println("gamescreen: prompt default");
                    break;
            }

        }else {
            prompt = new Tapey(language);
            System.out.println("gamescreen: if rules not easy ");
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
        game.dispose();
        stage.dispose();
    }
}
