package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class TransitionGameEndScreen  implements Screen {
    final Bombdife game;
    private Stage stage0;
    private Table table0;
    private OrthographicCamera camera;
    private ExtendViewport viewport;
    private Language language;
    private CustomUiBdf customUi;
    private Bomb bomb;
    private Label chrono;
    private Label consigne;
    private float stateTime;
    private float secTime;
    private Image bombI;

    private Stage stage1;
    private Table table1;
    private ImageButton back;
    private Label newHighscore;
    private Label newScore;
    private Label oldScore;
    private ImageButton retry;

    private ParticleEffect pe;

    public TransitionGameEndScreen(Bombdife game, String prompt){

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480,800);//Gdx.graphics.getWidth(), Gdx.graphics.getHeight()
        viewport = new ExtendViewport(camera.viewportWidth, camera.viewportHeight, camera);

        language = game.getLanguage();

        bomb = new Bomb();
        customUi = new CustomUiBdf(game);

        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("spread1.p"),Gdx.files.internal(""));
        pe.getEmitters().first().setPosition(Gdx.graphics.getWidth()/2,0);//Gdx.graphics.getHeight()/2
        pe.scaleEffect(25);
        pe.start();

        stage0 = new Stage();
        table0 = new Table();
        //table.debug();
        stage0.addActor(table0);
        table0.setFillParent(true);
        table0.row();

        chrono= customUi.createLabel(80, "00:00:00");
        table0.add(chrono).top().padTop(50);

        table0.row();
        bombI = bomb.getiBomb();
        table0.add(bombI).expand().fill();

        table0.row();
        consigne = customUi.createLabel(40, prompt);
        table0.add(consigne).expand();


        stage1 = new Stage();
        table1 = new Table();
        //table.debug();
        stage1.addActor(table1);
        table1.setFillParent(true);
        table1.row();
        table1.row();
        back = customUi.createButton( "arrow_r");
        back.setTransform(true);
        back.setOrigin(back.getWidth()/2, back.getHeight()/2);
        table1.add(back).top().left().expand();

        table1.row();
        newHighscore = customUi.createLabel(40, "");
        table1.add(newHighscore).expand();

        table1.row();
        newScore = customUi.createLabel(80,addZero(game.getRules().getHmsScore()[0])+":"
                +addZero(game.getRules().getHmsScore()[1])+":"
                +addZero(game.getRules().getHmsScore()[2]));
        table1.add(newScore).expand();
        table1.row();
        oldScore = customUi.createLabel(40,addZero(game.getPrefs().getInteger("highscoreNumH"))+":"
                +addZero(game.getPrefs().getInteger("highscoreNumMN"))+":"
                +addZero(game.getPrefs().getInteger("highscoreNumSEC")));
        table1.add(oldScore).expand();

        //newHighscore.setVisible(false);
        table1.row();
        retry = customUi.createButton("retry");
        table1.add(retry).padBottom(10);
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

        if (stateTime<1){
            stage0.draw();
        }else {
            stage1.draw();
        }
        pe.update(Gdx.graphics.getDeltaTime());
        game.getBatch().begin();
        pe.draw(game.getBatch());
        game.getBatch().end();

        if (stateTime<1.8 && stateTime>0.9){//0.9<stateTime && stateTime<1.8
            pe.allowCompletion();//end particle animation smoothly
        }


        stateTime += Gdx.graphics.getDeltaTime();


        if(stateTime>2){
            game.setScreen(new EndGameScreen(game));
            dispose();
        }
        /*if (stateTime-secTime >= 1){
            secTime = stateTime;

        }*/

    }

    private String addZero(int n){
        if (n<10){
            return "0"+n;
        }else{
            return Integer.toString(n);
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
        pe.dispose();
        stage0.dispose();
        stage1.dispose();
    }
}
