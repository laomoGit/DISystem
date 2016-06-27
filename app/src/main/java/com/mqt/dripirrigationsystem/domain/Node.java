package com.mqt.dripirrigationsystem.domain;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/20.
 */
public class Node implements Serializable{
    private int userId; //用户的id
    private int sysId; //阀门ID
    private String valueName;//阀门名称
    private boolean status;//阀门状态
    private boolean usePattern;//工作模式（true为自动，false为手动）
    private int pressure;//水压值
    //private String sensorsData;//传感器的数据（不进行描述）
    private int sensorT1Value;//一号温度传感器的值
    private int sensorT2Value;
    private int sensorH1Value;//一号湿度传感器的值
    private int sensorH2Value;
    private String recvTime;//用户编辑修改时间
    /*public String Address;
    public String Number;*/

    public Node(JSONObject obj) throws JSONException {
            if (obj.has("userId")) {
                userId = obj.getInt("userId");
            }
            if (obj.has("sysId")) {

                sysId = obj.getInt("sysId");
            }
            if (obj.has("valueName")) {
                valueName = obj.getString("valueName");
            }
            if (obj.has("status")) {
                if(obj.getString("status").equals("true")){
                    status = true;
                }
                if(obj.getString("status").equals("false")){
                    status = false;
                }
            }
            if (obj.has("usePattern")) {
                if(obj.getString("usePattern").equals("true")){
                    usePattern = true;
                }
                if(obj.getString("usePattern").equals("false")){
                    usePattern = false;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSysId() {
        return sysId;
    }

    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getUsePattern() {
        return usePattern;
    }

    public void setUsePattern(Boolean usePattern) {
        this.usePattern = usePattern;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
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

    public String getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(String recvTime) {
        this.recvTime = recvTime;
    }
}
