package com.zookeeper.base.service.impl;

import com.google.gson.Gson;
import com.zookeeper.base.service.IZookeeperService;
import com.zookeeper.base.zk.ZkApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ZookeeperServiceImpl
 * @Author FangShanShan
 * @Description zk 服务
 * @Date 2020/3/29 16:32
 */
@Service
public class ZookeeperServiceImpl implements IZookeeperService {

    @Autowired
    private ZkApiUtil zkApiUtil;



    @Override
    public String exists(String nodeName) {
        Gson gson = new Gson();
        return gson.toJson(zkApiUtil.exists(nodeName, false));
    }

    @Override
    public boolean create(String nodeName) {
        boolean flag = zkApiUtil.createNode(nodeName,"aaData");
        return flag;
    }
}