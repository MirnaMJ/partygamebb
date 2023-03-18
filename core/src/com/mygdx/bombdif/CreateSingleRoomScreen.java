package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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

public class CreateSingleRoomScreen implements Screen {

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
    int rotation = 180;
    private Label label2;
    private int nbPlayer;
    private int nbChallenge;
    private Label lDuration;
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
    private int hour;
    private int min;
    private int sec;
    private Label lTap;
    private CheckBox checkTap;
    private Label lSwipe;
    private CheckBox checkSwipe;
    private Label lShake;
    private CheckBox checkShake;
    private Label lCompass;
    private CheckBox checkCompass;

    private CheckBox[] trackingTasks;
    private String[] challenges;

    private Sound buttonSound;

    public CreateSingleRoomScreen(final Bombdife game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);

        buttonSound = Gdx.audio.newSound(Gdx.files.internal("menu_tick.wav"));
        language = game.getLanguage();

        nbPlayer = 1;
        nbChallenge = 0;

        hour = game.getRules().getHour();
        min = game.getRules().getMin();
        sec = game.getRules().getSec();

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


        scrollPane = cbutton.createScrollPane(table);
        scrollPane.setScrollbarsVisible(true);
        scrollPane.setScrollingDisabled(true,false);

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
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                game.setScreen(new TitleScreen(game));
                dispose();
            }
        });
        outerTable.add(back).top().left();;//.left().colspan(3).expand()
        outerTable.row();
        outerTable.add(scrollPane).expand();

        lDuration = cbutton.createLabel(20,language.getSelecTime());

        plusHour = cbutton.createButton("arrow_r");
        plusHour.setTransform(true);
        plusHour.setOrigin(plusHour.getWidth()/2, plusHour.getHeight()/2);
        plusHour.setRotation(-90);
        plusHour.setScale(0.4f);
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
        plusMin.setScale(0.4f);
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
        plusSec.setScale(0.4f);
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
        minusHour.setScale(0.4f);
        minusHour.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (hour > 0){
                    hour -= 1;
                    hourTF.setText(addZero(hour));
                }else if (hour <= 0){
                    hour = 0;
                    hourTF.setText(addZero(hour));
                }
            }
        });
        minusMin = cbutton.createButton("arrow_r");
        minusMin.setTransform(true);
        minusMin.setOrigin(minusMin.getWidth()/2, minusMin.getHeight()/2);
        minusMin.setRotation(90);
        minusMin.setScale(0.4f);
        minusMin.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (min > 0){
                    min -= 1;
                    minTF.setText(addZero(min));
                }else if (min <= 0){
                    min = 0;
                    minTF.setText(addZero(min));
                }
            }
        });
        minusSec = cbutton.createButton("arrow_r");
        minusSec.setTransform(true);
        minusSec.setOrigin(minusSec.getWidth()/2, minusSec.getHeight()/2);
        minusSec.setRotation(90);
        minusSec.setScale(0.4f);
        minusSec.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sec > 0){
                    sec -= 1;
                    secTF.setText(addZero(sec));
                }else if (sec <= 0){
                    sec = 0;
                    secTF.setText(addZero(sec));
                }
            }
        });

        fixedChrono(true);


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


        lTap = cbutton.createLabel(40,language.getTap());
        checkTap = cbutton.createCBox("","tap");
        checkTap.setOrigin(checkTap.getWidth()/2, checkTap.getHeight()/2);
        checkTap.setTransform(true);
        checkTap.setScale(0.5f);
        checkTap.setChecked(true);
        checkTap.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //action omtin omtin
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
            }
        });

        lSwipe = cbutton.createLabel(40,language.getSwipe());
        checkSwipe = cbutton.createCBox("","swipe");
        checkSwipe.setOrigin(checkSwipe.getWidth()/2, checkSwipe.getHeight()/2);
        checkSwipe.setTransform(true);
        checkSwipe.setScale(0.5f);
        checkSwipe.setChecked(true);
        checkSwipe.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //action omtin omtin
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
            }
        });


        lShake = cbutton.createLabel(40,language.getShake());
        checkShake = cbutton.createCBox("","shake");
        checkShake.setOrigin(checkShake.getWidth()/2, checkShake.getHeight()/2);
        checkShake.setTransform(true);
        checkShake.setScale(0.5f);
        if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)){//
            checkShake.setChecked(true);
            checkShake.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    //action omtin omtin
                    buttonSound.play(game.getPrefs().getFloat("volumeS"));
                }
            });
        }else{
            checkShake.setChecked(false);
            lShake.setColor(0.3f,0.3f,0.3f,1f);
            checkShake.getImage().setColor(0.4f,0.4f,0.4f,1f);
            checkShake.setDisabled(true);
        }

        lCompass = cbutton.createLabel(40,language.getPointTo());
        checkCompass = cbutton.createCBox("","pointTo");
        checkCompass.setOrigin(checkCompass.getWidth()/2, checkCompass.getHeight()/2);
        checkCompass.setTransform(true);
        checkCompass.setScale(0.5f);
        if (Gdx.input.isPeripheralAvailable(Input.Peripheral.Compass)){//
            checkCompass.setChecked(true);
            checkCompass.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    //action omtin omtin
                    buttonSound.play(game.getPrefs().getFloat("volumeS"));
                }
            });
        }else{
            checkCompass.setChecked(false);
            lCompass.setColor(0.3f,0.3f,0.3f,1f);
            checkCompass.getImage().setColor(0.4f,0.4f,0.4f,1f);
            checkCompass.setDisabled(true);
        }


        innerTable = new Table();
        //innerTable.debug();

        //scrollPane.setScrollingDisabled(true,false);
        innerTable.row();
        innerTable.add(lDuration).colspan(5).padTop(40);//.colspan(5)
        innerTable.row();
        /*innerTable.add(plusHour).colspan(2);//.colspan(2)
        innerTable.add(plusMin);
        innerTable.add(plusSec).colspan(2);
        innerTable.row();*/
        innerTable.add(hourTF);
        innerTable.add(sep0);
        innerTable.add(minTF);
        innerTable.add(sep1);
        innerTable.add(secTF);
        innerTable.row();
        label2 = cbutton.createLabel(40,language.getDiff());
        innerTable.add(label2).colspan(4).padTop(60);//.colspan(3).expand();


        innerTable.row();
        /*innerTable.add(minusHour).colspan(2);
        innerTable.add(minusMin);
        innerTable.add(minusSec).colspan(2);
        innerTable.row();*/
        innerTable.add(lTap);//.colspan(2)
        innerTable.add(checkTap).colspan(4);//
        innerTable.row();
        innerTable.add(lSwipe);
        innerTable.add(checkSwipe).colspan(4);
        innerTable.row();
        innerTable.add(lShake);
        innerTable.add(checkShake).colspan(4);
        innerTable.row();
        innerTable.add(lCompass);
        innerTable.add(checkCompass).colspan(4);

        table.row();
        table.add(innerTable).colspan(3);


        trackingTasks = new CheckBox[]{checkTap, checkSwipe, checkShake,checkCompass};
        //trackingTasks[] = ;
        //scrollPane.setScrollingDisabled(true, true);
        //scrollPane.setScrollY(-100);


        table.row();
        create = cbutton.createTButton(language.getCreate(), "noback");
        create.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //game.getRules().setDifficulty(difficulty.getText().toString());
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                challenges = new String[trackingTasks.length];
                for (int i = 0;i< trackingTasks.length;i++){
                    if (trackingTasks[i].isChecked()){
                        challenges[i] = trackingTasks[i].getName();
                        nbChallenge++;
                        System.out.println("createsingleroom"+nbChallenge);
                    }
                }

                game.getRules().setChallenge(challenges,nbChallenge);
                game.getRules().setCountdown(hour,min,sec);
                game.getRules().setNbPlayer(nbPlayer);
                switch(nbPlayer){
                    case 1:
                        System.out.println("cratroomcrn: Entrer dans singleplayer");
                        game.setScreen(new GameScreen(game));
                        dispose();
                        break;
                    case 2:
                        System.out.println("cratroomcrn: oh boy la decouverte du bluetooth ou wifi direct");
                        break;
                    case 3:
                        System.out.println("cratroomcrn: do i even want to make it blow on only two people for this case and not up");
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
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));//I need this for the scroll panto move
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
    private void tintChrono(float r,float g,float b,float a){
        plusHour.setColor(r,g,b,a);
        plusMin.setColor(r,g,b,a);
        plusSec.setColor(r,g,b,a);

        hourTF.setColor(r,g,b,a);
        minTF.setColor(r,g,b,a);
        secTF.setColor(r,g,b,a);

        minusHour.setColor(r,g,b,a);
        minusMin.setColor(r,g,b,a);
        minusSec.setColor(r,g,b,a);
    }

    private void setDisabledChrono(boolean bool){
        plusHour.setDisabled(bool);
        plusMin.setDisabled(bool);
        plusSec.setDisabled(bool);

        hourTF.setDisabled(bool);
        minTF.setDisabled(bool);
        secTF.setDisabled(bool);

        minusHour.setDisabled(bool);
        minusMin.setDisabled(bool);
        minusSec.setDisabled(bool);
    }

    private void fixedChrono(boolean fixed){
        if (fixed){
            tintChrono(0.7f,0.6f,0.5f,1);
            setDisabledChrono(true);
        }else{
            tintChrono(2,2,2,1);
            setDisabledChrono(false);
        }
    }

    @Override
    public void dispose() {
        buttonSound.dispose();
        stage.dispose();
    }
}


