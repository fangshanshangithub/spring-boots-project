package com.zookeeper.base;

import com.zookeeper.base.service.IZookeeperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootZookeeperApplicationTests {

    @Autowired
    private IZookeeperService zookeeperService;

    @Test
    void contextLoads() {
        String info = zookeeperService.exists("test");
        System.out.println("info：" + info);
    }



    @Test
    void create() {
        boolean info = zookeeperService.create("test");
        System.out.println("info：" + info);
    }

}
