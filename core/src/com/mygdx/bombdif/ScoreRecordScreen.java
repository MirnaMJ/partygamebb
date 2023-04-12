package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ScoreRecordScreen implements Screen {

    final Bombdife game;
    private ImageButton back;
    private TextButton deleteScore;
    private TextField score;
    private OrthographicCamera camera;
    private ExtendViewport viewport;
    private Language language;
    private Sound buttonSound;
    private CustomUiBdf cbutton;
    private Stage stage;
    private Table table;
    private Label label0;
    private Label label1;
    private Dialog dialog;
    private Label prompt;
    private TextButton lb;
    private TextButton rb;

    public ScoreRecordScreen(final Bombdife game){

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ExtendViewport(480, 800, camera);//

        language = game.getLanguage();

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("menu_tick.wav"));

        cbutton = new CustomUiBdf(game);

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        //table.debug();
        stage.addActor(table);
        table.setFillParent(true);

        table.row();
        back = cbutton.createButton( "arrow_r");
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //action omtin omtin
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                game.getPrefs().flush();
                game.setScreen(new OptionScreen(game));
                dispose();
            }
        });
        back.setTransform(true);
        back.setOrigin(back.getWidth()/2, back.getHeight()/2);
        back.setScale(0.6f,0.6f);
        table.add(back).top().left().pad(10);//.expand().right();//size of button via size of cell

        table.row();
        label0 = cbutton.createLabel(40, language.getHighscore());
        table.add(label0).bottom().padTop(10);

        table.row();
        score = cbutton.createTField(addZero(game.getPrefs().getInteger("highscoreNumH"))
                +":"+addZero(game.getPrefs().getInteger("highscoreNumMN"))
                +":"+addZero(game.getPrefs().getInteger("highscoreNumSEC")),"chrono");
        score.setAlignment(Align.center);
        score.setDisabled(true);
        table.add(score).expand().fill(true,false).padLeft(70).padRight(80);

        table.row();
        label1 = cbutton.createLabel(40, language.getMiss()+" : "+game.getPrefs().getInteger("mistake"));
        label1.setColor(1f,0.05f,0.1f,1);
        table.add(label1).expand().top();

        table.row();
        deleteScore = cbutton.createTButton("Foetus deletus","noback");
        deleteScore.setColor(0.8f,0.05f,0.1f,1);
        deleteScore.getLabel().setColor(0.8f,0.05f,0.1f,1);
        deleteScore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                game.getPrefs().putInteger("highscoreNumH", 0);
                game.getPrefs().putInteger("highscoreNumMN", 0);
                game.getPrefs().putInteger("highscoreNumSEC", 0);
                game.getPrefs().putInteger("mistake", 0);
                score.setText("00:00:00");
                label1.setText(language.getMiss()+" : 0");
            }
        });
        table.add(deleteScore).bottom().right();

        Gdx.graphics.setContinuousRendering(false);
    }


    @Override
    public void show() {

    }

    private String addZero(int n){
        if (n<10){
            return "0"+n;
        }else{
            return Integer.toString(n);
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.1f, 1);
        camera.update();
        stage.draw();

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
        buttonSound.dispose();

    }
}
