package com.jeb.springboot.demo.springmvc.controller;

import com.jeb.springboot.demo.springmvc.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件名：MvcController
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-05-09 22:42
 */
@RestController
public class MvcController {

    @GetMapping("/person/{id}")
    public Person person(@PathVariable Integer id, @RequestParam(required = false) String name) {

        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }
}
