CREATE DATABASE trans_demo;

DROP TABLE IF EXISTS trans_animal;
CREATE TABLE trans_animal (
	id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '记录主键id',
	animal_type INT(2) NOT NULL DEFAULT 0 COMMENT '动物类别',
	animal_type_name VARCHAR(50) NOT NULL DEFAULT '' COMMENT '动物类别名称',
	update_time DATETIME DEFAULT NULL COMMENT '更新时间',
	create_time DATETIME DEFAULT NULL COMMENT '创建时间'
);


DROP TABLE IF EXISTS sky_animal;
CREATE TABLE sky_animal (
	id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '记录主键id',
	sky_animal_name VARCHAR(50) NOT NULL DEFAULT '' COMMENT '动物名称',
	update_time DATETIME DEFAULT NULL COMMENT '更新时间',
	create_time DATETIME DEFAULT NULL COMMENT '创建时间'
);



## 增加唯一索引
ALTER TABLE `trans_animal` ADD UNIQUE (`animal_type_name`);

## 删除唯一索引
ALTER TABLE `trans_animal` ADD UNIQUE (`animal_type_name`);
