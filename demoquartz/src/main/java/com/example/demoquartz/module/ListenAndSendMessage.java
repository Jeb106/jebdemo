package com.example.demoquartz.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 文件名：ListeningMessage
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：定时从数据库中查询需要发布的消息，并发送
 * 创建人： huJb
 * 创建时间：2019-03-01 14:21
 */
@Slf4j
@Component
public class ListenAndSendMessage implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    public static void sendMessage(BaseMessageEvent event ) {
        //发送event
        applicationContext.publishEvent(event);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ListenAndSendMessage.applicationContext = applicationContext;

    }
}
