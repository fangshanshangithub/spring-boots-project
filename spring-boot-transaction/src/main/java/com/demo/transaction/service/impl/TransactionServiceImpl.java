package com.demo.transaction.service.impl;

import com.demo.transaction.dao.SkyAnimalMapper;
import com.demo.transaction.dao.TransAnimalMapper;
import com.demo.transaction.model.SkyAnimal;
import com.demo.transaction.model.TransAnimal;
import com.demo.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransAnimalMapper transAnimalMapper;

    @Autowired
    private SkyAnimalMapper skyAnimalMapper;


    @Override
    public boolean save(TransAnimal transAnimal) {
        int count = transAnimalMapper.insertSelective(transAnimal);
        return count > 0;
    }

    @Override
    public boolean saveAll(String name) {

        SkyAnimal skyAnimal = new SkyAnimal();
        skyAnimal.setSkyAnimalName("公乌鸦-" + name);
        skyAnimal.setCreateTime(new Date());
        skyAnimal.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal);


        TransAnimal transAnimal = new TransAnimal();
        transAnimal.setAnimalType(1);
        transAnimal.setAnimalTypeName("陆地动物-" + name);
        transAnimal.setCreateTime(new Date());
        transAnimal.setUpdateTime(new Date());
        transAnimalMapper.insertSelective(transAnimal);


        SkyAnimal skyAnimal11 = new SkyAnimal();
        skyAnimal11.setSkyAnimalName("母乌鸦-" + name);
        skyAnimal11.setCreateTime(new Date());
        skyAnimal11.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal11);


        return true;
    }


    /**
     * 1.没有 Transactional 事务注解没有回滚，报错之前的代码正常执行
     * 2.@Transactional(rollbackFor = Exception.class) 出现异常回滚
     */
    @Override
    //@Transactional(rollbackFor = Exception.class) // [1]
    //public boolean rollback(String name) throws Exception { // [3]
    public boolean rollback(String name){
        SkyAnimal skyAnimal = new SkyAnimal();
        skyAnimal.setSkyAnimalName("乌鸦-rollback-" + name);
        skyAnimal.setCreateTime(new Date());
        skyAnimal.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal);

        //int i = 10/0; // [2]
        /* // [3]
        if(null != name) {
            throw  new Exception("AAAA");
        }
        */

        SkyAnimal skyAnimal11 = new SkyAnimal();
        skyAnimal11.setSkyAnimalName("凤凰-rollback-" + name);
        skyAnimal11.setCreateTime(new Date());
        skyAnimal11.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal11);

        return false;
    }

    @Override
    @Transactional
    public boolean checkError(String name) throws Exception {


        if(name != null) {
                throw new Exception("万恶的异常输出");
        }


        SkyAnimal skyAnimal = new SkyAnimal();
        skyAnimal.setSkyAnimalName("公乌鸦-" + name);
        skyAnimal.setCreateTime(new Date());
        skyAnimal.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal);




        SkyAnimal skyAnimal11 = new SkyAnimal();
        skyAnimal11.setSkyAnimalName("母乌鸦-" + name);
        skyAnimal11.setCreateTime(new Date());
        skyAnimal11.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal11);


        return false;
    }
}
