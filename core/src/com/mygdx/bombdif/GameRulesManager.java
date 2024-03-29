package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameRulesManager {
    private int nbPlayer;
    private String difficulty;
    private String roomName;
    private String testPlayer;
    private int hour = 0;
    private int min = 0;
    private int sec = 20;
    private int countdown;
    private String[] challenge;
    private int score;
    private String highscore;
    private int[] hmsScore;
    private float volumeS;
    private float volumeM;
    private boolean vibe;
    private int miss;
    private boolean register_score;

    public GameRulesManager(Language lang, Preferences prefs){
        //prfs.putString("name", "Donald Duck");

        /*String name = prefs.getString("name", "No name stored");
        prefs.putBoolean("soundOn", true);*/
        //prefs.putInteger("nbPlayer",2);
        //prefs.putString("highscore","");
        //System.out.println("gamerulesmanager "+prefs.getInteger("highscoreH"));
        if (!prefs.contains("highscoreNumSEC")){
            System.out.println("gamerulemanager:   creatin pref");
            prefs.putInteger("highscoreNumH",0);
            prefs.putInteger("highscoreNumMN", 0);
            prefs.putInteger("highscoreNumSEC", 0);
            prefs.putInteger("mistake", 0);
            prefs.putFloat("volumeS", 0.7f);
            prefs.putFloat("volumeBS", 0.7f);
            prefs.putFloat("volumeM", 0.3f);
            prefs.putBoolean("vibe", true);
            prefs.putBoolean("colorHelp", true);

        }else {
            System.out.println("amrulmanar: not creatin pref");
        }
        nbPlayer = prefs.getInteger("nbPlayer",2);
        testPlayer = prefs.getString("testPlayer","Mineko");
        highscore = prefs.getString("highscore");
        volumeS = prefs.getFloat("volumeS");
        volumeM = prefs.getFloat("volumeM");
        vibe = prefs.getBoolean("vibe");
        prefs.flush();
        //difficulty = lang.getInter();
        roomName = "";
        countdown = hour*3600+min*60+sec;


    }

    public int getCountdown() {
        return countdown;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }

    public int getNbPlayer() {
        return nbPlayer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getRoomName() {
        return roomName;
    }

    public String[] getChallenge() {
        return challenge;
    }

    public int getScore() {
        return score;
    }

    public int[] getHmsScore() {
        return hmsScore;
    }

    public String getTestPlayer() {
        return testPlayer;
    }

    public int getMiss() {
        return miss;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setScore(int score) {
        this.score = score;
        hmsScore = new int[]{0, 0, 0};
        if (score/3600 >= 1){
            hmsScore[0] = (int) Math.floor(score/3600);
            hmsScore[2] = score - hmsScore[0]*3600;
            if (hmsScore[2]/60 >= 1){
                hmsScore[1] = (int) Math.floor(hmsScore[2]/60);
                hmsScore[2] -= hmsScore[1]*60;
            }else {
                hmsScore[2] = 0;
            }
        }else if (score/60 >= 1){
            hmsScore[0] = 0;
            hmsScore[1] = (int) Math.floor(score/60);
            hmsScore[2] = score - hmsScore[1]*60;
        }else {
            hmsScore[1] = 0;
            hmsScore[2] = score;
        }
    }

    public void setTestPlayer(String testPlayer) {
        this.testPlayer = testPlayer;
    }

    public void setCountdown(int hour, int min, int sec) {
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.countdown = hour*3600+min*60+sec;
    }

    public void setChallenge(String[] challenge, int nb) {
        if (nb != 0) {
            this.challenge = new String[nb];
            int j =0;
            for (int i = 0; i< challenge.length;i++){
                if (challenge[i]!= null){
                    this.challenge[j] = challenge[i];
                    j++;
                }
            }
            //System.out.println(this.challenge[0]);
        }else {
            this.challenge = new String[]{"tap", "swipe"};
        }
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }

    public void setRegister_score(boolean register_score) {
        this.register_score = register_score;
    }

    public boolean isRegister_score() {
        return register_score;
    }
}
