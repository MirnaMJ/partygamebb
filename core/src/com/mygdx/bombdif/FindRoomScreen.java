package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class FindRoomScreen implements Screen {

    final Bombdife game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Language language;
    private CustomUiBdf cbutton;
    private ImageButton back;
    private TextButton ok;
    private Label label;
    private TextField roomName;
    private Stage stage;
    private Table table;
    private Sound buttonSound;

    public FindRoomScreen(final Bombdife game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("menu_tick.wav"));
        language = game.getLanguage();

        cbutton = new CustomUiBdf(game);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        //table.debug();
        stage.addActor(table);
        table.setFillParent(true);

        table.row();
        back = cbutton.createButton( "arrow_r");
        back.setTransform(true);
        back.setOrigin(back.getWidth()/2, back.getHeight()/2);
        back.setScale(0.6f,0.6f);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                game.setScreen(new TitleScreen(game));
                dispose();
            }
        });
        table.add(back).top().left();

        table.row();
        label = cbutton.createLabel(20,language.getEnterName());
        //label = new Label(language.getEnterName(),labelStyle);
        table.add(label).expand().bottom();

        table.row();
        roomName = cbutton.createTField("","basic0");
        //roomName = new TextField("",textFStyle);
        /*roomName.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
            }
        });*/
        table.add(roomName).expand().top().padLeft(20).padRight(20);//.fill(true,false)

        table.row();
        ok = cbutton.createTButton("OK", "noback");
        ok.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                System.out.println(roomName.getText());
            }
        });
        table.add(ok).top();
        Gdx.graphics.setContinuousRendering(false);
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();
        stage.draw();
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
        //bombdif.dispose();
        //skin.dispose();

    }
}
