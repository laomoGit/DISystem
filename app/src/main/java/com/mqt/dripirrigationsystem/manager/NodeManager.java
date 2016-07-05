package com.mqt.dripirrigationsystem.manager;

import com.mqt.dripirrigationsystem.domain.Node;
import com.mqt.dripirrigationsystem.domain.User;
import com.mqt.dripirrigationsystem.utils.LogInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
public class NodeManager {
    private static NodeManager manager;
    private ArrayList<Node> nodes;
    private NodeManager(){
        nodes = new ArrayList<Node>();
    }

    public static NodeManager getInstance(){
        if(manager == null){
            manager = new NodeManager();
        }
        return manager;
    }

    public boolean saveNode(JSONArray array){
        Node node = null;
        for(int i=0;i<array.length();i++){
            try {
                node = new Node(array.getJSONObject(i));
                nodes.add(node);

                LogInfo.info("usep="+node.getUsePattern());
                LogInfo.info(node.getPressure()+"");
                LogInfo.info(node.getStatus()+"");
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;

    }

    public void modifyNode(Node node,boolean isAuto){
        for(Node n:nodes){
            if(node.getSysId()==n.getSysId()){
                n.setUsePattern(isAuto);
            }
        }
    }

    public void modifyNode(Node node,int pressure){
        for(Node n:nodes){
            if(n.getPressure() == node.getPressure()){
                n.setPressure(pressure);
            }
        }
    }

    public int getNodeCount(){
        return nodes.size();
    }

    public Node getNode(int position){
        return nodes.get(position);
    }

    public ArrayList<Node> getNodes(){
        return nodes;
    }
    public boolean removeNode(Node node){
        return nodes.remove(node);
    }
}
