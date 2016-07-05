package com.mqt.dripirrigationsystem.domain;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/7/1.
 */
public class Weather {
    private String weatherId;
    private int airTemp;
    private int airHum;
    private int airPressure;
    private int airSpeed;
    private int illumination;
    private int radiation;
    private String recvTime;

    public Weather(){}

    public Weather(JSONObject obj)throws JSONException {
        if (obj.has("weatherId")) {
            weatherId = obj.getString("weatherId");
        }
        if (obj.has("airTemp")) {

            airTemp = obj.getInt("airTemp");
        }
        if (obj.has("airHum")) {
            airHum = obj.getInt("airHum");
        }
        if (obj.has("airPressure")) {
            airPressure = obj.getInt("airPressure");
        }
        if (obj.has("airSpeed")) {
            airSpeed = obj.getInt("airSpeed");
        }
        if (obj.has("illumination")) {
            illumination = obj.getInt("illumination");
        }
        if (obj.has("radiation")) {
            radiation = obj.getInt("radiation");
        }
        if (obj.has("recvTime")) {
            recvTime = obj.getString("recvTime");
        }
    }

    public int getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(int airTemp) {
        this.airTemp = airTemp;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getAirHum() {
        return airHum;
    }

    public void setAirHum(int airHum) {
        this.airHum = airHum;
    }

    public int getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(int airPressure) {
        this.airPressure = airPressure;
    }

    public int getAirSpeed() {
        return airSpeed;
    }

    public void setAirSpeed(int airSpeed) {
        this.airSpeed = airSpeed;
    }

    public int getIllumination() {
        return illumination;
    }

    public void setIllumination(int illumination) {
        this.illumination = illumination;
    }

    public int getRadiation() {
        return radiation;
    }

    public void setRadiation(int radiation) {
        this.radiation = radiation;
    }

    public String getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(String recvTime) {
        this.recvTime = recvTime;
    }
}
