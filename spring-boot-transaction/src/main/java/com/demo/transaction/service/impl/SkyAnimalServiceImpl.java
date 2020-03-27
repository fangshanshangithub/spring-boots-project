package com.demo.transaction.service.impl;

import com.demo.transaction.dao.SkyAnimalMapper;
import com.demo.transaction.model.SkyAnimal;
import com.demo.transaction.service.ISkyAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.Date;

/**
 * @ClassName SkyAnimalServiceImpl
 * @Author FangShanShan
 * @Description ISkyAnimalService
 * @Date 2020/3/27 18:00
 */
@Service
public class SkyAnimalServiceImpl implements ISkyAnimalService {

    @Autowired
    private SkyAnimalMapper skyAnimalMapper;


    @Override
    public void save(String name) {
        SkyAnimal skyAnimal11 = new SkyAnimal();
        skyAnimal11.setSkyAnimalName("凤凰-" + name);
        skyAnimal11.setCreateTime(new Date());
        skyAnimal11.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal11);
    }
}