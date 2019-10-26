package com.example.demoquartz.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 消息监听处理表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseMessageEvent {

    /**ID*/
    private String id;


    /** 业务ID*/
    private String businessId;

    /** 消息发送者*/
    private String sender;


    /** 消息接收者*/
    private String receiver;


    /** 业务类型*/
    private Integer type;


    /** 消息动作*/
    private String action;




    /**消息内容*/
    private String content;

    /**消息业务处理标示*/
    private Integer flag;

    /**预留字段1*/
    private String ext1;
    /**预留字段1*/
    private String ext2;
    /**预留字段1*/
    private String ext3;

    /**预留字段4*/
    private byte[] ext4;

    private Date timestampFlag;

    private Date createTime;

    /** 对应的DealMessage 的全路径名*/
    private String actionClassPath;

    /** 落地文件名 */
    private String fileName;
    /** 链路 */
    private String link;

    /** 是否需要返回 0 需要，-1 不需要*/
    private Integer needReturn = -1;

    /** 表名 */
    private String[] tableName;

    private Object object;



    public BaseMessageEvent(String businessId, String sender, String receiver, Integer type, String action, String actionClassPath) {
        this.businessId=businessId;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.action = action;
        this.actionClassPath = actionClassPath;
        this.createTime = new Date();

    }

    public BaseMessageEvent(String businessId, String sender, String receiver, Integer type, String action, String actionClassPath , Integer needReturn) {
        this.businessId=businessId;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.action = action;
        this.actionClassPath = actionClassPath;
        this.createTime = new Date();
        this.needReturn = needReturn;

    }

    public BaseMessageEvent(String businessId, String sender, String receiver, Integer type, String action, String actionClassPath, Integer flag, String ext1, Integer needReturn) {
        this.businessId=businessId;
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.action = action;
        this.actionClassPath = actionClassPath;
        this.createTime = new Date();
        this.flag = flag;
        this.ext1 = ext1;
        this.needReturn = needReturn;

    }


}
