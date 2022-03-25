package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
    //private Label.LabelStyle labelStyle;
    private TextField roomName;
    //private TextField.TextFieldStyle textFStyle;
    private Stage stage;
    private Table table;
    //private TextureAtlas bombdif;
    //private Skin skin;

    public FindRoomScreen(final Bombdife game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);

        language = game.getLanguage();

        //bombdif = new TextureAtlas(Gdx.files.internal("bombdif.atlas"));

        //skin = new Skin();
        //skin.addRegions(bombdif);
        //labelStyle = new Label.LabelStyle();
        //labelStyle.font = game.font20;
        //textFStyle = new TextField.TextFieldStyle();
        //textFStyle.font = game.font20;
        //textFStyle.fontColor = new Color(0.2f, 0.81f, 0.81f, 1f);
        //textFStyle.background = skin.getDrawable("back_rec");
        //textFStyle.cursor =  skin.getDrawable("iconbomb");
        //textFStyle.selection = skin.getDrawable("noback_rec");

        cbutton = new CustomUiBdf(game);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        //table.debug();
        stage.addActor(table);
        table.setFillParent(true);

        table.row();
        back = cbutton.createButton( "arrow");
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
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
        table.add(roomName).expand().top().padLeft(20).padRight(20);//.fill(true,false)

        table.row();
        ok = cbutton.createTButton("OK", "noback");
        ok.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(roomName.getText());
            }
        });
        table.add(ok).top();
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
        //bombdif.dispose();
        //skin.dispose();

    }
}
