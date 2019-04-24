package com.example.demozull;

import com.example.demozull.service.IPerson;
import com.example.demozull.service.impl.Student;
import com.example.demozull.service.impl.Teacher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件名：StudentConfig
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-19 14:23
 */
@Configuration
//在application.properties配置"mf.assert"，对应的值为true

public class AppConfig {

    @ConditionalOnProperty(prefix="mf",name = "student", havingValue = "true")
    @Bean
    public IPerson iPerson(){
        return new Student();
    }


    @ConditionalOnProperty(prefix="mf",name = "teacher", havingValue = "true")
    @Bean
    public IPerson iPersonTeacher(){
        return new Teacher();
    }
}
