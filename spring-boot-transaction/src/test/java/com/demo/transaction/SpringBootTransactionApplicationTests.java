package com.demo.transaction;

import com.demo.transaction.model.TransAnimal;
import com.demo.transaction.service.ITransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringBootTransactionApplicationTests {


    @Autowired
    private ITransactionService transactionService;

    @Test
    void contextLoads() {
        TransAnimal transAnimal = new TransAnimal();
        transAnimal.setAnimalType(1);
        transAnimal.setAnimalTypeName("陆地动物");
        transAnimal.setCreateTime(new Date());
        transAnimal.setUpdateTime(new Date());
        boolean flag =  transactionService.save(transAnimal);

    }

}
