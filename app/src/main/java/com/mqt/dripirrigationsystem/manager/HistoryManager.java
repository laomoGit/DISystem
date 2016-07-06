package com.mqt.dripirrigationsystem.manager;

import com.mqt.dripirrigationsystem.domain.History;
import com.mqt.dripirrigationsystem.domain.Node;
import com.mqt.dripirrigationsystem.utils.LogInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
public class HistoryManager {
    public List<History> histories;
    public HistoryManager(){
            histories = new ArrayList<History>();
    }


    public boolean saveHistories(String resp){
        try {
            JSONObject json = new JSONObject(resp);
            JSONArray array = json.getJSONArray("history");

            History history = null;
            for(int i=0;i<array.length();i++) {
                history = new History(array.getJSONObject(i));
                histories.add(history);

                LogInfo.info("history---sysid=" + history.getSysId());
            }

        } catch (JSONException e) {
            e.printStackTrace();
                return false;
        }
        return true;

    }
    public boolean saveHistory(History history){
        return histories.add(history);
    }

    public boolean removeHistory(History history){
        return histories.remove(history);
    }
}
