package com.mqt.dripirrigationsystem.manager;

import com.mqt.dripirrigationsystem.domain.Node;
import com.mqt.dripirrigationsystem.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
public class NodeManager {
    private static NodeManager manager;
    private List<Node> nodes;
    private NodeManager(){
        nodes = new ArrayList<Node>();
    }

    public static NodeManager getInstance(){
        if(manager == null){
            manager = new NodeManager();
        }
        return manager;
    }

    public boolean saveNode(Node node){
        return nodes.add(node);
    }

    public boolean removeNode(Node node){
        return nodes.remove(node);
    }
}
