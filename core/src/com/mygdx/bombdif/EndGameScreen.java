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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class EndGameScreen implements Screen {

    final Bombdife game;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private Language language;
    private CustomUiBdf customUi;
    private Stage stage;
    private Table table;
    private ImageButton back;
    private Label newHighscore;
    private Label newScore;
    private Label oldScore;
    private ImageButton retry;
    private Sound buttonSound;

    public EndGameScreen(final Bombdife game){

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
        //table.debug();
        stage.addActor(table);
        table.setFillParent(true);

        table.row();
        //you latd for n cond, try not to ma it nativ wit a you lot and diplay tim it latd?
        table.row();
        back = customUi.createButton( "arrow_r");
        back.setTransform(true);
        back.setOrigin(back.getWidth()/2, back.getHeight()/2);
        //back.setScale(0.6f,0.6f);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                game.setScreen(new TitleScreen(game));
                game.getPrefs().flush();
                dispose();
            }
        });
        table.add(back).top().left().expand();;//.left().colspan(3)

        table.row();
        newHighscore = customUi.createLabel(40, language.getNewHighscore());
        table.add(newHighscore).expand();

        table.row();
        newScore = customUi.createLabel(80,addZero(game.getRules().getHmsScore()[0])+":"
                +addZero(game.getRules().getHmsScore()[1])+":"
                +addZero(game.getRules().getHmsScore()[2]));
        table.add(newScore).expand();
        table.row();
        oldScore = customUi.createLabel(40,addZero(game.getPrefs().getInteger("highscoreNumH"))+":"
                +addZero(game.getPrefs().getInteger("highscoreNumMN"))+":"
                +addZero(game.getPrefs().getInteger("highscoreNumSEC")));

        int[] old = {game.getPrefs().getInteger("highscoreNumH"),game.getPrefs().getInteger("highscoreNumMN"),game.getPrefs().getInteger("highscoreNumSEC")};

        if (compareTime(old,game.getRules().getHmsScore())){
            System.out.println("endgamescre3n: "+addZero(game.getPrefs().getInteger("highscoreNumH"))+":"
                    +addZero(game.getPrefs().getInteger("highscoreNumMN"))+":"
                    +addZero(game.getPrefs().getInteger("highscoreNumSEC")));
            System.out.println(game.getRules().getHmsScore()[0]*3600+game.getRules().getHmsScore()[1]*60+game.getRules().getHmsScore()[2]);
            game.getPrefs().putInteger("highscoreNumH", game.getRules().getHmsScore()[0]);
            game.getPrefs().putInteger("highscoreNumMN", game.getRules().getHmsScore()[1]);
            game.getPrefs().putInteger("highscoreNumSEC", game.getRules().getHmsScore()[2]);
            game.getPrefs().putInteger("mistake", game.getRules().getMiss());
        }else if (old[0] == game.getPrefs().getInteger("highscoreNumH")& old[1] == game.getPrefs().getInteger("highscoreNumMN") & old[2] == game.getPrefs().getInteger("highscoreNumSEC")){
            newHighscore.setVisible(false);
            if (game.getRules().getMiss()>game.getPrefs().getInteger("mistake")){
                game.getPrefs().putInteger("mistake", game.getRules().getMiss());
                System.out.println("endgame screen : mistake registered");
            }
            System.out.println("nothing registered");
        } else {
            newHighscore.setVisible(false);
            System.out.println("nothing registered");
            System.out.println(game.getRules().getHmsScore()[0]*3600+game.getRules().getHmsScore()[1]*60+game.getRules().getHmsScore()[2]);
            System.out.println("endgamescreen : "+addZero(game.getPrefs().getInteger("highscoreNumH"))+":"
                    +addZero(game.getPrefs().getInteger("highscoreNumMN"))+":"
                    +addZero(game.getPrefs().getInteger("highscoreNumSEC")));

        }
        //oldScore = customUi.createLabel(40,"00:42:00");
        table.add(oldScore).expand();

        //newHighscore.setVisible(false);
        table.row();
        retry = customUi.createButton("retry");
        retry.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play(game.getPrefs().getFloat("volumeS"));
                game.setScreen(new GameScreen(game));
                game.getPrefs().flush();
                dispose();
            }
        });
        table.add(retry).padBottom(10);
        //Gdx.graphics.setContinuousRendering(false);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0, 0.1f, 1);
        camera.update();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height);
    }

    private String addZero(int n){
        if (n<10){
            return "0"+n;
        }else{
            return Integer.toString(n);
        }
    }

    private boolean compareTime(int[] old,int[] recent){
        if (old[0] < recent[0]) {
            return true;
        } else if (old[0]>recent[0]) {
            return false;
        } else {
            if (old[1] < recent[1]){
                return true;
            }else if (old[1]>recent[1]){
                return false;
            }else{
                if (old[2] < recent[2]){
                    System.out.println("Endgamescreen: more score in recent");
                    return true;
                }else if (old[2]>=recent[2]) {
                    System.out.println("Endgamescreen: old "+old[2]);
                    System.out.println("Endgamescreen: recent "+recent[2]);
                    System.out.println("Endgamescreen: more score in old");
                    return false;
                }
            }
        }
        System.out.println("Endgamescreen: i might have a problem to know if a higher score was found");
        return true;
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
