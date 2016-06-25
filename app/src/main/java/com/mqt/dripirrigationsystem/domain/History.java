package com.mqt.dripirrigationsystem.domain;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/6/25.
 */
public class History {
    private int sysId;
    private Boolean status;
    private int sensorT1Value;
    private int sensorT2Value;
    private int sensorH1Value;
    private int sensorH2Value;
    private int pressure;
    private String recvTime;

    public History(JSONObject obj) throws JSONException {
        if (obj.has("sysId")) {

            sysId = obj.getInt("sysId");
        }
        if (obj.has("status")) {
            if(obj.getString("status").equals("true")){
                status = true;
            }
            if(obj.getString("status").equals("false")){
                status = false;
            }
        }
        if (obj.has("pressure")) {
            pressure = obj.getInt("pressure");
        }
        if (obj.has("sensorT1Value")) {
            sensorT1Value = obj.getInt("sensorT1Value");
        }
        if (obj.has("sensorT2Value")) {
            sensorT2Value = obj.getInt("sensorT2Value");
        }
        if (obj.has("sensorH1Value")) {
            sensorH1Value = obj.getInt("sensorH1Value");
        }
        if (obj.has("sensorH2Value")) {
            sensorH2Value = obj.getInt("sensorH2Value");
        }
        if (obj.has("recvTime")) {
            recvTime = obj.getString("recvTime");
        }
    }

    public int getSysId() {
        return sysId;
    }

    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getSensorT1Value() {
        return sensorT1Value;
    }

    public void setSensorT1Value(int sensorT1Value) {
        this.sensorT1Value = sensorT1Value;
    }

    public int getSensorT2Value() {
        return sensorT2Value;
    }

    public void setSensorT2Value(int sensorT2Value) {
        this.sensorT2Value = sensorT2Value;
    }

    public int getSensorH1Value() {
        return sensorH1Value;
    }

    public void setSensorH1Value(int sensorH1Value) {
        this.sensorH1Value = sensorH1Value;
    }

    public int getSensorH2Value() {
        return sensorH2Value;
    }

    public void setSensorH2Value(int sensorH2Value) {
        this.sensorH2Value = sensorH2Value;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public String getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(String recvTime) {
        this.recvTime = recvTime;
    }
}
