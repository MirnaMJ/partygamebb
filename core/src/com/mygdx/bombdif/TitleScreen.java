package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TitleScreen implements Screen {
    final Bombdife game;
    private OrthographicCamera camera;
    private ExtendViewport viewport;
    private Language language;
    private CustomUiBdf cbutton;
    private Stage stage;
    private Table table;
    private TextButton single;
    private TextButton multi;
    private TextButton option;
    private TextureAtlas bombdif;
    private TextureRegion bomb;
    private Image imageBomb;
    private Sound buttonSound;

    public TitleScreen(final Bombdife game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,540 , 960 );//Gdx.graphics.getWidth()Gdx.graphics.getHeight()
        //camera.zoom= 0.55f;
        //camera.lookAt(camera.position.add(-48,15,0));
        viewport = new ExtendViewport(580 , 980, camera);//FillViewport(camera.viewportWidth, camera.viewportHeight, camera)

        language = game.getLanguage();

        bombdif = new TextureAtlas(Gdx.files.internal("bombdif.atlas"));
        bomb = bombdif.findRegion("Logo");
        imageBomb = new Image(bomb);
        //imageBomb.setSize(1000,1000);imageBomb.getWidth()*camera.viewportWidth,imageBomb.getHeight()*camera.viewportHeight

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("menu_tick.wav"));

        cbutton = new CustomUiBdf(game);


        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        table = new Table();

        //table.debug();
        stage.addActor(table);
        table.setFillParent(true);

        table.row();
        table.add(imageBomb).expand().fill();//.padTop(Gdx.graphics.getHeight()/16).padBottom(Gdx.graphics.getHeight()/16).padLeft(Gdx.graphics.getWidth()/32).padRight(Gdx.graphics.getWidth()/32)
        imageBomb.setScaling(Scaling.fit);


        table.row();
        single = cbutton.createTButton(language.getSingle(), "noback");
        single.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                game.setScreen(new CreateSingleRoomScreen(game));
                dispose();
            }
        });
        table.add(single).expand().fill(0.5f,0.4f);//.padBottom(Gdx.graphics.getHeight()/64).top().expand()size of button via size of cell.padLeft(pad).padRight(pad).fill().padLeft(Gdx.graphics.getWidth()/8).padRight(Gdx.graphics.getWidth()/8)



        table.row();
        option = cbutton.createTButton(language.getOption(), "noback");
        option.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                game.setScreen(new OptionScreen(game));
                dispose();
            }
        });
        table.add(option).top().expand().fill(0.5f,0.3f);//.expand().padLeft(pad).padRight(pad).padTop(50).padBottom(80)

        Gdx.graphics.setContinuousRendering(false);
        //Gdx.graphics.requestRendering();


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        stage.draw();
        //game.getBatch().setProjectionMatrix(camera.combined);


    }

    @Override
    public void show() {
        game.menuMusic.play();
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
        bombdif.dispose();
        buttonSound.dispose();
    }
}
