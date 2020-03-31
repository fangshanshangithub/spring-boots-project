package com.zookeeper.base.zk.util;

import com.zookeeper.base.config.ZookeeperConfig;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

//https://blog.csdn.net/lovexiaotaozi/article/details/83382128
@Component
public class ZkClientUtil {

    @Autowired
    private ZookeeperConfig zookeeperConfig;


    //分布式锁,用于挂起当前线程,等待上一把分布式锁释放
    private static CountDownLatch DISTRIBUTE_LOCK = new CountDownLatch(1);
    //分布式锁节点名
    private final static String DISTRIBUTE_LOCK_NAME = "distribute-lock";

    /**
     * 开启zk连接
     * @param sleepTime 定义失败重试间隔时间 单位:毫秒
     * @param maxRetries 定义失败重试次数
     * @param sessionTimeOut  定义会话存活时间,根据业务灵活指定 单位:毫秒
     * @param zkServerIp zkurl和端口号
     * @param nameSpace 工作空间,可以不指定,建议指定,功能类似于项目包,之后创建的所有的节点都会在该工作空间下,方便管理
     * @return
     */
    public  CuratorFramework build(Integer sleepTime, Integer maxRetries, Integer sessionTimeOut,String zkServerIp, String nameSpace){
        //创建比较简单,链式编程指定点参数就ok了
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(sleepTime,maxRetries);//重试策略
        CuratorFramework client = CuratorFrameworkFactory
                .builder()
                .connectString(zkServerIp)
                .retryPolicy(retryPolicy)
                .namespace(nameSpace)
                .sessionTimeoutMs(sessionTimeOut)
                .build();
        return client;
    }

    public  CuratorFramework build(){
       return build(3000, 3 , 60000, zookeeperConfig.getConnectString(),  "fangss");
    }



    /**
     * 获取分布式锁
     */
    public  void getLock() {
        CuratorFramework client = build();
        client.start();
        while (true) {
            try {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/ZkClientUtil");
                System.out.println("获取分布式锁成功...");
                return;
            } catch (Exception e) {
                try {
                    //如果没有获取到锁,需要重新设置同步资源值
                    if (DISTRIBUTE_LOCK.getCount() <= 0) {
                        DISTRIBUTE_LOCK = new CountDownLatch(1);
                    }
                    System.out.println("获取分布式锁失败,等待他人释放锁中...");
                    DISTRIBUTE_LOCK.await();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    /**
     * 释放锁资源
     */
    public  void  release(String path) {
        CuratorFramework client = build();
        client.start();
        try {
            client.delete().forPath(path);
            System.out.println("锁释放成功...");
        } catch (Exception e) {
            System.out.println("释放锁失败...");
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    /**
     * 为指定路径节点创建watch,观察锁状态
     */
    public  void addWatcher2Path(final String path) throws Exception {
        CuratorFramework client = build();
        client.start();
        final PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path, true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        System.out.println("创建观察者成功...");
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                if (pathChildrenCacheEvent.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                    String nodePath = pathChildrenCacheEvent.getData().getPath();
                    System.out.println("上一会话已释放锁或会话已断开...节点路径为:" + nodePath);
                    if (nodePath.contains(DISTRIBUTE_LOCK_NAME)) {
                        DISTRIBUTE_LOCK.countDown();
                        System.out.println("释放计数器,计数器值为:"+DISTRIBUTE_LOCK.getCount()+"让当前请求来获取分布式锁...");
                    }
                }
            }
        });
    }


}
