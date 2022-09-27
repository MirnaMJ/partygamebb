package com.mygdx.bombdif;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Language {
    private String language;
    private String create;
    private String join;
    private String option;
    private String tongue;
    private String volume = "Volume";
    private String diff;
    private String selecTime;
    private String tap;
    private String swipe;
    private String single;
    private String multi;
    private String shake;
    private String enterName;
    private String chooseName;
    private String nbPlayer;
    private String waiting;
    private String room;
    private String start;
    private String instrucSwipe;
    private String instrucTap;
    private String instrucShake;
    private String nTimes;
    private String newHighscore;
    private String highscore;
    private String miss;
    private String sound;
    private String music;
    private String vibe;

    public Language(String lang) {
        language = lang;
        translate(language);
    }

    public void setLanguage(String language) {
        this.language = language;
        translate(this.language);

    }

    private void translate(String language){
        switch (language) {
            case "Français":
                create = "Créer une salle";
                join = "Rejoindre une salle";
                option = "Option";
                tongue = "Langue";
                diff = "Liste des taches";
                selecTime = "Durée du décompte";
                tap = "Tappe";
                swipe = "Glisse";
                shake = "Secoue";
                single = "Un joueur";
                multi = "Multijoueurs";
                enterName = "Entrer le nom de la salle à rejoindre";
                chooseName = "Entrer le nom de \nla salle à créer";
                nbPlayer = "Nombre de joueur";
                waiting = "En attente de...";
                room = "Salle ";
                start = "COMMENCER";
                instrucSwipe = "GLISSE TON DOIGT SUR\n L'ECRAN";
                instrucTap = "TAPPE L'ECRAN ";
                instrucShake = "SECOUE LE TELEPHONE!";
                nTimes = " FOIS!";
                newHighscore = "Nouveau Records!!!";
                highscore = "Record actuel";
                miss = "Erreurs";
                sound = "Son";
                music = "Musique";
                vibe = "Vibreur";
                break;
            case "English":
                create = "Create room";
                join = "Join room";
                option = "Option";
                tongue = "Language";
                diff = "Task list";
                selecTime = "Timer duration";
                tap = "Tap";
                swipe = "Swipe";
                shake = "Shake";
                single = "Singleplayer";
                multi = "Multiplayer";
                enterName = "Enter the room's name to join:";
                chooseName = "   Name the room \nyou are going to host";
                nbPlayer = "Number of player";
                waiting = "Waiting for...";
                room = "Room ";
                start = "START";
                instrucSwipe = "SWIPE";
                instrucTap = "TAP THE SCREEN ";
                instrucShake = "SHAKE YOUR PHONE!";
                nTimes = " TIMES!";
                newHighscore = "New HighScore!!";
                highscore = "Current High score";
                miss = "Mistakes";
                sound = "Sound";
                music = "Music";
                vibe = "Vibrator";
                break;
            default:
                create = "";
                join = "";
                option = "";
                tongue = "";
                diff = "";
                selecTime = "";
                tap = "";
                swipe = "";
                shake = "";
                single = "";
                multi = "";
                enterName = "";
                chooseName = "";
                nbPlayer = "";
                waiting = "";
                room = "";
                start = "";
                instrucSwipe = "";
                instrucTap = "";
                instrucShake = "";
                nTimes = "";
                newHighscore = "";
                highscore = "";
                miss = "";
                sound = "";
                music = "";
                vibe = "";
        }
    }

    public String getCreate() {
        return create;
    }

    public String getChooseName() {
        return chooseName;
    }

    public String getDiff() {
        return diff;
    }

    public String getEasy() {
        return "easy";
    }
    public String getInter() {
        return "inter";
    }
    public String getHard() {
        return "hard";
    }

    public String getSingle() {
        return single;
    }

    public String getMulti() {
        return multi;
    }

    public String getEnterName() {
        return enterName;
    }

    public String getSelecTime() {
        return selecTime;
    }

    public String getInstrucShake() {
        return instrucShake;
    }

    public String getInstrucSwipe() {
        return instrucSwipe;
    }

    public String getInstrucTap() {
        return instrucTap;
    }

    public String getTap() {
        return tap;
    }

    public String getSwipe() {
        return swipe;
    }

    public String getShake() {
        return shake;
    }

    public String getJoin() {
        return join;
    }

    public String getLanguage() {
        return language;
    }

    public String getNbPlayer() {
        return nbPlayer;
    }

    public String getOption() {
        return option;
    }

    public String getnTimes() {
        return nTimes;
    }

    public String getStart() {
        return start;
    }

    public String getTongue() {
        return tongue;
    }

    public String getVolume() {
        return volume;
    }

    public String getWaiting() {
        return waiting;
    }

    public String getRoom(){return room;}

    public String getNewHighscore() {
        return newHighscore;
    }

    public String getSound() {
        return sound;
    }

    public String getMusic() {
        return music;
    }

    public String getVibe() {
        return vibe;
    }

    public String getHighscore() {
        return highscore;
    }

    public String getMiss() {
        return miss;
    }
}
