package com.example.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * @ClassName：BTraceOnMethodDemo
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-07-31 19:21
 */
@BTrace
public class BTraceOnMethodDemo {
	@OnMethod(clazz = "com.fable.jsst.task.web.MonitorController", method = "dispatchExtract")
//	@OnMethod(clazz = "com.fable.dssg.system.datasource.repository.ResDataSourcesRepository", method = "save")
	public static void onThreadStart() {
		BTraceUtils.println("tracing method start");
		BTraceUtils.jstack();
	}

	@OnMethod(clazz="com.fable.jsst.task.web.MonitorController",location=@Location(value= Kind.LINE,line=57))
	public static void traceExecute(@ProbeClassName String pcn, @ProbeMethodName String pmn, int line){
		BTraceUtils.println(BTraceUtils.strcat(BTraceUtils.strcat(BTraceUtils.strcat("call ",pcn),"."),pmn));
	}
}
