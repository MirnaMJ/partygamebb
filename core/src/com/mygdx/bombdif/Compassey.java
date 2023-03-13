package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;

public class Compassey extends Challenge{
    private String[] instruction;
    private String[] direction;
    private int[][] zones;
    private float[][] colors;
    private int curZone;
    private int pick;
    private String id;

    public Compassey(Language lang){
        super();
        direction = new String[8];
        direction[0] = lang.getN();
        direction[1] = lang.getNe();
        direction[2] = lang.getEa();
        direction[3] = lang.getSe();
        direction[4] = lang.getSud();
        direction[5] = lang.getSo();
        direction[6] = lang.getO();
        direction[7] = lang.getNo();
        pick = (int)(Math.random() * 8);//Min + (int)(Math.random() * ((Max - Min) + 1))
        instruction = new String[2];
        instruction[0] = lang.getInstrucCompass();
        instruction[1] = direction[pick];
        zones = new int[][]{{-22, 22}, {22, 67}, {67, 112}, {112, 157}, {-157, 157}, {-157, -112}, {-112, -67}, {-67, -22}};
        colors = new float[][]{{0f,1f,0f},{1f,1f,0f},{1f,0.6f,0.4f},{1f,0.4f,0.4f}};//{green,yellow,orange,red}
        id = "compass";
    }

    @Override
    public String getInstruc() {
        return instruction[0]+instruction[1];
    }

    @Override
    public void updateState() {
        switch(pick) {
            case 0:
                if ((Gdx.input.getAzimuth() <= Math.abs(22))) {
                    this.setDone();
                }
                break;
            case 1:
                if ((Gdx.input.getAzimuth() <= 67 && Gdx.input.getAzimuth() >= 23)) {
                    this.setDone();
                }
                break;
            case 2:
                if ((Gdx.input.getAzimuth() <= 112 && Gdx.input.getAzimuth() >= 68)) {
                    this.setDone();
                }
                break;
            case 3:
                if ((Gdx.input.getAzimuth() <= 157 && Gdx.input.getAzimuth() >= 113)) {
                    this.setDone();
                }
                break;
            case 4:
                if ((Gdx.input.getAzimuth() <= Math.abs(180) && Gdx.input.getAzimuth() >= Math.abs(158))) {
                    this.setDone();
                }
                break;
            case 5:
                if ((Gdx.input.getAzimuth() <= -113 && Gdx.input.getAzimuth() >= -157)) {
                    this.setDone();
                }
                break;
            case 6:
                if ((Gdx.input.getAzimuth() <= -68 && Gdx.input.getAzimuth() >= -112)) {
                    this.setDone();
                }
                break;
            case 7:
                if ((Gdx.input.getAzimuth() <= -23 && Gdx.input.getAzimuth() >= -67)) {
                    this.setDone();
                }
                break;
            default:
                System.out.println("compay: number picked wasnt  between 0 znd 7");
                break;
        }
    }

    @Override
    public String getId() {
        return id;
    }

    public float[] currentZone(float az) {
        int i =0;
        curZone = 99;
        while(curZone==99){
            System.out.println("compassey: zones(i) ["+zones[i][0]+","+zones[i][1]+"] "+i);
            System.out.println("compassey: azimuth: "+az);
            if (Math.abs(az)>=157 ){
                curZone = 4;
                System.out.println("compassey: is it south? "+az);
            }else if (zones[i][0]<=az && az<=zones[i][1]){
                curZone = i;
                System.out.println("compassey: current zone "+i+" "+zones[i][0]+" , "+zones[i][1]+" zones[i]");
            }else{
                System.out.println("compassey: couldnt find the curre zone gosh "+az);
            }
            i++;
        }
        for (int j=0;j< colors.length;j++){
            if (pick+j+1 > 7){
                switch (pick){
                    case 5:
                        if (curZone == 0) {
                            return colors[2];//dont search logic im hardcoding here, do a table on what color with colum goal and line curZone if you want to check
                        }
                    case 6:
                        if (pick+j+1 == 8 && curZone == 0){
                            return colors[1];
                        }
                        if (pick+j+1 == 9 && curZone == 1){
                            return colors[2];
                        }
                    case 7:
                        if (pick+j+1 == 8 && curZone == 0){
                            return colors[0];
                        }
                        if (pick+j+1 == 9 && curZone == 1){
                            return colors[2];
                        }
                        if (pick+j+1 == 10 && curZone == 2){
                            return colors[2];
                        }
                }
            }else if (pick-(j+1) < 0){
                switch (pick){
                    case 0:
                        if (pick-(j+1) == -3 && curZone == 5){
                            return colors[2];
                        }
                        if (pick-(j+1) == -2 && curZone == 6){
                            return colors[1];
                        }
                        if (pick-(j+1) == -1 && curZone == 7){
                            return colors[0];
                        }
                    case 1:
                        if (pick-(j+1) == -2 && curZone == 6){
                            return colors[2];
                        }
                        if (pick-(j+1) == -1 && curZone == 7){
                            return colors[1];
                        }
                    case 2:
                        if (pick-(j+1) == -1 && curZone == 7){
                            return colors[2];
                        }
                }
            }else if (curZone == pick+j+1 || curZone == pick-(j+1)){
                return colors[j];
            }
        }
        return new float[]{0f,0f,0f};
    }
}
