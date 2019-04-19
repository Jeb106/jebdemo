package com.example.demozull.hessionclient;

import com.example.HessionApi.HelloWorldService;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 文件名：HessionConfig
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-04-17 22:20
 */
@Component
public class HessionConfig {
    @Bean
    public HessianProxyFactoryBean helloClient() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://localhost:8090/HelloWorldService");
        factory.setServiceInterface(HelloWorldService.class);
        return factory;
    }
}
