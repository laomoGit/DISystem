package com.mqt.dripirrigationsystem.manager;

import com.mqt.dripirrigationsystem.domain.History;
import com.mqt.dripirrigationsystem.domain.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
public class HistoryManager {

    private static HistoryManager manager;
    private List<History> histories;
    private HistoryManager(){
        histories = new ArrayList<History>();
    }

    public static HistoryManager getInstance(){
        if(manager == null){
            manager = new HistoryManager();
        }
        return manager;
    }

    public boolean saveHistory(History history){
        return histories.add(history);
    }

    public boolean removeHistory(History history){
        return histories.remove(history);
    }
}
