package com.example.demoquartz.module;

import com.example.demoquartz.config.Env;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 文件名：SendMessage
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：监听消息并处理
 * 创建人： huJb
 * 创建时间：2019-03-02 09:01
 */
@Component
@Slf4j
public class ListeningMessages {


    @EventListener
    public boolean listenMessage(BaseMessageEvent baseMessageEvent) {
        log.info("listenMessage====================");
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.example.demoquartz.module.DealMessage");


            DealMessage dealMessage = (DealMessage) Env.getBean(aClass);
            dealMessage.dealMessage(baseMessageEvent);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return true;

    }
}
