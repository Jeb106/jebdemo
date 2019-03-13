package com.example.demospring.module.springaop.service.impl;

import com.example.demospring.module.springaop.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 文件名：LoginServiceImpl
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-12 09:56
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public boolean login() {
        log.info("==========login=========");
        return true;
    }

    @Override
    public boolean logout() {
        log.info("===========logout=============");
        return true;
    }
}
