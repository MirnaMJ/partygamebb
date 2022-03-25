package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class TitleScreen implements Screen {
    final Bombdife game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Language language;
    private CustomUiBdf cbutton;
    private Stage stage;
    private Table table;
    private TextButton create;
    private TextButton join;
    private TextButton option;
    private TextureAtlas bombdif;
    private TextureRegion bomb;
    private Image imageBomb;


    public TitleScreen(final Bombdife game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //camera.zoom= 0.55f;
        //camera.lookAt(camera.position.add(-48,15,0));
        viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);

        language = game.getLanguage();

        bombdif = new TextureAtlas(Gdx.files.internal("bombdif.atlas"));
        bomb = bombdif.findRegion("iconbomb");
        imageBomb = new Image(bomb);

        cbutton = new CustomUiBdf(game);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        //table.debug();
        stage.addActor(table);
        table.setFillParent(true);

        table.row();
        table.add(imageBomb).padLeft(50).padRight(30).padBottom(10).expand().fill();//.padTop(10)

        table.row();
        int pad = 50;
        create = cbutton.createTButton(language.getCreate(), "noback");
        create.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new CreateRoomScreen(game));
                dispose();
            }
        });
        table.add(create).fill().padLeft(pad).padRight(pad);//size of button via size of cell

        table.row();
        join = cbutton.createTButton(language.getJoin(), "noback");
        join.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new FindRoomScreen(game));
                dispose();
            }
        });
        table.add(join).fill().padLeft(pad).padRight(pad);

        table.row();
        option = cbutton.createTButton(language.getOption(), "noback");
        option.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new OptionScreen(game));
                dispose();
            }
        });
        table.add(option).fill().padLeft(pad).padRight(pad);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.12f, 1);
        camera.update();
        stage.draw();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getFont40().draw(game.getBatch(), "Bomb Di Fé", camera.viewportWidth/3, camera.viewportHeight/2);
        game.getBatch().end();


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
        bombdif.dispose();
    }
}
