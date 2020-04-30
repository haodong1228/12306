package com.example.nicha.a12306;

/**
 * Created by nicha on 2017/11/9.
 */

public class Train {
    private String checi;
    private int imageId_start;
    private String start;
    private String start_time;
    private int imageId_card;
    private String take_time;
    private int imageId_arrive;
    private String arrive;
    private String arrive_time;
    private String shangwu_ticket;
    private String yideng_ticket;
    private String erdeng_ticket;
    private String wuzuo_ticket;

    public Train( String checi, int imageId_start, String start, String start_time, int imageId_card,
             String take_time, int imageId_arrive, String arrive, String arrive_time, String shangwu_ticket,
             String yideng_ticket, String erdeng_ticket, String wuzuo_ticket){
        this.checi = checi;
        this.imageId_start = imageId_start;
        this.start = start;
        this.start_time = start_time;
        this.imageId_card = imageId_card;
        this.take_time = take_time;
        this.imageId_arrive = imageId_arrive;
        this.arrive = arrive;
        this.arrive_time = arrive_time;
        this.shangwu_ticket = shangwu_ticket;
        this.yideng_ticket = yideng_ticket;
        this.erdeng_ticket = erdeng_ticket;
        this.wuzuo_ticket = wuzuo_ticket;
    }
    public String getCheci() {
        return checi;
    }

    public void setCheci(String checi) {
        this.checi = checi;
    }

    public int getImageId_start() {
        return imageId_start;
    }

    public void setImageId_start(int imageId_start) {
        this.imageId_start = imageId_start;
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

    public int getImageId_card() {
        return imageId_card;
    }

    public void setImageId_card(int imageId_card) {
        this.imageId_card = imageId_card;
    }

    public String getTake_time() {
        return take_time;
    }

    public void setTake_time(String take_time) {
        this.take_time = take_time;
    }

    public int getImageId_arrive() {
        return imageId_arrive;
    }

    public void setImageId_arrive(int imageId_arrive) {
        this.imageId_arrive = imageId_arrive;
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

    public String getShangwu_ticket() {
        return shangwu_ticket;
    }

    public void setShangwu_ticket(String shangwu_ticket) {
        this.shangwu_ticket = shangwu_ticket;
    }

    public String getYideng_ticket() {
        return yideng_ticket;
    }

    public void setYideng_ticket(String yideng_ticket) {
        this.yideng_ticket = yideng_ticket;
    }

    public String getErdeng_ticket() {
        return erdeng_ticket;
    }

    public void setErdeng_ticket(String erdeng_ticket) {
        this.erdeng_ticket = erdeng_ticket;
    }

    public String getWuzuo_ticket() {
        return wuzuo_ticket;
    }

    public void setWuzuo_ticket(String wuzuo_ticket) {
        this.wuzuo_ticket = wuzuo_ticket;
    }
}
