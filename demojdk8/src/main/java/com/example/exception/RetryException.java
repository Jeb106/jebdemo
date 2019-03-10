package com.example.exception;

import lombok.Data;

/**
 * 文件名：RetryException
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-06 11:27
 */
@Data
public class RetryException extends  RuntimeException{
    private String code;
    private String msg;
}
