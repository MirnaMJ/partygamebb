package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class ScoreRecordScreen implements Screen {

    final Bombdife game;
    private ImageButton back;
    private TextField score;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Language language;
    private Sound buttonSound;
    private CustomUiBdf cbutton;
    private Stage stage;
    private Table table;
    private Label label0;
    private Label label1;
    private Label label2;

    public ScoreRecordScreen(final Bombdife game){

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);

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
                game.setScreen(new OptionScreen(game));
                dispose();
            }
        });
        //back.setTransform(true);
        //back.setOrigin(back.getWidth()/2, back.getHeight()/2);
        //back.setScale(0.6f,0.6f);
        table.add(back).top().left();//.expand().right().pad(10);//size of button via size of cell

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
