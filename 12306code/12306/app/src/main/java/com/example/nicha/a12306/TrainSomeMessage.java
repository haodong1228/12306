package com.example.nicha.a12306;

/**
 * Created by nicha on 2017/12/6.
 */

public class TrainSomeMessage {
    private String checi;
    private String start;
    private String start_time;
    private String take_time;
    private String arrive;
    private String arrive_time;

    public TrainSomeMessage( String checi, String start, String start_time, String take_time, String arrive, String arrive_time){
        this.checi = checi;
        this.start = start;
        this.start_time = start_time;
        this.take_time = take_time;
        this.arrive = arrive;
        this.arrive_time = arrive_time;
    }
    public String getCheci() {
        return checi;
    }

    public void setCheci(String checi) {
        this.checi = checi;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTake_time() {
        return take_time;
    }

    public void setTake_time(String take_time) {
        this.take_time = take_time;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

}
