package com.demo.transaction.dao;

import com.demo.transaction.model.TransAnimal;
import org.apache.ibatis.annotations.Mapper;

@Mapper //注意这里的Mapper注解
public interface TransAnimalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trans_animal
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    int insert(TransAnimal record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table trans_animal
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    int insertSelective(TransAnimal record);
}