package com.zookeeper.base.service;


import org.springframework.stereotype.Service;

/**
 * @ClassName IZookeeperService
 * @Author FangShanShan
 * @Description zk server 服务
 * @Date 2020/3/29 11:17
 */

public interface IZookeeperService {


    String exists(String nodeName);

    boolean create(String nodeName);
}
