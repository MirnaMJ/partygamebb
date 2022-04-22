package com.mygdx.bombdif;

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

    public GameRulesManager(Language lang){
        nbPlayer = 2;
        testPlayer = "Mineko";
        difficulty = lang.getInter();
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

    public String getTestPlayer() {
        return testPlayer;
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

    public void setTestPlayer(String testPlayer) {
        this.testPlayer = testPlayer;
    }

    public void setCountdown(int hour, int min, int sec) {
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.countdown = hour*3600+min*60+sec;
    }

    public void setChallenge(String[] challenge) {
        this.challenge = challenge;
    }
}
