package com.mygdx.bombdif;


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
    private String pointTo;
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
    private String instrucCompass;
    private String n;
    private String sud;
    private String ea;
    private String o;
    private String no;
    private String ne;
    private String se;
    private String so;
    private String nTimes;
    private String newHighscore;
    private String highscore;
    private String miss;
    private String sound;
    private String bombSound = "Explosion";;
    private String music;
    private String vibe;
    private String assistColor;
    private String foetusdeletus;
    private String infoscore;

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
            case "  Français  ":
                create = "JOUER";
                join = "Rejoindre une salle";
                option = "Option";
                tongue = "Langue";
                diff = "Liste des taches";
                selecTime = "Durée du décompte";
                tap = "Tape";
                swipe = "Balaye";
                shake = "Secoue";
                pointTo = "Direction";
                single = "    Créer une partie    ";
                multi = "Multijoueurs";
                enterName = "Entrer le nom de la salle à rejoindre";
                chooseName = "Entrer le nom de \nla salle à créer";
                nbPlayer = "Nombre de joueur";
                waiting = "En attente de...";
                room = "Salle ";
                start = "COMMENCER";
                instrucSwipe = "BALAYE L'ECRAN!";
                instrucTap = "TAPE ";
                instrucShake = "SECOUE LE TELEPHONE!";
                instrucCompass = "POINTE ";
                n = "AU NORD!";
                sud = "AU SUD!";
                ea = "AU EST!";
                o = "AU OUEST";
                no = "AU NORD OUEST!";
                ne = "AU NORD EST!";
                se = "AU SUD EST!";
                so = "AU SUD OUEST!";
                nTimes = " FOIS!";
                newHighscore = "Nouveau Records!!!";
                highscore = "   Record actuel   ";
                miss = "Erreurs";
                sound = "Son";
                music = "Musique";
                vibe = "Vibreur";
                assistColor = "Indice direction couleur";
                foetusdeletus = " Supprimer score";
                infoscore = "** Si une des taches est exclue,\n le record ne sera pas enregistré.";
                break;
            case "  English  ":
                create = "PLAY";
                join = "Join room";
                option = " Option ";
                tongue = "Language";
                diff = "Task list";
                selecTime = "Timer duration";
                tap = "Tap";
                swipe = "Swipe";
                shake = "Shake";
                pointTo = "Point to";
                single = "    Create a game    ";
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
                instrucCompass = "POINT ";
                n = "NORTH";
                sud = "SOUTH";
                ea = "EAST";
                o = "WEST";
                no = "NORTH WEST";
                ne = "NORTH EAST";
                se = "SOUTH EAST";
                so = "SOUTH WEST";
                newHighscore = "New HighScore!!";
                highscore = "    Current High score    ";
                miss = "Mistakes";
                sound = "Sound";
                music = "Music";
                vibe = "Vibrator";
                assistColor = "Color indicator for direction";
                foetusdeletus = "Delete Score";
                infoscore = "** If one of the tasks aren't checked,\n the highscore won't be registered";
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
                pointTo = "";
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
                instrucCompass = "";
                n = "";
                sud = "";
                ea = "";
                o = "";
                no = "";
                ne = "";
                se = "";
                so = "";
                newHighscore = "";
                highscore = "";
                miss = "";
                sound = "";
                music = "";
                vibe = "";
                assistColor = "";
                foetusdeletus = "";
                infoscore = "";
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
    /*public String getInter() {
        return "inter";
    }*/
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

    public String getInstrucCompass(){
        return instrucCompass;
    }

    public String getN() {
        return n;
    }

    public String getNe() {
        return ne;
    }

    public String getEa() {
        return ea;
    }

    public String getSe() {
        return se;
    }

    public String getSud() {
        return sud;
    }

    public String getSo() {
        return so;
    }

    public String getO() {
        return o;
    }

    public String getNo() {
        return no;
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

    public String getPointTo(){
        return pointTo;
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

    public String getBombSound() {
        return bombSound;
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

    public String getAssistColor() {
        return assistColor;
    }

    public String getFoetusdeletus() {
        return foetusdeletus;
    }

    public String getInfoscore() {
        return infoscore;
    }
}
