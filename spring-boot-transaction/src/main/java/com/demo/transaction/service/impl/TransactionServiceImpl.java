package com.demo.transaction.service.impl;

import com.demo.transaction.dao.SkyAnimalMapper;
import com.demo.transaction.dao.TransAnimalMapper;
import com.demo.transaction.model.SkyAnimal;
import com.demo.transaction.model.TransAnimal;
import com.demo.transaction.service.ISkyAnimalService;
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


    @Autowired
    private ISkyAnimalService skyAnimalService;




    /**
     * 多个数据库 操作，哪次操作出错，程序就断掉，且不可回滚
     */
    @Override
    public boolean noTransaction(String name) {
        SkyAnimal skyAnimal = new SkyAnimal();
        skyAnimal.setSkyAnimalName("乌鸦-" + name);
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
        skyAnimal11.setSkyAnimalName("凤凰-" + name);
        skyAnimal11.setCreateTime(new Date());
        skyAnimal11.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal11);

        return true;
    }

    /**
     * 出现错误之前的代码正常执行
     * 1. 非运行时异常 在方法中抛出 会阻断执行
     * 2. 非运行时异常 try catch ，catch  不阻断，则不会阻断执行
     */
    @Override
    public boolean checkErrorNoTransaction(String name){
        SkyAnimal skyAnimal = new SkyAnimal();
        skyAnimal.setSkyAnimalName("乌鸦-" + name);
        skyAnimal.setCreateTime(new Date());
        skyAnimal.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal);

        /*if(null != name) { // 选择在方法上抛出异常,会阻断执行
            throw new Exception("万恶的异常");
        }*/

        if(null != name) { // try catch  如果捕获异常不处理，不会阻断
            try {
                throw new Exception("万恶的异常");
            } catch (Exception e) {
                //e.printStackTrace();
                return false; // 阻断执行
            }
        }

        SkyAnimal skyAnimal11 = new SkyAnimal();
        skyAnimal11.setSkyAnimalName("凤凰-" + name);
        skyAnimal11.setCreateTime(new Date());
        skyAnimal11.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal11);

        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean propagationRequiredA(String name) {

        // 调用事务
        propagationRequired(name);

        // 1  出错代码 -- 数据库报错
        TransAnimal transAnimal = new TransAnimal();
        transAnimal.setAnimalType(1);
        transAnimal.setAnimalTypeName("陆地动物-" + name);
        transAnimal.setCreateTime(new Date());
        transAnimal.setUpdateTime(new Date());
        transAnimalMapper.insertSelective(transAnimal);


        SkyAnimal skyAnimal11 = new SkyAnimal();
        skyAnimal11.setSkyAnimalName("凤凰-" + name);
        skyAnimal11.setCreateTime(new Date());
        skyAnimal11.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal11);

        return false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public boolean propagationRequiredB(String name) throws Exception {

        // 调用事务
        propagationRequired(name);

        //
        if(null != name) {
            throw new Exception("哇喔哇喔");
        }

        SkyAnimal skyAnimal11 = new SkyAnimal();
        skyAnimal11.setSkyAnimalName("凤凰-" + name);
        skyAnimal11.setCreateTime(new Date());
        skyAnimal11.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal11);

        return false;
    }





    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean propagationSupportsA(String name) {

        skyAnimalService.save(name);


        return true;
    }


    /**
     * @Transactional 使用  方法必须是 public
     * 如果没有 @Transactional
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean propagationRequired(String name) {

        SkyAnimal skyAnimal = new SkyAnimal();
        skyAnimal.setSkyAnimalName("乌鸦-" + name);
        skyAnimal.setCreateTime(new Date());
        skyAnimal.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal);

        return false;
    }




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
    @Transactional
    public boolean rollback(String name){
        SkyAnimal skyAnimal = new SkyAnimal();
        skyAnimal.setSkyAnimalName("乌鸦-rollback-" + name);
        skyAnimal.setCreateTime(new Date());
        skyAnimal.setUpdateTime(new Date());
        skyAnimalMapper.insertSelective(skyAnimal);

        int i = 10/0; // [2]
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
