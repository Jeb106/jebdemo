package com.example.demospring.module.user.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName：Person
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-08-18 11:30
 */
@Entity
@Table(name = "person")
@Data
@ToString
public class Person {
	@Id
	@Column(name = "id" , columnDefinition = "varchar(255) comment 'id'")
	private String id;
	@Column(name = "name" , columnDefinition = "varchar(255) comment '姓名'")
	private String name;
}
