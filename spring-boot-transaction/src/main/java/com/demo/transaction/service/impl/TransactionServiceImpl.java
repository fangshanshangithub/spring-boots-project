package com.demo.transaction.service.impl;

import com.demo.transaction.dao.SkyAnimalMapper;
import com.demo.transaction.dao.TransAnimalMapper;
import com.demo.transaction.model.SkyAnimal;
import com.demo.transaction.model.TransAnimal;
import com.demo.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
