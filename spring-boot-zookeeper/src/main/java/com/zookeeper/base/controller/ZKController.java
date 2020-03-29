package com.zookeeper.base.controller;

import com.zookeeper.base.service.IZookeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ZKController
 * @Author FangShanShan
 * @Description ZK测试
 * @Date 2020/3/27 19:40
 */
@Controller
public class ZKController {

    @Autowired
    private IZookeeperService zookeeperService;

    @RequestMapping("/exists")
    public String exists(String nodeName) {
        return zookeeperService.exists(nodeName);
    }


}