package com.mygdx.bombdif;

public class GameRulesManager {
    private int nbPlayer;
    private String difficulty;
    private String roomName;
    private String testPlayer;
    public GameRulesManager(Language lang){
        nbPlayer = 1;
        testPlayer = "Mineko";
        difficulty = lang.getInter();
        roomName = "";
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
}
