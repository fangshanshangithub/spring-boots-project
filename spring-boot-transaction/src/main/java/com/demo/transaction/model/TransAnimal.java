package com.demo.transaction.model;

import java.util.Date;

public class TransAnimal {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_animal.id
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_animal.animal_type
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    private Integer animalType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_animal.animal_type_name
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    private String animalTypeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_animal.update_time
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column trans_animal.create_time
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_animal.id
     *
     * @return the value of trans_animal.id
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_animal.id
     *
     * @param id the value for trans_animal.id
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_animal.animal_type
     *
     * @return the value of trans_animal.animal_type
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    public Integer getAnimalType() {
        return animalType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_animal.animal_type
     *
     * @param animalType the value for trans_animal.animal_type
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    public void setAnimalType(Integer animalType) {
        this.animalType = animalType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_animal.animal_type_name
     *
     * @return the value of trans_animal.animal_type_name
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    public String getAnimalTypeName() {
        return animalTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_animal.animal_type_name
     *
     * @param animalTypeName the value for trans_animal.animal_type_name
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    public void setAnimalTypeName(String animalTypeName) {
        this.animalTypeName = animalTypeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_animal.update_time
     *
     * @return the value of trans_animal.update_time
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_animal.update_time
     *
     * @param updateTime the value for trans_animal.update_time
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column trans_animal.create_time
     *
     * @return the value of trans_animal.create_time
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column trans_animal.create_time
     *
     * @param createTime the value for trans_animal.create_time
     *
     * @mbg.generated Tue Mar 24 00:55:30 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



}