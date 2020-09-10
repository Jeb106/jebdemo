package com.jeb.demo.module.test.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@KeySequence("SEQ_USER")
@TableName("user2")
public class User2 {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    private String name;
    private Integer age;
    private String email;
}