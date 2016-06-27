package com.mqt.dripirrigationsystem.service;

import com.mqt.dripirrigationsystem.manager.NodeManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/6/27.
 */
public class NodeService extends BaseService{
    NodeManager manager;
    public NodeService(){}
    @Override
    protected void onResponseToJson(String response) {
        manager = NodeManager.getInstance();
        try {
            JSONObject json = new JSONObject(response);
            JSONArray jsonArray = json.getJSONArray("node");
            manager.saveNode(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
