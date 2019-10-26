package com.example.demoquartz.module;

import org.springframework.stereotype.Component;

/**
 * 文件名：DealMessage
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述： 消息处理接口
 * 创建人： huJb
 * 创建时间：2019-03-01 16:11
 */
@Component
public   class DealMessage {
   public     boolean dealMessage(BaseMessageEvent baseMessageEvent){

      TaskExecute.instance.syncTest();
      return true;
   }
}
