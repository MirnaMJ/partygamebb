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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class OptionScreen implements Screen {
    final Bombdife game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Language language;
    private CustomUiBdf cbutton;
    private Label label0;
    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;
    private Stage stage;
    private Table table;
    private ImageButton back;
    private TextButton lingua;
    private Slider volume;
    private Slider volumeM;
    private CheckBox vibration;
    private Sound buttonSound;
    private float vol;
    private float volM;

    public OptionScreen(final Bombdife game){
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
                game.setScreen(new TitleScreen(game));
                game.getPrefs().putFloat("volumeS", vol);
                game.getPrefs().putFloat("volumeM", volM);
                game.getPrefs().flush();
                //game.menuMusic.setVolume(game.getPrefs().getFloat("volumeM"));
                dispose();
            }
        });
        back.setTransform(true);
        back.setOrigin(back.getWidth()/2, back.getHeight()/2);
        back.setScale(0.6f,0.6f);
        table.add(back).top().left();//.expand().right().pad(10);//size of button via size of cell

        table.row();
        label0 = cbutton.createLabel(40,language.getTongue());
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

        label1 = cbutton.createLabel(40,language.getVolume());
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

        label3 = cbutton.createLabel(40, language.getMusic());
        table.add(label3).colspan(2);//.top()

        table.row();
        volumeM = cbutton.createSlider(0,1,0.05f,false,"bombCursor");
        table.add(volumeM).fill(true,false).padLeft(20f).padRight(20f).colspan(2);//.padBottom(70)
        volumeM.setValue(game.getPrefs().getFloat("volumeM"));
        volM = volumeM.getValue();

        table.row();
        vibration = cbutton.createCBox("", "viby");
        vibration.setChecked(true);
        vibration.setOrigin(vibration.getWidth()/2, vibration.getHeight()/2);
        vibration.setTransform(true);
        vibration.setScale(0.6f);
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

        label4 = cbutton.createLabel(20,language.getVibe());
        table.add(label4).expand().left();
    }

    public void updateLabel(){
        label0.setText(language.getTongue());
        lingua.setText(language.getLanguage());
        label2.setText(language.getSound());
        label3.setText(language.getMusic());
        label4.setText(language.getVibe());
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        stage.draw();
        if (vol != volume.getValue()){
            //buttonSound.play();
            vol = volume.getValue();
            buttonSound.play(vol,1f,0f);//([0,1],[0.5,1,2],[-1,1]
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
        stage.dispose();
        buttonSound.dispose();
    }
}
