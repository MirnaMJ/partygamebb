package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    private Stage stage;
    private Table table;
    private ImageButton back;
    private TextButton lingua;
    private Slider volume;
    private Sound buttonSound;
    private float vol;

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
                game.getPrefs().putFloat("volumeS", vol/10);
                game.getPrefs().flush();
                game.menuMusic.setVolume(game.getPrefs().getFloat("volumeS"));
                dispose();
            }
        });
        back.setTransform(true);
        back.setOrigin(back.getWidth()/2, back.getHeight()/2);
        back.setScale(0.6f,0.6f);
        table.add(back).top().left();//.expand().right().pad(10);//size of button via size of cell

        table.row();
        label0 = cbutton.createLabel(40,language.getTongue());
        table.add(label0);//.top().padLeft(80).expand().padTop(20).left()

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
        table.add(lingua).padBottom(60).top().expand();//.pad(10).bottom();.colspan(2)

        table.row();

        label1 = cbutton.createLabel(40,language.getVolume());
        table.add(label1).expand().top();//.colspan(2)

        table.row();
        volume = cbutton.createSlider(0,10,1f,false,"bombCursor");
        table.add(volume).padBottom(70).fill(true,false).padLeft(20f).padRight(20f);
        volume.setValue(game.getPrefs().getFloat("volumeS")*10);
        vol = volume.getValue();


    }

    public void updateLabel(){
        label0.setText(language.getTongue());
        lingua.setText(language.getLanguage());
        //label1.setText(language.getVolume());

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        stage.draw();
        if (vol != volume.getValue()){
            buttonSound.play();
            vol = volume.getValue();
            buttonSound.play(vol/10f,1f,0f);//([0,1],[0.5,1,2],[-1,1]
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
