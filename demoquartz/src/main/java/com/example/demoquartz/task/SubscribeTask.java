package com.example.demoquartz.task;
/* 
* 文件名：SubscribeTask 
* 版权：Copyright © Fable  Data Technology NanJing Co , Ltd. 
* 描述：〈一句话功能简述〉 
* 创建人： jinBiaoHu
* 创建时间：2017-01-04 15:30 
*/

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SubscribeTask implements Serializable{
	private static final long serialVersionUID = 6985183145103252345L;
	private String resourceId;//资源id
	private String srcDsId;//数据源
	private String[] tmpTable;//与源表对应的临时表名
	private List<String[]> trigger;//	源表、临时表对应的触发器名
	private String[] srcTable =new String[0];//源表名
	private String[] srcFields;//源表字段列表
	private String[] srcPk;//主键
	private String[] descPk;//目标主键
	private String filters;//过滤条件
	private String transfers;//转换条件
	private String destDsId;//目标数据源ID
	private String  destTable;//目标表名
	private String[] destFields;//目标字段列表
	private String taskId;//交换任务ID
	private String subscriptionId;//资源订阅方唯一标识
	private String subscriptionIdentify;//订阅id
	private String publisherId;//资源发布方唯一标识
	private String[] fileNames; //
	private int incrementModel = 1; //1触发器  2 时间戳 3日志模式 4:ftp全量 5:ftp增量 6:保留记录模式 7：mysql日志模式 8：oracle和mssq日志模式 9
	private String timestampField; //时间戳字段
	private String taskName;
	private String query;
	private int cleanDataFlag;//0删除 -1不删除
	private boolean snapUsed; //是否使用快照
	private boolean enableExchangeErrorLog;//是否启动交换日志错误记录开关
	/** 源文件夹列表 */
	private String[] folderNames;
	/** 目标文件写入方式（默认值为1，其中1:重命名 2:覆盖原文件） */
	private int fileWritingModel = 1;
	/** 是否清除目标文件（默认值为false，包括FTP和SMB） */
	private boolean cleanDestFiles = false;
	private String destCustomPath;//目标自定义路径
	private int synType;//3全量 4增量
	/**
	 * 是否关联行级日志
	 */
	private boolean relateLog;//true 关联，false；不关联
	/** 操作类型字段名*/
	private String field1;
	/** 操作时间戳字段名*/
	private String field2;
	/**是否为双向交换模式*/
	private boolean isDouble=false;
	/** 交换完成后，是否清除源文件服务器文件（默认为false） */
	private boolean isCleanSrcFiles = false;
	/** 任务标识:发布后新增 撤销发布后更新 */
	private String taskIdentify;
}

