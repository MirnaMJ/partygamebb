package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

public class CreateRoomScreen implements Screen {

    final Bombdife game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Language language;
    private CustomUiBdf cbutton;
    private ImageButton back;
    private ImageButton minusPlayer;
    private ImageButton plusPlayer;
    private TextButton difficulty;
    private TextButton create;
    private Stage stage;
    private Table table;
    //private TextureAtlas bombdif;
    //private Skin skin;
    //private Label.LabelStyle labelStyle0;
    //private Label.LabelStyle labelStyle1;
    private Label label0;
    private Label label1;
    private Label label2;
    private Label label4;
    private TextField roomName;
    private int nbPlayer;
    /* Hide textfield for room name when nbplayer equal 1
    * */

    public CreateRoomScreen(final Bombdife game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);

        language = game.getLanguage();

        nbPlayer = game.getRules().getNbPlayer();

        //bombdif = new TextureAtlas(Gdx.files.internal("bombdif.atlas"));

        //skin = new Skin();
        //skin.addRegions(bombdif);
        //labelStyle0 = new Label.LabelStyle();
        //labelStyle0.font = game.font40;
        //labelStyle1 = new Label.LabelStyle();
        //labelStyle1.font = game.font80;

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
                game.setScreen(new TitleScreen(game));
                dispose();
            }
        });
        table.add(back).colspan(3).top().left();//.left().colspan(3).expand();

        table.row();
        label4 = cbutton.createLabel(40, language.getChooseName());
        table.add(label4).colspan(3).expand();;//.colspan(3);

        table.row();
        roomName = cbutton.createTField("Lorem","basic0");
        //roomName.setMessageText();
        roomName.setText(game.getRules().getRoomName());
        table.add(roomName).colspan(3).expand();;//.colspan(3);

        table.row();
        label0 = cbutton.createLabel(40,language.getNbPlayer());
        //label0 = new Label(language.getNbPlayer(),labelStyle0);
        table.add(label0).colspan(3).expand();;//.colspan(3).expand();

        table.row();
        minusPlayer = cbutton.createButton( "arrow_r");
        minusPlayer.setTransform(true);
        minusPlayer.setOrigin(minusPlayer.getWidth()/2, minusPlayer.getHeight()/2);
        minusPlayer.setScale(0.5f,0.5f);
        minusPlayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (nbPlayer > 1){
                    nbPlayer -= 1;
                    label1.setText(nbPlayer);
                }else if (nbPlayer <= 1){
                    nbPlayer = 10;
                    label1.setText(nbPlayer);
                }
            }
        });
        table.add(minusPlayer);

        label1 = cbutton.createLabel(80,Integer.toString(nbPlayer));
        //label1 = new Label(Integer.toString(nbPlayer),labelStyle1);
        table.add(label1).expand();;

        plusPlayer = cbutton.createButton( "arrow_r");
        plusPlayer.setTransform(true);
        plusPlayer.setOrigin(plusPlayer.getWidth()/2, plusPlayer.getHeight()/2);
        plusPlayer.setScale(0.5f,0.5f);
        plusPlayer.setRotation(180);
        plusPlayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (nbPlayer < 10){
                    nbPlayer += 1;
                    label1.setText(nbPlayer);
                }else if (nbPlayer >= 10){
                    nbPlayer =1;
                    label1.setText(nbPlayer);
                }
            }
        });
        table.add(plusPlayer);

        table.row();
        label2 = cbutton.createLabel(40,language.getDiff());
        //label2 = new Label(language.getDiff(),labelStyle0);
        table.add(label2).colspan(3).expand();;//.colspan(3).expand();

        /*table.row();
        difficulty = cbutton.createTButton(game.getRules().getDifficulty(),"back");
        difficulty.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (language.getEasy().equals(difficulty.getText().toString())) {
                    difficulty.setText(language.getInter());
                }else if (language.getInter().equals(difficulty.getText().toString())) {
                    difficulty.setText(language.getHard());
                }else{
                    difficulty.setText(language.getEasy());
                }
            }
        });
        table.add(difficulty).colspan(3).expand();;//.colspan(3).expand().top();*/


        table.row();
        create = cbutton.createTButton(language.getCreate(), "noback");
        create.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //game.getRules().setTestPlayer();

                game.getRules().setDifficulty(difficulty.getText().toString());
                game.getRules().setNbPlayer(nbPlayer);
                game.getRules().setRoomName(roomName.getText());
                switch(nbPlayer){
                    case 1:
                        System.out.println("Entrer dans singleplayer");
                        game.setScreen(new GameScreen(game));
                        dispose();
                        break;
                    case 2:
                        System.out.println("oh boy la decouverte du bluetooth ou wifi direct");
                        break;
                    case 3:
                        System.out.println("do i even want to make it blow on only two people for this case and not up");
                        break;
                    default:
                        game.setScreen(new WaitingRoomScreen(game));
                        dispose();
                        break;
                }

            }
        });
        table.add(create).colspan(3).fillX().padBottom(10);;//.colspan(3).fillX();
        Gdx.graphics.setContinuousRendering(false);
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
        stage.dispose();
    }
}
