package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class WaitingRoomScreen implements Screen {

    final Bombdife game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Language language;
    private CustomUiBdf customUi;
    private Stage stage;
    private Table table;
    private ImageButton back;
    private Label labelRoomName;
    private Table header;
    private Label labelWait;
    private TextButton start;
    private Sound buttonSound;

    public WaitingRoomScreen(final Bombdife game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        buttonSound = Gdx.audio.newSound(Gdx.files.internal("menu_tick.wav"));

        language = game.getLanguage();

        customUi = new CustomUiBdf(game);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.debug();
        stage.addActor(table);
        table.setFillParent(true);

        table.row();
        back = customUi.createButton( "arrow");
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                game.setScreen(new CreateRoomScreen(game));
                dispose();
            }
        });
        table.add(back).top().left();//.expand()

        table.row();
        labelRoomName = customUi.createLabel(40,language.getRoom()+game.getRules().getRoomName());
        table.add(labelRoomName).expand().top();//.top()

        table.row();
        header = customUi.createTable("header");//pttr a remplafer avec container, plus leger
        labelWait = customUi.createLabel(20,language.getWaiting());
        header.row();
        header.add(labelWait);
        table.add(header).padLeft(20).padRight(20).fillX();


        //players = customUi.createTable("basic");
        Label[] labels = new Label[10];
        Table[] players = new Table[10];
        for (int i = 0;i<game.getRules().getNbPlayer();i++) {
            //players.row();
            //labels[i] = customUi.createLabel(20, "player test "+i);

            //label2.setText("player test "+i);
            //label2 = customUi.createLabel(20, "player test "+i);
            //players.add(labels[i]);
            table.row();
            players[i] = customUi.createTable("basic");
            players[i].row();
            labels[i] = customUi.createLabel(20, "player test "+i);
            players[i].add(labels[i]);
            table.add(players[i]).padLeft(20).padRight(20).expand().top().fill();
        }

        table.row();
        start = customUi.createTButton(language.getStart(),"noback");
        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        table.add(start).padTop(10).fillX();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.15f, 1);
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
        buttonSound.dispose();
        stage.dispose();
    }
}
