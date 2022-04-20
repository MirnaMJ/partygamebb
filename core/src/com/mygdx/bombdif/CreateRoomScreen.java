package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
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
    private TextButton create;
    private Stage stage;
    private Table outerTable;
    private Table table;
    private Table innerTable;
    private final ScrollPane scrollPane;
    private final ImageButton viewTask;
    int rotation = 180;
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
    private Label lDuration;
    private int duration;
    private ImageButton plusHour;
    private ImageButton plusMin;
    private ImageButton plusSec;
    private ImageButton minusHour;
    private ImageButton minusMin;
    private ImageButton minusSec;
    private TextField hourTF;
    private Label sep0;
    private TextField minTF;
    private Label sep1;
    private TextField secTF;
    private int hour = 0;
    private int min = 0;
    private int sec = 20;
    private Label lTap;
    private CheckBox checkTap;
    private Label lSwipe;
    private CheckBox checkSwipe;
    private Label lShake;
    private CheckBox checkShake;


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
        //Gdx.input.setCatchKey(Input.Keys.BACK, true);

        table = new Table();
        //table.debug();

        outerTable = new Table();
        //outerTable.debug();

        scrollPane = new ScrollPane(table);
        //scrollPane.setScrollingDisabled(true, false);

        stage.addActor(outerTable);
        outerTable.setFillParent(true);
        //scrollPane.setFillParent(true);
        //table.setFillParent(true);

        outerTable.row();
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
        outerTable.add(back).top().left();;//.left().colspan(3).expand()
        outerTable.row();
        outerTable.row();
        outerTable.add(scrollPane).expand();

        table.row();
        label4 = cbutton.createLabel(40, language.getChooseName());
        table.add(label4).colspan(3).expand();;//.colspan(3);

        table.row();
        roomName = cbutton.createTField("Lorem","basic0");
        //roomName.setMessageText();
        roomName.setText(game.getRules().getRoomName());
        table.add(roomName).colspan(3).expand().padBottom(20);;//.colspan(3);

        table.row();
        label0 = cbutton.createLabel(40,language.getNbPlayer());
        //label0 = new Label(language.getNbPlayer(),labelStyle0);
        table.add(label0).colspan(3).expand();;//.colspan(3).expand();

        table.row();
        minusPlayer = cbutton.createButton( "arrow_r");
        minusPlayer.setTransform(true);
        minusPlayer.setOrigin(minusPlayer.getWidth()/2, minusPlayer.getHeight()/2);
        minusPlayer.setScale(0.65f,0.65f);
        minusPlayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (nbPlayer > 1){
                    nbPlayer -= 1;
                    label1.setText(nbPlayer);
                }else if (nbPlayer <= 1){
                    /*if (nbPlayer ==1){
                        label4.setVisible(false);
                        roomName.setVisible(false);
                    }else{
                        label4.setVisible(true);
                        roomName.setVisible(true);
                    } tat not it viually*/
                    nbPlayer = 10;
                    label1.setText(nbPlayer);
                }
            }
        });
        table.add(minusPlayer);

        label1 = cbutton.createLabel(80,Integer.toString(nbPlayer));
        table.add(label1).expand();

        plusPlayer = cbutton.createButton( "arrow_r");
        plusPlayer.setTransform(true);
        plusPlayer.setOrigin(plusPlayer.getWidth()/2, plusPlayer.getHeight()/2);
        plusPlayer.setScale(0.65f,0.65f);
        plusPlayer.setRotation(180);
        plusPlayer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (nbPlayer < 10){
                    nbPlayer += 1;
                    label1.setText(nbPlayer);
                    //label4.setVisible(true);
                    //roomName.setVisible(true);
                }else if (nbPlayer >= 10){
                    nbPlayer =1;
                    label1.setText(nbPlayer);
                    //    label4.setVisible(false);
                    //    roomName.setVisible(false);
                }
            }
        });
        table.add(plusPlayer);

        table.row();
        label2 = cbutton.createLabel(40,language.getDiff());
        table.add(label2).colspan(2).expand();;//.colspan(3).expand();

        viewTask = cbutton.createButton("arrow_r");
        viewTask.setTransform(true);
        viewTask.setOrigin(viewTask.getWidth()/2, viewTask.getHeight()/2);
        viewTask.setRotation(rotation);
        viewTask.setScale(0.4f);

        lDuration = cbutton.createLabel(40,language.getSelecTime());

        plusHour = cbutton.createButton("arrow_r");
        plusHour.setTransform(true);
        plusHour.setOrigin(plusHour.getWidth()/2, plusHour.getHeight()/2);
        plusHour.setRotation(-90);
        plusHour.setScale(0.2f);
        plusHour.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (hour < 42){
                    hour += 1;
                    hourTF.setText(addZero(hour));
                }else if (hour >= 42){
                    hour = 0;
                    hourTF.setText(addZero(hour));
                }
            }
        });
        plusMin = cbutton.createButton("arrow_r");
        plusMin.setTransform(true);
        plusMin.setOrigin(plusMin.getWidth()/2, plusMin.getHeight()/2);
        plusMin.setRotation(-90);
        plusMin.setScale(0.2f);
        plusMin.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (min < 59){
                    min += 1;
                    minTF.setText(addZero(min));
                }else if (min >= 59){
                    min = 0;
                    minTF.setText(addZero(min));
                }
            }
        });
        plusSec = cbutton.createButton("arrow_r");
        plusSec.setTransform(true);
        plusSec.setOrigin(plusSec.getWidth()/2, plusSec.getHeight()/2);
        plusSec.setRotation(-90);
        plusSec.setScale(0.2f);
        plusSec.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sec < 59){
                    sec += 1;
                    secTF.setText(addZero(sec));
                }else if (sec >= 59){
                    sec = 0;
                    secTF.setText(addZero(sec));
                }
            }
        });


        hourTF = cbutton.createTField(addZero(hour),"chrono");
        hourTF.setAlignment(Align.center);
        hourTF.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                hour = Integer.parseInt(hourTF.getText());
                return super.keyDown(event, keycode);
            }
        });
        sep0 = cbutton.createLabel(40,":");
        minTF = cbutton.createTField(addZero(min),"chrono");
        minTF.setAlignment(Align.center);
        minTF.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                min = Integer.parseInt(minTF.getText());
                return super.keyDown(event, keycode);
            }
        });
        sep1 = cbutton.createLabel(40,":");
        secTF = cbutton.createTField(addZero(sec),"chrono");
        secTF.setAlignment(Align.center);
        secTF.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                sec = Integer.parseInt(secTF.getText());
                return super.keyDown(event, keycode);
            }
        });

        minusHour = cbutton.createButton("arrow_r");
        minusHour.setTransform(true);
        minusHour.setOrigin(minusHour.getWidth()/2, minusHour.getHeight()/2);
        minusHour.setRotation(90);
        minusHour.setScale(0.2f);
        minusHour.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (hour > 0){
                    hour -= 1;
                    hourTF.setText(addZero(hour));
                }else if (hour <= 0){
                    hour = 0;
                    label1.setText(addZero(hour));
                }
            }
        });
        minusMin = cbutton.createButton("arrow_r");
        minusMin.setTransform(true);
        minusMin.setOrigin(minusMin.getWidth()/2, minusMin.getHeight()/2);
        minusMin.setRotation(90);
        minusMin.setScale(0.2f);
        minusMin.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (min > 0){
                    min -= 1;
                    minTF.setText(addZero(min));
                }else if (min <= 0){
                    min = 0;
                    label1.setText(addZero(min));
                }
            }
        });
        minusSec = cbutton.createButton("arrow_r");
        minusSec.setTransform(true);
        minusSec.setOrigin(minusSec.getWidth()/2, minusSec.getHeight()/2);
        minusSec.setRotation(90);
        minusSec.setScale(0.2f);
        minusSec.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sec > 0){
                    sec -= 1;
                    secTF.setText(addZero(sec));
                }else if (sec <= 0){
                    sec = 0;
                    label1.setText(addZero(sec));
                }
            }
        });

        lTap = cbutton.createLabel(20,language.getTap());
        checkTap = cbutton.createCBox("");
        checkTap.setOrigin(checkTap.getWidth()/2, checkTap.getHeight()/2);
        checkTap.setTransform(true);
        checkTap.setScale(0.5f);
        checkTap.setChecked(true);

        lSwipe = cbutton.createLabel(20,language.getSwipe());
        checkSwipe = cbutton.createCBox("");
        checkSwipe.setOrigin(checkSwipe.getWidth()/2, checkSwipe.getHeight()/2);
        checkSwipe.setTransform(true);
        checkSwipe.setScale(0.5f);
        checkSwipe.setChecked(true);


        lShake = cbutton.createLabel(20,language.getShake());
        checkShake = cbutton.createCBox("");
        checkShake.setOrigin(checkShake.getWidth()/2, checkShake.getHeight()/2);
        checkShake.setTransform(true);
        checkShake.setScale(0.5f);
        checkShake.setChecked(true);

        innerTable = new Table();
        //innerTable.debug();

        //scrollPane.setScrollingDisabled(true,false);
        innerTable.row();
        innerTable.add(lDuration).colspan(5);//.colspan(5)
        innerTable.row();
        innerTable.add(plusHour).colspan(2);//.colspan(2)
        innerTable.add(plusMin);
        innerTable.add(plusSec).colspan(2);
        innerTable.row();
        innerTable.add(hourTF);
        innerTable.add(sep0);
        innerTable.add(minTF);
        innerTable.add(sep1);
        innerTable.add(secTF);
        innerTable.row();
        innerTable.add(minusHour).colspan(2);
        innerTable.add(minusMin);
        innerTable.add(minusSec).colspan(2);
        innerTable.row();
        innerTable.add(lTap);//.colspan(2)
        innerTable.add(checkTap).colspan(4);//
        innerTable.row();
        innerTable.add(lSwipe);
        innerTable.add(checkSwipe).colspan(4);
        innerTable.row();
        innerTable.add(lShake);
        innerTable.add(checkShake).colspan(4);


        viewTask.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (rotation == 180){
                    rotation = 90;
                    System.out.println("opn");
                    innerTable.setVisible(true);
                    //scrollPane.setScrollingDisabled(true, false);
                    //scrollPane.setScrollY(0);

                }else{
                    rotation = 180;
                    System.out.println("clo");
                    //table.removeActor(innerTable);
                    innerTable.setVisible(false);
                    //scrollPane.setScrollingDisabled(true, true);
                    //scrollPane.setScrollY(-100);
                }
                viewTask.setRotation(rotation);
            }
        });
        table.add(viewTask);

        table.row();
        table.add(innerTable).colspan(3);//
        innerTable.setVisible(false);
        //scrollPane.setScrollingDisabled(true, true);
        //scrollPane.setScrollY(-100);

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

                //game.getRules().setDifficulty(difficulty.getText().toString());
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
        outerTable.row();
        outerTable.add(create).fillX().padBottom(10);;//.colspan(3).fillX();
        //Gdx.graphics.setContinuousRendering(false);
    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.15f, 1);
        camera.update();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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

    private String addZero(int n){
        if (n<10){
            return "0"+n;
        }else{
            return Integer.toString(n);
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
