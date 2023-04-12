package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class OptionScreen implements Screen {
    final Bombdife game;
    private Table outerTable;
    private ScrollPane scrollPane;
    private OrthographicCamera camera;
    private ScreenViewport viewport;
    private Language language;
    private CustomUiBdf cbutton;
    private Label label0;
    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Label label5;
    private Stage stage;
    private Table table;
    private ImageButton back;
    private TextButton lingua;
    private Slider volume;
    private Slider volumeBomb;
    private Slider volumeM;
    private CheckBox vibration;
    private Sound buttonSound;
    private Sound boomSound;
    private float vol;
    private float volBomb;
    private float volM;
    private TextButton hScore;

    public OptionScreen(final Bombdife game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ScreenViewport( camera);//camera.viewportWidth, camera.viewportHeight,

        language = game.getLanguage();

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("menu_tick.wav"));
        boomSound = Gdx.audio.newSound(Gdx.files.internal("atari_boom6.wav"));

        cbutton = new CustomUiBdf(game);

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        //table.debug();
        outerTable = new Table();
        //outerTable.debug();
        scrollPane = cbutton.createScrollPane(table);
        scrollPane.setScrollbarsVisible(true);

        stage.addActor(outerTable);
        outerTable.setFillParent(true);
        //scrollPane.setFillParent(true);
        //table.setFillParent(true);



        outerTable.row();
        back = cbutton.createButton( "arrow_r");
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //action omtin omtin
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                game.setScreen(new TitleScreen(game));
                //game.menuMusic.setVolume(game.getPrefs().getFloat("volumeM"));
                dispose();
            }
        });
        back.setTransform(true);
        back.setOrigin(back.getWidth()/2, back.getHeight()/2);
        back.setScale(0.7f,0.7f);
        outerTable.add(back).top().left();//.expand().right().pad(10);//size of button via size of cell

        outerTable.row();
        outerTable.add(scrollPane).expand().fill();

        table.row();
        label0 = cbutton.createLabel(80,language.getTongue());
        table.add(label0).colspan(2);//.top().padLeft(80).expand().padTop(20).left()

        table.row();

        lingua = cbutton.createTButton(language.getLanguage(), "back");
        lingua.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (language.getLanguage().equals("Français")) {
                    language.setLanguage("English");
                    updateLabel();
                    game.getPrefs().putString("language","English");
                }else if (language.getLanguage().equals("English")){
                    language.setLanguage("Français");
                    updateLabel();
                    game.getPrefs().putString("language","Français");
                }
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
            }
        });
        table.add(lingua).padBottom(60).top().expand().colspan(2);//.pad(10).bottom();

        table.row();

        label1 = cbutton.createLabel(80,language.getVolume());
        table.add(label1).expand().top().left().colspan(2).padLeft(10);//

        table.row();

        label2 = cbutton.createLabel(40, language.getSound());
        table.add(label2).colspan(2);//.expand().top()

        table.row();
        volume = cbutton.createSlider(0,1,0.1f,false,"bombCursor");
        table.add(volume).fill(true,false).padLeft(30f).padRight(20f).colspan(2);//.padBottom(70)
        volume.setValue(game.getPrefs().getFloat("volumeS"));
        vol = volume.getValue();


        table.row();

        label5 = cbutton.createLabel(40, language.getBombSound());
        table.add(label5).colspan(2);//.expand().top()

        table.row();
        volumeBomb = cbutton.createSlider(0,1,0.1f,false,"bombCursor");
        table.add(volumeBomb).fill(true,false).padLeft(30f).padRight(20f).colspan(2);//.padBottom(70)
        volumeBomb.setValue(game.getPrefs().getFloat("volumeBS"));
        volBomb = volumeBomb.getValue();


        table.row();

        label3 = cbutton.createLabel(40, language.getMusic());
        table.add(label3).colspan(2);//.top()

        table.row();
        volumeM = cbutton.createSlider(0,1,0.05f,false,"bombCursor");
        table.add(volumeM).fill(true,false).padLeft(20f).padRight(20f).colspan(2);//.padBottom(70)
        volumeM.setValue(game.getPrefs().getFloat("volumeM"));
        volM = volumeM.getValue();

        table.row();
        vibration = cbutton.createCBox("", "viby");
        vibration.setChecked(game.getPrefs().getBoolean("vibe"));
        vibration.setOrigin(vibration.getWidth()/2, vibration.getHeight()/2);
        vibration.setTransform(true);
        vibration.setScale(0.8f);
        vibration.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //action omtin omtin
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                if (vibration.isChecked()){
                    Gdx.input.vibrate(150);
                }
            }
        });
        table.add(vibration);//.left().expand()

        label4 = cbutton.createLabel(40,language.getVibe());
        table.add(label4).expand().left();

        table.row();
        hScore = cbutton.createTButton(language.getHighscore(), "noback");
        hScore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play(game.getPrefs().getFloat("volumeS"));

                game.setScreen(new ScoreRecordScreen(game));
                dispose();
            }
        });
        table.add(hScore).padBottom(Gdx.graphics.getHeight()/4).expand().fill().colspan(2).padLeft(Gdx.graphics.getWidth()/64).padRight(Gdx.graphics.getWidth()/4);//.pad(10).bottom();.top().colspan(2)Gdx.graphics.getWidth()/64
        scrollPane.setScrollingDisabled(true, false);
        //Gdx.graphics.setContinuousRendering(true);
    }

    public void updateLabel(){
        label0.setText(language.getTongue());
        lingua.setText(language.getLanguage());
        label2.setText(language.getSound());
        label3.setText(language.getMusic());
        label4.setText(language.getVibe());
        hScore.setText(language.getHighscore());
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.1f, 1);
        camera.update();
        stage.draw();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        if (vol != volume.getValue()){
            //buttonSound.play();
            vol = volume.getValue();
            buttonSound.play(vol,1f,0f);//([0,1],[0.5,1,2],[-1,1]
        }

        if (volBomb != volumeBomb.getValue()){
            volBomb = volumeBomb.getValue();
            boomSound.play(volBomb,1f,0f);//([0,1],[0.5,1,2],[-1,1]
        }

        if (volM != volumeM.getValue()){
            volM = volumeM.getValue();
            game.menuMusic.setVolume(volM);
        }
    }

    @Override
    public void show() {

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
        game.getPrefs().putFloat("volumeS", vol);
        game.getPrefs().putFloat("volumeBS", volBomb);
        game.getPrefs().putFloat("volumeM", volM);
        game.getPrefs().putBoolean("vibe", vibration.isChecked());
        game.getPrefs().flush();
        stage.dispose();
        buttonSound.dispose();
        boomSound.dispose();
    }
}
