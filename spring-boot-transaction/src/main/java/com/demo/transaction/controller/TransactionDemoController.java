package com.demo.transaction.controller;

import com.demo.transaction.model.TransAnimal;
import com.demo.transaction.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/trans")
public class TransactionDemoController {

    @Autowired
    private ITransactionService transactionService;

    @RequestMapping("/")
    public String index() {
        return "IndexPage";
    }

    @RequestMapping("/save")
    public boolean save() {
        TransAnimal transAnimal = new TransAnimal();
        transAnimal.setAnimalType(1);
        transAnimal.setAnimalTypeName("陆地动物");
        transAnimal.setCreateTime(new Date());
        transAnimal.setUpdateTime(new Date());
        return transactionService.save(transAnimal);
    }


    @RequestMapping("/saveAll")
    public boolean saveAll() {
        return transactionService.saveAll();
    }

}

