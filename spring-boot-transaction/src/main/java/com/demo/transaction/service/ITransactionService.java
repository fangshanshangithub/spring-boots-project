package com.demo.transaction.service;

import com.demo.transaction.model.TransAnimal;

public interface ITransactionService {

    boolean save(TransAnimal transAnimal);

    boolean saveAll(String name);


    boolean rollback(String name) throws Exception;

    boolean checkError(String name) throws Exception;
}
