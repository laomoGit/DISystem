package com.mqt.dripirrigationsystem.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/20.
 */
public class Node implements Serializable{
    public int SysId;
    public String ValveName;//阀门名称编号
    public Boolean Status;//阀门工作状态
    public Boolean UsePattern;//阀门工作模式
    public Double Pressure;//水压值
    public Double SensorT1Value;//温度
    public Double SensorT2Value;
    public Double SensorH1Value;//湿度
    public Double SensorH2Value;
    public String Address;
    public String Number;

    public Double getSensorT1Value() {
        return SensorT1Value;
    }

    public void setSensorT1Value(Double sensorT1Value) {
        SensorT1Value = sensorT1Value;
    }

    public int getSysId() {
        return SysId;
    }

    public void setSysId(int sysId) {
        SysId = sysId;
    }

    public String getValveName() {
        return ValveName;
    }

    public void setValveName(String valveName) {
        ValveName = valveName;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public Double getPressure() {
        return Pressure;
    }

    public void setPressure(Double pressure) {
        Pressure = pressure;
    }

    public Boolean getUsePattern() {
        return UsePattern;
    }

    public void setUsePattern(Boolean usePattern) {
        UsePattern = usePattern;
    }

    public Double getSensorT2Value() {
        return SensorT2Value;
    }

    public void setSensorT2Value(Double sensorT2Value) {
        SensorT2Value = sensorT2Value;
    }

    public Double getSensorH1Value() {
        return SensorH1Value;
    }

    public void setSensorH1Value(Double sensorH1Value) {
        SensorH1Value = sensorH1Value;
    }

    public Double getSensorH2Value() {
        return SensorH2Value;
    }

    public void setSensorH2Value(Double sensorH2Value) {
        SensorH2Value = sensorH2Value;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
