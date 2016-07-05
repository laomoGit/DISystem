package com.mqt.dripirrigationsystem.domain;

/**
 * Created by Administrator on 2016/7/1.
 */
public class Clock {
    private String clockTime;
    private String clockContent;
    private String clockSpace;//距离时间
    private boolean isClock;//开启闹钟
    private String ringSong;//铃声
    private boolean isVibrate;

    public String getRingSong() {
        return ringSong;
    }

    public void setRingSong(String ringSong) {
        this.ringSong = ringSong;
    }

    public boolean isVibrate() {
        return isVibrate;
    }

    public void setVibrate(boolean vibrate) {
        isVibrate = vibrate;
    }

    public Clock(){}

    public String getClockTime() {
        return clockTime;
    }

    public void setClockTime(String clockTime) {
        this.clockTime = clockTime;
    }

    public String getClockContent() {
        return clockContent;
    }

    public void setClockContent(String clockContent) {
        this.clockContent = clockContent;
    }

    public String getClockSpace() {
        return clockSpace;
    }

    public void setClockSpace(String clockSpace) {
        this.clockSpace = clockSpace;
    }

    public boolean isClock() {
        return isClock;
    }

    public void setClock(boolean clock) {
        isClock = clock;
    }
}
