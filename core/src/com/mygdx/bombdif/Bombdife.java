package com.mygdx.bombdif;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

public class Bombdife extends Game {
	private SpriteBatch batch;
	private FreeTypeFontGenerator generator;
	//private BitmapFont font20;
	private BitmapFont font30;
	private BitmapFont font40;
	private BitmapFont font80;
	private BitmapFont font100;
	private Language language;
	public Music menuMusic;
	private GameRulesManager rules;
	private Preferences prefs;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		generator = new FreeTypeFontGenerator(Gdx.files.internal("riffic.bold.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		//parameter.size = 20;
		//font20 = generator.generateFont(parameter); // font size 15 pixels
		parameter.size = (int)Math.floor(30*(Gdx.graphics.getDensity()/1.5));//(int)Math.floor(30*(Gdx.graphics.getDensity()/1.5))
		font30 = generator.generateFont(parameter); // font size 15 pixels
		parameter.size = 40;
		font40 = generator.generateFont(parameter); // font size 40 pixels
		parameter.size = (int)Math.floor(80*(Gdx.graphics.getDensity()/1.5));
		font80 = generator.generateFont(parameter); // font size 80 pixels
		parameter.size = (int)Math.floor(100*(Gdx.graphics.getDensity()/1.5));
		font100 = generator.generateFont(parameter); // font size 100 pixels
		prefs = Gdx.app.getPreferences("My Preferences");
        /*String name = prefs.getString("name", "No name stored");
        prefs.putBoolean("soundOn", true);*/
		language = new Language(prefs.getString("language","English"));//Français
		rules = new GameRulesManager(language,prefs);
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("menu_chill.wav"));
		menuMusic.setLooping(true);
		menuMusic.setVolume(getPrefs().getFloat("volumeM"));
		this.setScreen(new TitleScreen(this));
		// might need to check presnecepsnece of accelerometer? boolean available = Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer);

	}

	@Override
	public void render () {
		super.render();
	}

	public GameRulesManager getRules() {
		return rules;
	}

	public Language getLanguage() {
		return language;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	/*public BitmapFont getFont20() {
		return font20;
	}*/

	public BitmapFont getFont30() {
		return font30;
	}

	public BitmapFont getFont40() {
		return font40;
	}

	public BitmapFont getFont80() {
		return font80;
	}

	public Preferences getPrefs() {
		return prefs;
	}

	public BitmapFont getFont100() {
		return font100;
	}

	@Override
	public void dispose () {
		//prefs.putInteger("highscoreNumH", 0);
		//prefs.putInteger("highscoreNumMN", 0);
		//prefs.putInteger("highscoreNumSEC", 0);
		prefs.flush();
		batch.dispose();
		//font20.dispose();
		font30.dispose();
		font40.dispose();
		font80.dispose();
		font100.dispose();
		generator.dispose();
		menuMusic.dispose();
	}
}
