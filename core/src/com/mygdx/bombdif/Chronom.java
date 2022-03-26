package com.mygdx.bombdif;

public class Chronom {
    private int sec_total;
    private int sec = 0;
    private int min = 0;
    private int hour = 0;
    private boolean counting;
    public Chronom(int sec_total){
        this.sec_total = sec_total;
        convert();
        counting = true;
    }

    public String display(){
        convert();
        return addZero(hour)+":"+addZero(min)+":"+addZero(sec);
    }

    private void convert(){

        if (sec_total/3600 >= 1){
            hour = (int) Math.floor(sec_total/3600);
            sec = sec_total - hour*3600;
            if (sec/60 >= 1){
                min = (int) Math.floor(sec/60);
                sec -= min*60;
            }else {
                min = 0;
            }
        }else if (sec_total/60 >= 1){
            hour = 0;
            min = (int) Math.floor(sec_total/60);
            sec = sec_total - min*60;
        }else {
            min = 0;
            sec = sec_total;
        }
    }

    private String addZero(int n){
        if (n<10){
            return "0"+n;
        }else{
            return Integer.toString(n);
        }
    }

    public boolean updateTimer(){
        sec_total-=1;
        if (sec_total <=0){
            return false;
        }return true;
    }

    public int getSec() {
        return sec_total;
    }

    public void bonusSec(int sec) {
        this.sec_total += sec;
    }

    /*public boolean updateTimer(){
        if (sec == 0 && min==0 && hour==0 ){
            counting = false;
            return false;
        }
        if (sec-1 < 0){
            sec = 59;
            if (min-1 < 0){
                min = 59;
                if (hour- 1 >= 0) {
                    hour-=1;
                }
            }else {
                min -= 1;
            }
        }else {
            sec-=1;
        }
        return counting;
    }*/


}
