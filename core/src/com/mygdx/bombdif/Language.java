package com.mygdx.bombdif;

public class Language {
    private String language;
    private String create;
    private String join;
    private String option;
    private String tongue;
    private String volume = "Volume";
    private String diff;
    private String easy;
    private String inter = "normal";
    private String hard;
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
                diff = "Difficulté";
                easy = "facile";
                hard = "dur";
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
                break;
            case "English":
                create = "Create room";
                join = "Join room";
                option = "Option";
                tongue = "Language";
                diff = "Difficulty";
                easy = "easy";
                hard = "harder";
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
                break;
            default:
                create = "";
                join = "";
                option = "";
                tongue = "";
                diff = "";
                easy = "";
                hard = "";
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
        return easy;
    }

    public String getEnterName() {
        return enterName;
    }

    public String getHard() {
        return hard;
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

    public String getInter() {
        return inter;
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
}
